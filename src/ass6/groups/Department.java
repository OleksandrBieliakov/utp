package ass6.groups;

import ass6.people.Person;

import java.util.Collection;

public class Department extends PeopleGroup {

    public Department(String name, Collection<Person> teachers) {
        super(name, teachers);
    }

}
