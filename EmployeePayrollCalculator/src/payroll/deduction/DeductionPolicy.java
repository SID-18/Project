package payroll.deduction;

import payroll.model.Employee;

public interface DeductionPolicy {
    boolean isApplicable(Employee employee);
    String name();
    double apply(Employee employee, double grossPay);
}
