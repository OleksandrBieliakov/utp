package ass6.groups;

import ass6.people.Teacher;

import java.util.Collection;

public class Department extends PeopleGroup {

    public Department(String name, Collection<Teacher> teachers) {
        super(name, teachers);
    }

}
