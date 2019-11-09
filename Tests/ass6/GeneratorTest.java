package ass6;

import ass6.extents.Departments;
import ass6.extents.Students;
import ass6.extents.Teachers;
import ass6.generators.Generator;
import ass6.groups.Department;
import ass6.groups.StudentsGroup;
import ass6.groups.Subject;
import ass6.people.Person;
import ass6.people.Student;
import ass6.people.Teacher;
import org.junit.Assert;
import org.junit.Test;

public class GeneratorTest {

    private static final int STUDENTS_NUMBER = 100;
    private static final int GROUPS_NUMBER = 12;
    private static final int GROUP_SIZE = STUDENTS_NUMBER / GROUPS_NUMBER;

    private static final int DEPARTMENTS_NUMBER = 3;
    private static final int TEACHERS_NUMBER = 10;
    private static final int DEPARTMENT_SIZE = TEACHERS_NUMBER / DEPARTMENTS_NUMBER;

    private static final int SUBJECTS_NUMBER = 10;
    private static final int STUDENTS_PER_SUBJECT = STUDENTS_NUMBER / SUBJECTS_NUMBER;

    @Test
    public void generateAndFillAll() {
        Generator generator = new Generator();
        generator.generateAndFillAll();

        Assert.assertEquals(STUDENTS_NUMBER, generator.getStudents().size());
        Assert.assertEquals(TEACHERS_NUMBER, generator.getTeachers().size());
        Assert.assertEquals(GROUPS_NUMBER, generator.getStudentsGroups().size());
        Assert.assertEquals(DEPARTMENTS_NUMBER, generator.getDepartments().size());
        Assert.assertEquals(SUBJECTS_NUMBER, generator.getSubjects().size());

        // STUDENTS GROUPS
        Students students = new Students();
        for (StudentsGroup group : generator.getStudentsGroups().getGroups()) {
            students.addAll(group.getPeople());
            Assert.assertTrue(group.size() >= GROUP_SIZE && group.size() <= GROUP_SIZE + 1);
            System.out.println(group);
        }
        Assert.assertEquals(STUDENTS_NUMBER, students.size());

        // DEPARTMENTS
        Teachers teachers = new Teachers();
        for (Department department : generator.getDepartments().getGroups()) {
            teachers.addAll(department.getPeople());
            Assert.assertTrue(department.size() >= DEPARTMENT_SIZE && department.size() <= DEPARTMENT_SIZE + 1);
            System.out.println(department);
        }
        Assert.assertEquals(TEACHERS_NUMBER, teachers.size());

        // SUBJECTS
        students = new Students();
        teachers = new Teachers();
        Departments departments = new Departments();
        for (Subject subject : generator.getSubjects().getGroups()) {
            students.addAll(subject.getPeople());
            teachers.add(subject.getLecturer());
            departments.add(subject.getDepartment());
            Assert.assertTrue(subject.size() >= STUDENTS_PER_SUBJECT && subject.size() <= STUDENTS_PER_SUBJECT + 1);
            System.out.println(subject);
        }
        Assert.assertEquals(STUDENTS_NUMBER, students.size());
        Assert.assertEquals(TEACHERS_NUMBER, teachers.size());
        Assert.assertEquals(DEPARTMENTS_NUMBER, departments.size());

        // TEACHERS
        int count = 1;
        System.out.println("Teachers:");
        for (Teacher teacher : generator.getTeachers().getPeople())
            System.out.println(count++ + ". " + teacher);

        // STUDENTS
        count = 1;
        System.out.println("\nStudents:");
        for (Student student : generator.getStudents().getPeople())
            System.out.println(count++ + ". " + student);

        // PEOPLE
        count = 1;
        System.out.println("\nPeople: ");
        for (Person person : generator.getPeople().getPeople())
            System.out.println(count++ + ". " + person);
    }

}
