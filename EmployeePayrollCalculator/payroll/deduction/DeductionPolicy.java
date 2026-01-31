package EmployeePayrollCalculator.payroll.deduction;

import EmployeePayrollCalculator.payroll.model.Employee;

public interface DeductionPolicy {
    boolean isApplicable(Employee employee);
    String name();
    double apply(Employee employee, double grossPay);
}
