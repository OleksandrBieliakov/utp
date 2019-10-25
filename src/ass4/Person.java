package ass4;

import java.time.LocalDate;

public class Person implements Comparable<Person> {

    private final String firstName;
    private final String surname;
    private final LocalDate birthDate;

    public Person(String firstName, String surname, LocalDate birthDate) {
        this.firstName = firstName;
        this.surname = surname;
        this.birthDate = birthDate;
    }

    public String firstName() {
        return firstName;
    }

    public String surname() {
        return surname;
    }

    public LocalDate birthDate() {
        return birthDate;
    }

    @Override
    public int compareTo(Person otherPerson) {
        int result = surname.compareTo(otherPerson.surname);
        if (result != 0) return result;
        result = firstName.compareTo(otherPerson.firstName);
        if (result != 0) return result;
        result = birthDate.compareTo(otherPerson.birthDate);
        return result;
    }

    @Override
    public String toString() {
        return firstName + " " + surname + " " + birthDate.getYear() + "-" + birthDate.getMonthValue() + "-" + birthDate.getDayOfMonth();
    }

}