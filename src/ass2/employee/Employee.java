package ass2.employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Employee extends Person {

    //
    // attributes:
    // * salary (use BigDecimal type for representing currency values)
    // * manager (empty if at top of hierarchy)

    private BigDecimal salary;
    private Manager manager;

    Employee(String firstName, String surname, LocalDate birthDate, BigDecimal salary, Manager manager) {
        super(firstName, surname, birthDate);
        this.salary = salary;
        this.manager = manager;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public Manager getManager() {
        return manager;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    // (assignment 03)
    // methods:
    // * salary is greater than given amount of money
    // * salary is less than given amount of money
    // * compare salary with other employee salary

    public boolean salaryGreaterThan(BigDecimal sum) {
        return salary.compareTo(sum) > 0;
    }

    public boolean salaryLessThan(BigDecimal sum) {
        return salary.compareTo(sum) < 0;
    }

    public int compareSalary(Employee other) {
        return salary.compareTo(other.salary);
    }

    public void giveRaise(double raise) {
        salary = salary.multiply(new BigDecimal(raise));
    }

}