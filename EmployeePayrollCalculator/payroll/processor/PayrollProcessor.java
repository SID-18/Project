package EmployeePayrollCalculator.payroll.processor;

import EmployeePayrollCalculator.payroll.deduction.DeductionPolicy;
import EmployeePayrollCalculator.payroll.model.Employee;
import EmployeePayrollCalculator.payroll.model.PaySlip;
import EmployeePayrollCalculator.payroll.salary.IGrossPayCalculator;
import EmployeePayrollCalculator.payroll.tax.ITaxCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class PayrollProcessor {

    private final IGrossPayCalculator iGrossPayCalculator;
    private final ITaxCalculator iTaxCalculator;
    private final List<DeductionPolicy> deductionPolicies;

    public PayrollProcessor(IGrossPayCalculator iGrossPayCalculator,
                            ITaxCalculator iTaxCalculator,
                            List<DeductionPolicy> deductionPolicies) {
        this.iGrossPayCalculator = iGrossPayCalculator;
        this.iTaxCalculator = iTaxCalculator;
        this.deductionPolicies = deductionPolicies;
    }

    // Calculate Gross Pay
    public double calculateGrossPay(Employee employee, int hoursOrDays) {
        double gross = iGrossPayCalculator.calculate(employee, hoursOrDays);
        return round(gross);
    }

    // Calculate Tax
    public double calculateTax(double grossPay) {
        return round(iTaxCalculator.calculate(grossPay));
    }

    // Calculate Deductions
    public Map<String, Double> calculateDeductions(Employee employee, double grossPay) {
        Map<String, Double> deductions = new LinkedHashMap<>();

        for (DeductionPolicy rule : deductionPolicies) {
            if (rule.isApplicable(employee)) {
                double amount = round(rule.apply(employee, grossPay));
                deductions.put(rule.name(), amount);
            }
        }
        return deductions;
    }

    // Generate PaySlip
    public PaySlip generatePaySlip(Employee employee, int hoursOrDays) {
        double grossPay = calculateGrossPay(employee, hoursOrDays);
        double tax = calculateTax(grossPay);

        Map<String, Double> deductions = calculateDeductions(employee, grossPay);
        double totalDeductions =
                deductions.values().stream().mapToDouble(Double::doubleValue).sum();

        double netPay = round(grossPay - tax - totalDeductions);

        return new PaySlip(employee, grossPay, tax, deductions, netPay);
    }

    // Process Monthly Payroll
    public List<PaySlip> processMonthlyPayroll(List<Employee> employees) {
        List<PaySlip> paySlips = new ArrayList<>();
        //
        for (Employee employee : employees) {
            int hoursOrDays = resolveUnits(employee);
            paySlips.add(generatePaySlip(employee, hoursOrDays));
        }
        return paySlips;
    }

    // assumption since we don't have number of hours or days for monthly payroll
    // in signature
    private int resolveUnits(Employee employee) {
        return switch (employee.getType()) {
            case FULL_TIME -> 0;
            case PART_TIME -> 100;
            case CONTRACTOR -> 20;
        };
    }

    private double round(double value) {
        return BigDecimal.valueOf(value)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
