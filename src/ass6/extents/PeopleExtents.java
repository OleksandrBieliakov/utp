package ass6.extents;

import ass6.people.Person;

import java.util.*;

abstract public class PeopleExtents<T extends Person> implements Iterable, Comparable<PeopleExtents> {

    private Set<T> people = new TreeSet<>();

    public PeopleExtents(Collection<T> people) {
        this.people.addAll(people);
    }

    public PeopleExtents() {
    }

    public void add(T person) {
        people.add(person);
    }

    public void addAll(Collection<T> people) {
        this.people.addAll(people);
    }

    public Set<T> getPeople() {
        return people;
    }

    public int size() {
        return people.size();
    }

    @Override
    public Iterator<T> iterator() {
        return people.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PeopleExtents)) return false;
        PeopleExtents<?> that = (PeopleExtents<?>) o;
        return Objects.equals(getPeople(), that.getPeople());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPeople());
    }

    @Override
    public int compareTo(PeopleExtents other) {
        return people.size() - other.people.size();
    }

    @Override
    public String toString() {
        return people.toString();
    }

}
