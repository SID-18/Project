package payroll.salary;

import payroll.model.Employee;

public class GrossPayCalculator implements IGrossPayCalculator {

    @Override
    public double calculate(Employee e, int units) {
        return switch (e.getType()) {
            case FULL_TIME -> e.getPayRate();
            case PART_TIME -> e.getPayRate() * Math.min(units, 120);
            case CONTRACTOR -> e.getPayRate() * units;
        };
    }
}
