package ass4;

import ass4.comparators.BirthDateComparator;
import ass4.comparators.FirstNameComparator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

final class PersonDatabase {

    private List<Person> notSorted = new ArrayList<>();
    private List<Person> sortedByFirstName = new ArrayList<>();
    private List<Person> sortedBySurnameFirstNameAndBirthDate = new ArrayList<>(); // Natural order
    private List<Person> sortedByBirthDate = new ArrayList<>();
    private Map<LocalDate, List<Person>> mappedByDate = new HashMap<>();

    PersonDatabase() {
    }

    PersonDatabase(Person person) {
        add(person);
    }

    PersonDatabase(List<Person> people) {
        addAll(people);
    }

    void add(Person person) {
        if (person == null) return;
        addToAll(person);
        sortAll();
    }

    void addAll(List<Person> people) {
        if (people == null || people.isEmpty()) return;
        addToAll(people);
        sortAll();
    }

    private void addToAll(Person person) {
        notSorted.add(person);
        sortedByFirstName.add(person);
        sortedBySurnameFirstNameAndBirthDate.add(person);
        sortedByBirthDate.add(person);
        addToMap(person);
    }

    private void addToAll(List<Person> people) {
        notSorted.addAll(people);
        sortedByFirstName.addAll(people);
        sortedBySurnameFirstNameAndBirthDate.addAll(people);
        sortedByBirthDate.addAll(people);
        addToMap(people);
    }

    private void addToMap(Person person) {
        List<Person> bucket = mappedByDate.computeIfAbsent(person.birthDate(), k -> new ArrayList<>());
        bucket.add(person);
    }

    private void addToMap(List<Person> people) {
        people.forEach(this::addToMap);
    }

    private void sortAll() {
        sortedByFirstName.sort(new FirstNameComparator());
        sortedBySurnameFirstNameAndBirthDate.sort(Comparator.naturalOrder());
        sortedByBirthDate.sort(new BirthDateComparator());
    }

    int size() {
        return notSorted.size();
    }

    List<Person> notSorted() {
        return notSorted;
    }

    List<Person> sortedByFirstName() {
        return sortedByFirstName;
    }

    List<Person> sortedBySurnameFirstNameAndBirthDate() {
        return sortedBySurnameFirstNameAndBirthDate;
    }

    List<Person> sortedByBirthDate() {
        return sortedByBirthDate;
    }

    List<Person> bornOnDay(LocalDate date) {
        return mappedByDate.get(date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonDatabase)) return false;
        PersonDatabase database = (PersonDatabase) o;
        return Objects.equals(notSorted, database.notSorted) &&
                Objects.equals(sortedByFirstName, database.sortedByFirstName) &&
                Objects.equals(sortedBySurnameFirstNameAndBirthDate, database.sortedBySurnameFirstNameAndBirthDate) &&
                Objects.equals(sortedByBirthDate, database.sortedByBirthDate) &&
                Objects.equals(mappedByDate, database.mappedByDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notSorted, sortedByFirstName, sortedBySurnameFirstNameAndBirthDate, sortedByBirthDate, mappedByDate);
    }

    // assignment 8 - factory method based on deserialization
    public static PersonDatabase deserialize(DataInputStream input) throws Assignment08Exception {
        List<Person> people = new LinkedList<>();
        int size;
        try {
            size = input.readInt();
        } catch (IOException e) {
            throw new Assignment08Exception("Error during deserializing person database size", e);
        }
        for (int i = 0; i < size; ++i)
            people.add(Person.deserialize(input));
        return new PersonDatabase(people);
    }

    // assignment 8
    public void serialize(DataOutputStream output) throws Assignment08Exception {
        try {
            output.writeInt(size());
        } catch (IOException e) {
            throw new Assignment08Exception("Error during serializing person database size", e);
        }
        for (Person person : notSorted)
            person.serialize(output);
    }

}