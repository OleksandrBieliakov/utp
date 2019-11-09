package ass6.extents;

import ass6.people.Person;

import java.util.Collection;

public class People extends PeopleExtents<Person> {

    public People(Collection<Person> people) {
        super(people);
    }

    public People() {
    }

}
