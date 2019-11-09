package ass6.extents;

import ass6.people.Student;

import java.util.Collection;

public class Students extends PeopleExtents<Student> {

    public Students(Collection<Student> people) {
        super(people);
    }

    public Students() {
    }

}
