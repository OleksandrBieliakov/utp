package ass6.people;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

abstract public class Person implements Comparable<Person> {

    private static final Locale PL_LOCALE = Locale.forLanguageTag("pl-PL");
    private static final PersonComparatorByLocale DEFAULT_COMPARATOR = new PersonComparatorByLocale(PL_LOCALE);

    private final String pesel;
    private final String name;
    private final String surname;
    private final LocalDate birthDate;
    private final Nationality nationality;

    public Person(String pesel, String name, String surname, LocalDate birthDate, Nationality nationality) {
        this.pesel = pesel;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.nationality = nationality;
    }

    public String getPesel() {
        return pesel;
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

    public static PersonComparatorByLocale getDefaultComparator() {
        return DEFAULT_COMPARATOR;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(getPesel(), person.getPesel()) &&
                Objects.equals(getName(), person.getName()) &&
                Objects.equals(getSurname(), person.getSurname()) &&
                Objects.equals(getBirthDate(), person.getBirthDate()) &&
                getNationality() == person.getNationality();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPesel(), getName(), getSurname(), getBirthDate(), getNationality());
    }

    @Override
    public int compareTo(Person other) {
        int result = DEFAULT_COMPARATOR.compare(this, other);
        if (result != 0) return result;
        result = birthDate.compareTo(other.birthDate);
        if (result != 0) return result;
        result = nationality.toString().compareTo(other.nationality.toString());
        if (result != 0) return result;
        return pesel.compareTo(other.pesel);
    }

    @Override
    public String toString() {
        return "PESEL - " + pesel +
                ", " + surname.toUpperCase() +
                " " + name.toUpperCase() +
                ", birth date - " + birthDate +
                ", " + nationality;
    }

}
