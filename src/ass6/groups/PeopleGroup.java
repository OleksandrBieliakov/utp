package ass6.groups;

import ass6.people.Nationality;
import ass6.people.Person;
import ass6.people.PersonComparatorByLocale;

import java.text.Collator;
import java.util.*;

abstract public class PeopleGroup<T extends Person> implements Comparable<PeopleGroup> {

    private static final Collator PL_COLLATOR = Collator.getInstance(Locale.forLanguageTag("pl-PL"));

    private String name;
    private Set<T> people;
    private Map<Nationality, Set<T>> peopleByNationality = new HashMap<>();

    public PeopleGroup(String name, Collection<T> people) {
        this.name = name;
        this.people = new TreeSet<>(people);
        addToMap(people);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<T> getPeople() {
        return people;
    }

    private void addToMap(T person) {
        Set<T> bucket = peopleByNationality.computeIfAbsent(person.getNationality(), k ->
                new TreeSet<>(new PersonComparatorByLocale(person.getNationality().getLocale())));
        bucket.add(person);
    }

    private void addToMap(Collection<T> people) {
        people.forEach(this::addToMap);
    }

    public Set<T> getPeopleOfNationality(Nationality nationality) {
        return peopleByNationality.get(nationality);
    }

    public int size() {
        return people.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PeopleGroup)) return false;
        PeopleGroup that = (PeopleGroup) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getPeople(), that.getPeople());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPeople());
    }

    @Override
    public int compareTo(PeopleGroup other) {
        int result = PL_COLLATOR.compare(name, other.name);
        if (result == 0) result = people.size() - other.people.size();
        return result;
    }

    @Override
    public String toString() {
        return name + ":\n" + people.toString();
    }

}
