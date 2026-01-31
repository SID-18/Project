package payroll.tax;

public class TaxCalculator implements ITaxCalculator {

    @Override
    public double calculate(double grossPay) {
        double tax = 0;

        if (grossPay > 5000) {
            tax += (grossPay - 5000) * 0.30;
            grossPay = 5000;
        }
        if (grossPay > 3000) {
            tax += (grossPay - 3000) * 0.20;
            grossPay = 3000;
        }
        if (grossPay > 1000) {
            tax += (grossPay - 1000) * 0.10;
        }

        return tax;
    }
}
