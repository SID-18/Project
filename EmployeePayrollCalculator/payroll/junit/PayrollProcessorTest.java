package EmployeePayrollCalculator.payroll.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import EmployeePayrollCalculator.payroll.deduction.*;
import EmployeePayrollCalculator.payroll.model.*;
import EmployeePayrollCalculator.payroll.processor.PayrollProcessor;
import EmployeePayrollCalculator.payroll.salary.GrossPayCalculator;
import EmployeePayrollCalculator.payroll.tax.TaxCalculator;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PayrollProcessorTest {

    private PayrollProcessor payrollProcessor;

    private Employee fullTimeEmployee;
    private Employee partTimeEmployee;
    private Employee contractorEmployee;

    @BeforeEach
    void setUp() {
        payrollProcessor = new PayrollProcessor(
                new GrossPayCalculator(),
                new TaxCalculator(),
                List.of(
                        new HealthInsuranceDeduction(),
                        new RetirementDeduction(),
                        new UnionDeduction()
                )
        );

        fullTimeEmployee = new Employee(
                1, "Alice", EmployeeType.FULL_TIME, 6000, true, true
        );

        partTimeEmployee = new Employee(
                2, "Bob", EmployeeType.PART_TIME, 20, false, false
        );

        contractorEmployee = new Employee(
                3, "Charlie", EmployeeType.CONTRACTOR, 500, true, false
        );
    }

    @Test
    void testCalculateGrossPay_FullTime() {
        double grossPay = payrollProcessor.calculateGrossPay(fullTimeEmployee, 0);
        assertEquals(6000.00, grossPay);
    }

    @Test
    void testCalculateGrossPay_PartTime_WithHourLimit() {
        double grossPay = payrollProcessor.calculateGrossPay(partTimeEmployee, 150);
        // max 120 hours enforced
        assertEquals(2400.00, grossPay);
    }

    @Test
    void testCalculateGrossPay_Contractor() {
        double grossPay = payrollProcessor.calculateGrossPay(contractorEmployee, 10);
        assertEquals(5000.00, grossPay);
    }

    @Test
    void testCalculateTax_NoTaxBracket() {
        assertEquals(0.00, payrollProcessor.calculateTax(900.00));
    }

    @Test
    void testCalculateTax_MiddleBracket() {
        assertEquals(200.00, payrollProcessor.calculateTax(3000.00));
    }

    @Test
    void testCalculateTax_HighestBracket() {
        assertEquals(900.00, payrollProcessor.calculateTax(6000.00));
    }

    @Test
    void testCalculateDeductions_AllDeductionsApplied() {
        Map<String, Double> deductions =
                payrollProcessor.calculateDeductions(fullTimeEmployee, 6000.00);

        assertEquals(3, deductions.size());
        assertEquals(150.00, deductions.get("Health Insurance"));
        assertEquals(300.00, deductions.get("Retirement"));
        assertEquals(50.00, deductions.get("Union Dues"));
    }

    @Test
    void testCalculateDeductions_NoneApplied() {
        Map<String, Double> deductions =
                payrollProcessor.calculateDeductions(partTimeEmployee, 2000.00);

        assertTrue(deductions.isEmpty());
    }

    @Test
    void testProcessMonthlyPayroll_CreatesPaySlipsForAllEmployees() {
        List<Employee> employees = List.of(
                fullTimeEmployee,
                partTimeEmployee,
                contractorEmployee
        );

        List<PaySlip> paySlips = payrollProcessor.processMonthlyPayroll(employees);

        assertEquals(3, paySlips.size());
        assertEquals("Alice", paySlips.get(0).getEmployee().getName());
        assertEquals("Bob", paySlips.get(1).getEmployee().getName());
        assertEquals("Charlie", paySlips.get(2).getEmployee().getName());
    }
}