package ass2.employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Worker extends Employee {

    // attributes
    // * employment date
    // * bonus
    private final LocalDate employmentDate;
    private BigDecimal bonus;

    public Worker(String firstName, String surname, LocalDate birthDate, BigDecimal salary, Manager manager,
                  LocalDate employmentDate, BigDecimal bonus) {
        super(firstName, surname, birthDate, salary, manager);
        this.employmentDate = employmentDate;
        this.bonus = bonus;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

}