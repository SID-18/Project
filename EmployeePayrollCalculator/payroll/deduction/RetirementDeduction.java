package EmployeePayrollCalculator.payroll.deduction;

import EmployeePayrollCalculator.payroll.model.Employee;

public class RetirementDeduction implements DeductionPolicy {

    public boolean isApplicable(Employee e) {
        return e.getHasRetirement();
    }

    public String name() { return "Retirement"; }

    public double apply(Employee e, double grossPay) {
        return grossPay * 0.05;
    }
}
