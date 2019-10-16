package ass2.employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Trainee extends Employee {

    // attributes:
    // * practice start date
    // * practice length (in days)
    private final LocalDate practiceStart;
    private int practiceLength;

    public Trainee(String firstName, String surname, LocalDate dateBirth, BigDecimal salary, Manager manager,
                   LocalDate practiceStart, int practiceLength) {
        super(firstName, surname, dateBirth, salary, manager);
        this.practiceStart = practiceStart;
        this.practiceLength = practiceLength;
    }

    public LocalDate getPracticeStart() {
        return practiceStart;
    }

    public int getPracticeLength() {
        return practiceLength;
    }

    public void setPracticeLength(int practiceLength) {
        this.practiceLength = practiceLength;
    }

}