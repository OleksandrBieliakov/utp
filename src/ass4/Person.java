package ass4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) &&
                Objects.equals(surname, person.surname) &&
                Objects.equals(birthDate, person.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, surname, birthDate);
    }

    @Override
    public String toString() {
        return firstName + " " + surname + " " + birthDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    // assignment 8
    public void serialize(DataOutputStream output) throws Assignment08Exception {
        // serialize birth date with getTime() method
        // encapsulate IOException in Assignment08Exception
        try {
            output.writeUTF(firstName);
            output.writeUTF(surname);
            output.writeUTF(birthDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        } catch (IOException e) {
            throw new Assignment08Exception("Error during serializing person: " + this.toString(), e);
        }
    }

    public static Person deserialize(DataInputStream input) throws Assignment08Exception {
        Person person;
        try {
            String firstName = input.readUTF();
            String surname = input.readUTF();
            LocalDate birthDate = LocalDate.parse(input.readUTF());
            person = new Person(firstName, surname, birthDate);
        } catch (IOException e) {
            throw new Assignment08Exception("Error during deserializing person", e);
        }
        return person;
    }

}