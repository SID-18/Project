package payroll;

import payroll.deduction.*;
import payroll.model.*;
import payroll.processor.PayrollProcessor;
import payroll.salary.GrossPayCalculator;
import payroll.tax.TaxCalculator;

import java.util.List;

public class PayrollTest {

    static void main(String[] args) {

        List<PaySlip> paySlips = getPaySlipList();

        for (PaySlip paySlip : paySlips) {
            Employee e = paySlip.getEmployee();

            System.out.println("\nEmployee: " + e.getName() +
                    " (" + e.getType() + ")");
            System.out.println("Net Pay: $" + paySlip.getNetPay());

            if (paySlip.getDeductions().isEmpty()) {
                System.out.println("Deductions: None");
            } else {
                System.out.println("Deductions:");
                paySlip.getDeductions().forEach((name, amount) ->
                        System.out.println("  - " + name + ": $" + amount)
                );
            }
        }
    }

    private static List<PaySlip> getPaySlipList() {
        List<Employee> employees = List.of(
                new Employee(1, "Test1", EmployeeType.FULL_TIME, 6000, true, true),
                new Employee(2, "Test2", EmployeeType.FULL_TIME, 4000, false, false),
                new Employee(3, "Test3", EmployeeType.PART_TIME, 25, true, false),
                new Employee(4, "Test4", EmployeeType.PART_TIME, 30, false, false),
                new Employee(5, "Test5", EmployeeType.CONTRACTOR, 300, false, false),
                new Employee(6, "Test6", EmployeeType.CONTRACTOR, 500, true, false)
        );

        PayrollProcessor payrollProcessor = new PayrollProcessor(
                new GrossPayCalculator(),
                new TaxCalculator(),
                List.of(
                        new HealthInsuranceDeduction(),
                        new RetirementDeduction(),
                        new UnionDeduction()
                )
        );

        return payrollProcessor.processMonthlyPayroll(employees);
    }
}
