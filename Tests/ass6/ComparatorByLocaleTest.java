package ass6;

import ass6.generators.Generator;
import ass6.people.Nationality;
import ass6.people.PersonComparatorByLocale;
import ass6.people.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ComparatorByLocaleTest {

    @Test
    public void sort() {
        Generator generator = new Generator();
        generator.generateAll();

        int count = 1;
        System.out.println("Students natural order (sorted by studentID first):");
        for (Student student : generator.getStudents().getPeople())
            System.out.println(count++ + ". " + student);

        List<Student> students = new ArrayList<>(generator.getStudents().getPeople());
        students.sort(new PersonComparatorByLocale(Nationality.POLISH.getLocale()));

        count = 1;
        System.out.println("\nStudents sorted by Polish locale:");
        for (Student student : students)
            System.out.println(count++ + ". " + student);

    }

}
