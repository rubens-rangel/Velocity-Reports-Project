//package com.rubensrangel.ApacheVelocityReports.service;
//
//import com.rubensrangel.ApacheVelocityReports.model.ReportData;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ReportServiceTest {
//
//    @Autowired
//    private ReportService reportService;
//
//    @Test
//    void generateReport() {
//        ReportData reportData = new ReportData();
//        reportData.setTitle("Titulo");
//        List<String> itensList = new ArrayList<>();
//        itensList.add("Borracha");
//        reportData.setItems(itensList);
//
//        String resposta = reportService.generateReport(reportData);
//
//        assertNotNull(resposta);
//    }
//}