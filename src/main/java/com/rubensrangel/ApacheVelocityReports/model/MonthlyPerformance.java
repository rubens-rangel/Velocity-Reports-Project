package com.rubensrangel.ApacheVelocityReports.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class MonthlyPerformance {
    private String name = "";
    private double sales = 0.0;
    private double target = 0.0;
    private double targetPercentage = 0.0;
    private double growth = 0.0;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }

    public double getTarget() {
        return target;
    }

    public void setTarget(double target) {
        this.target = target;
    }

    public double getTargetPercentage() {
        return targetPercentage;
    }

    public void setTargetPercentage(double targetPercentage) {
        this.targetPercentage = targetPercentage;
    }

    public double getGrowth() {
        return growth;
    }

    public void setGrowth(double growth) {
        this.growth = growth;
    }
}