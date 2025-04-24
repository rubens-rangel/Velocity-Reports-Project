package com.rubensrangel.ApacheVelocityReports.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class FinancialReport {
    private String title = "";
    private LocalDate startDate;
    private LocalDate endDate;
    private ReportSummary summary = new ReportSummary();
    private List<SalesCategory> salesByCategory = new ArrayList<>();
    private List<MonthlyPerformance> monthlyPerformance = new ArrayList<>();
    private List<TopClient> topClients = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public ReportSummary getSummary() {
        return summary;
    }

    public void setSummary(ReportSummary summary) {
        this.summary = summary;
    }

    public List<SalesCategory> getSalesByCategory() {
        return salesByCategory;
    }

    public void setSalesByCategory(List<SalesCategory> salesByCategory) {
        this.salesByCategory = salesByCategory;
    }

    public List<MonthlyPerformance> getMonthlyPerformance() {
        return monthlyPerformance;
    }

    public void setMonthlyPerformance(List<MonthlyPerformance> monthlyPerformance) {
        this.monthlyPerformance = monthlyPerformance;
    }

    public List<TopClient> getTopClients() {
        return topClients;
    }

    public void setTopClients(List<TopClient> topClients) {
        this.topClients = topClients;
    }
}