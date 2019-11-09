package ass6.groups;

import ass6.people.Teacher;

import java.util.Collection;

public class Department extends PeopleGroup<Teacher> {

    public Department(String name, Collection<Teacher> teachers) {
        super(name, teachers);
    }

    public Department(String name) {
        super(name);
    }

}
