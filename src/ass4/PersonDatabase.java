package ass4;

import ass4.comparators.BirthDateComparator;
import ass4.comparators.FirstNameComparator;

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

}