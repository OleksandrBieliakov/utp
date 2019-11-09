package ass6.extents;

import ass6.groups.PeopleGroup;

import java.util.*;

abstract public class GroupsExtents<T extends PeopleGroup> implements Iterable, Comparable<GroupsExtents> {

    private Set<T> groups = new TreeSet<>();

    public GroupsExtents(Collection<T> groups) {
        this.groups.addAll(groups);
    }

    public GroupsExtents() {
    }

    public void add(T group) {
        groups.add(group);
    }

    public void addAll(T groups) {
        this.groups.add(groups);
    }

    public Set<T> getGroups() {
        return groups;
    }

    public int size() {
        return groups.size();
    }

    @Override
    public Iterator<T> iterator() {
        return groups.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupsExtents)) return false;
        GroupsExtents<?> that = (GroupsExtents<?>) o;
        return Objects.equals(getGroups(), that.getGroups());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroups());
    }

    @Override
    public int compareTo(GroupsExtents other) {
        return groups.size() - other.groups.size();
    }

}
