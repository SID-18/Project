package payroll.model;

public class Employee {
    private final int id;
    private final String name;
    private final EmployeeType type;
    private final double payRate;
    private final boolean isUnionMember;
    private final boolean hasRetirement;

    public Employee(int id, String name, EmployeeType type,
                    double payRate, boolean isUnionMember, boolean hasRetirement) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.payRate = payRate;
        this.isUnionMember = isUnionMember;
        this.hasRetirement = hasRetirement;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public EmployeeType getType() { return type; }
    public double getPayRate() { return payRate; }
    public boolean getIsUnionMember() { return isUnionMember; }
    public boolean getHasRetirement() { return hasRetirement; }
}
