package ass6.extents;

import ass6.people.Person;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

abstract public class PeopleExtents<T extends Person> implements Iterable {

    private Set<T> people = new TreeSet<>();

    public void add(T person) {
        people.add(person);
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

}
