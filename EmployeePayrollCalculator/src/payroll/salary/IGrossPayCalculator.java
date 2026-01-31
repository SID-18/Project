package payroll.salary;

import payroll.model.Employee;

public interface IGrossPayCalculator {
    double calculate(Employee employee, int units);
}
