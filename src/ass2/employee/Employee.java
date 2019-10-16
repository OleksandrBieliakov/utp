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

}