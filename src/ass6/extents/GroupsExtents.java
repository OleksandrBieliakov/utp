package ass6.extents;

import ass6.groups.PeopleGroup;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

abstract public class GroupsExtents<T extends PeopleGroup> implements Iterable {

    private Set<T> groups = new TreeSet<>();

    public void add(T group) {
        groups.add(group);
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

}
