package ass2.employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Trainee extends Employee {

    // attributes:
    // * practice start date
    // * practice length (in days)
    private final LocalDate practiceStart;

    public Trainee(String firstName, String surname, LocalDate dateBirth, BigDecimal salary, Manager manager,
                   LocalDate practiceStart) {
        super(firstName, surname, dateBirth, salary, manager);
        this.practiceStart = practiceStart;
    }

    public LocalDate getPracticeStart() {
        return practiceStart;
    }

    public long getPracticeLength() {
        return ChronoUnit.DAYS.between(practiceStart, LocalDate.now());
    }

    // (assignment 03)
    // * practice length is shorter than given number of days
    // * practice length is longer than given number of days

    public boolean practiceShorterThan(int days) {
        return getPracticeLength() < days;
    }

    public boolean practiceLongerThan(int days) {
        return getPracticeLength() > days;
    }

}