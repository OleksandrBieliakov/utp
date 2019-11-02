package ass6.people;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

abstract public class Person implements Comparable<Person> {

    private static final PersonComparatorByLocale PL_COMPARATOR = new PersonComparatorByLocale(Locale.forLanguageTag("pl-PL"));

    private final String PESEL;
    private final String name;
    private final String surname;
    private final LocalDate birthDate;
    private final Nationality nationality;

    public Person(String PESEL, String name, String surname, LocalDate birthDate, Nationality nationality) {
        this.PESEL = PESEL;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.nationality = nationality;
    }

    public String getPESEL() {
        return PESEL;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Nationality getNationality() {
        return nationality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(getPESEL(), person.getPESEL()) &&
                Objects.equals(getName(), person.getName()) &&
                Objects.equals(getSurname(), person.getSurname()) &&
                Objects.equals(getBirthDate(), person.getBirthDate()) &&
                getNationality() == person.getNationality();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPESEL(), getName(), getSurname(), getBirthDate(), getNationality());
    }

    @Override
    public int compareTo(Person other) {
        int result = PL_COMPARATOR.compare(this, other);
        if (result != 0) return result;
        result = birthDate.compareTo(other.birthDate);
        if (result != 0) return result;
        result = nationality.toString().compareTo(other.nationality.toString());
        if (result != 0) return result;
        return PESEL.compareTo(other.PESEL);
    }

    @Override
    public String toString() {
        return "PESEL:" + PESEL + " " + name + " " + surname + " " + nationality + " " + birthDate;
    }

}
