package ass2.employee;

import java.time.LocalDate;
import java.time.Period;

public abstract class Person {

    // To implement an attribute means that you provide a backing field and
    // getter or optionally setter for read-write properties/attributes
    //
    // NO BACKING FIELDS SHOULD BE PROVIDED FOR DERIVED ATTRIBUTES
    // THOSE SHOULD BE COMPUTED ON-LINE
    //
    // attributes:
    // * first name (read-only)
    // * surname (read-only)
    // * birth date (read-only) --- date MUST BE represented by an instance of
    // type designed for date representation (either Date or LocalDate)
    //
    // * age (derived --- computed based on birth date) --- implemented as a
    // getter calculating the difference between the current date and birth date

    private final String firstName; // backing field
    private final String surname;
    private final LocalDate birthDate;

    Person(String firstName, String surname, LocalDate birthDate) {
        this.firstName = firstName;
        this.surname = surname;
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Period getAge() {
        return Period.between(birthDate, LocalDate.now());
    }

    // (assignment 03)
    // methods:
    // * is older than other person
    // * is younger than other person
    // * compare age with other person's age

    public boolean isOlder(Person other) {
        return birthDate.compareTo(other.birthDate) < 0;
    }

    public boolean isYounger(Person other) {
        return birthDate.compareTo(other.birthDate) > 0;
    }

    public int compareAge(Person other) {
        return birthDate.compareTo(other.birthDate) * (-1);
    }

}