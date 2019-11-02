package ass6;

import ass6.groups.StudentsGroup;
import ass6.people.Nationality;
import ass6.people.Person;
import ass6.people.Student;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class PersonTest {

    @Test
    public void comparableTest() {
        Student student1 = new Student(1, "1", "A", LocalDate.of(2000, 1, 1), Nationality.POLISH);
        Student student2 = new Student(1, "1", "A", LocalDate.of(2000, 1, 1), Nationality.POLISH);
        Student student3 = new Student(1, "1", "A", LocalDate.of(2000, 1, 1), Nationality.POLISH);
        List<Person> studentsList = new ArrayList<>(Arrays.asList(student2, student3, student1, student3));
        StudentsGroup group = new StudentsGroup("gr1", studentsList);
        Set<Person> studentsSet = group.getPeople();
        System.out.println(studentsSet);
    }

}
