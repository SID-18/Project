package EmployeePayrollCalculator.payroll.salary;

import EmployeePayrollCalculator.payroll.model.Employee;

public interface IGrossPayCalculator {
    double calculate(Employee employee, int units);
}
