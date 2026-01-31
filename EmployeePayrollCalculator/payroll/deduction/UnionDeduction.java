package EmployeePayrollCalculator.payroll.deduction;

import EmployeePayrollCalculator.payroll.model.Employee;

public class UnionDeduction implements DeductionPolicy {

    public boolean isApplicable(Employee e) {
        return e.getIsUnionMember();
    }

    public String name() { return "Union Dues"; }

    public double apply(Employee e, double grossPay) {
        return 50.00;
    }
}
