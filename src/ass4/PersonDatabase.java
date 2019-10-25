package ass4;

import ass4.comparators.BirthDateComparator;
import ass4.comparators.FirstNameComparator;

import java.time.LocalDate;
import java.util.*;

final class PersonDatabase {

    private List<Person> notSorted;
    private List<Person> sortedByFirstName;
    private List<Person> sortedBySurnameFirstNameAndBirthDate; // Natural order
    private List<Person> sortedByBirthDate;
    private Map<LocalDate, List<Person>> mappedByDate;

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
        List<Person> personHolder = new ArrayList<>();
        personHolder.add(person);
        addAll(personHolder);
    }

    void addAll(List<Person> people) {
        if (people == null || people.size() == 0) return;
        if (notSorted == null) initializeAll();
        addToAll(people);
        sortAll();
    }

    private void initializeAll() {
        notSorted = new ArrayList<>();
        sortedByFirstName = new ArrayList<>();
        sortedBySurnameFirstNameAndBirthDate = new ArrayList<>();
        sortedByBirthDate = new ArrayList<>();
        mappedByDate = new HashMap<>();
    }

    private void addToAll(List<Person> people) {
        notSorted.addAll(people);
        sortedByFirstName.addAll(people);
        sortedBySurnameFirstNameAndBirthDate.addAll(people);
        sortedByBirthDate.addAll(people);
        addToMap(people);
    }

    private void addToMap(List<Person> people) {
        people.forEach(person -> {
            LocalDate date = person.birthDate();
            List<Person> bucket = mappedByDate.computeIfAbsent(date, k -> new ArrayList<>());
            bucket.add(person);
        });
    }

    private void sortAll() {
        sortedByFirstName.sort(new FirstNameComparator());
        sortedBySurnameFirstNameAndBirthDate.sort(Comparator.naturalOrder());
        sortedByBirthDate.sort(new BirthDateComparator());
    }

    int size() {
        if (notSorted == null) return 0;
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