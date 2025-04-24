package com.rubensrangel.ApacheVelocityReports.service;

import com.itextpdf.html2pdf.HtmlConverter;
import com.rubensrangel.ApacheVelocityReports.model.FinancialReport;
import com.rubensrangel.ApacheVelocityReports.model.ReportSummary;
import com.rubensrangel.ApacheVelocityReports.model.TopClient;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final VelocityEngine velocityEngine;

    public ReportService() {
        Properties props = new Properties();
        props.setProperty("resource.loader", "classpath");
        props.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine = new VelocityEngine();
        velocityEngine.init(props);
    }

    public byte[] generateFinancialReport(FinancialReport reportData) {
        try {
            VelocityContext context = new VelocityContext();
            Map<String, Object> reportMap = new HashMap<>();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // Title
            reportMap.put("title", reportData.getTitle() != null ? reportData.getTitle() : "");

            // Dates
            reportMap.put("startDate", formatDate(reportData.getStartDate(), dateFormatter));
            reportMap.put("endDate", formatDate(reportData.getEndDate(), dateFormatter));

            // Summary
            ReportSummary summary = reportData.getSummary() != null ? reportData.getSummary() : new ReportSummary();
            reportMap.put("summary", summary);

            // Collections
            reportMap.put("salesByCategory", reportData.getSalesByCategory() != null
                    ? reportData.getSalesByCategory()
                    : Collections.emptyList());

            reportMap.put("monthlyPerformance", reportData.getMonthlyPerformance() != null
                    ? reportData.getMonthlyPerformance()
                    : Collections.emptyList());

            // Top Clients
            List<Map<String, Object>> topClients = new ArrayList<>();
            if (reportData.getTopClients() != null) {
                topClients = reportData.getTopClients().stream()
                        .map(this::mapTopClient)
                        .collect(Collectors.toList());
            }
            reportMap.put("topClients", topClients);

            context.put("report", reportMap);

            // Formatting tools
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            NumberFormat percentFormat = NumberFormat.getPercentInstance(new Locale("pt", "BR"));
            percentFormat.setMaximumFractionDigits(2);

            context.put("currency", currencyFormat);
            context.put("percent", percentFormat);
            context.put("now", LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

            String htmlContent = processTemplate("templates/financial-report.vm", context);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            HtmlConverter.convertToPdf(htmlContent, outputStream);

            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar relatório", e);
        }
    }

    private String formatDate(LocalDate date, DateTimeFormatter formatter) {
        return date != null ? date.format(formatter) : "N/A";
    }

    private Map<String, Object> mapTopClient(TopClient client) {
        Map<String, Object> clientMap = new HashMap<>();
        clientMap.put("name", client.getName() != null ? client.getName() : "");
        clientMap.put("totalValue", client.getTotalValue());
        clientMap.put("lastPurchase", formatDate(client.getLastPurchase(),
                DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        clientMap.put("loyaltyLevel", client.getLoyaltyLevel() != null ? client.getLoyaltyLevel() : "");
        return clientMap;
    }

    private String processTemplate(String templatePath, VelocityContext context) {
        Template template = velocityEngine.getTemplate(templatePath);
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        return writer.toString();
    }
}