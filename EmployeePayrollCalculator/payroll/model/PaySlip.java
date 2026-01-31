package EmployeePayrollCalculator.payroll.model;

import java.util.Map;

public class PaySlip {
    private final Employee employee;
    private final double grossPay;
    private final double taxAmount;
    private final Map<String, Double> deductions;
    private final double netPay;

    public PaySlip(Employee employee, double grossPay, double taxAmount,
                   Map<String, Double> deductions, double netPay) {
        this.employee = employee;
        this.grossPay = grossPay;
        this.taxAmount = taxAmount;
        this.deductions = deductions;
        this.netPay = netPay;
    }

    public Employee getEmployee() {
        return employee;
    }

    public double getGrossPay() {
        return grossPay;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public Map<String, Double> getDeductions() {
        return deductions;
    }

    public double getNetPay() {
        return netPay;
    }
}
