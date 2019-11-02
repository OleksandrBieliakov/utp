package ass6.groupscollections;

import ass6.groups.PeopleGroup;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

abstract public class PeopleGroups<T extends PeopleGroup> implements Comparable<PeopleGroups> {

    private Map<String, T> peopleGroups = new TreeMap<>();

    public PeopleGroups(Collection<T> peopleGroups) {
        addAll(peopleGroups);
    }

    public Map<String, T> getPeopleGroups() {
        return peopleGroups;
    }

    public void addAll(Collection<T> peopleGroups) {
        peopleGroups.forEach(this::add);
    }

    public void add(T peopleGroup) {
        peopleGroups.put(peopleGroup.getName(), peopleGroup);
    }

    public T get(String name) {
        return peopleGroups.get(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PeopleGroups)) return false;
        PeopleGroups<?> that = (PeopleGroups<?>) o;
        return Objects.equals(getPeopleGroups(), that.getPeopleGroups());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPeopleGroups());
    }

    @Override
    public int compareTo(PeopleGroups other) {
        return peopleGroups.size() - other.peopleGroups.size();
    }

    @Override
    public String toString() {
        return peopleGroups.toString();
    }

}
