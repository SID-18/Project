package payroll.deduction;

import payroll.model.Employee;
import payroll.model.EmployeeType;

public class HealthInsuranceDeduction implements DeductionPolicy {

    public boolean isApplicable(Employee e) {
        return e.getType() == EmployeeType.FULL_TIME;
    }

    public String name() { return "Health Insurance"; }

    public double apply(Employee e, double grossPay) {
        return 150.00;
    }
}
