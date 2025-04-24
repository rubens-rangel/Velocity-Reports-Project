package com.rubensrangel.ApacheVelocityReports.controller;

import com.rubensrangel.ApacheVelocityReports.model.FinancialReport;
import com.rubensrangel.ApacheVelocityReports.model.ReportSummary;
import com.rubensrangel.ApacheVelocityReports.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }


    @PostMapping("/financial-report")
    public ResponseEntity<byte[]> generateFinancialReport(@RequestBody FinancialReport reportData) {
        byte[] pdfBytes = reportService.generateFinancialReport(reportData);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(
                ContentDisposition.builder("attachment")
                        .filename("relatorio-financeiro.pdf")
                        .build());

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);

    }
}