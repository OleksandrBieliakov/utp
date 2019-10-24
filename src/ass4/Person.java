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
        int result;
        if ((result = firstName.compareTo(otherPerson.firstName)) != 0) return result;
        if ((result = surname.compareTo(otherPerson.surname)) != 0) return result;
        return birthDate.compareTo(otherPerson.birthDate);
    }

}