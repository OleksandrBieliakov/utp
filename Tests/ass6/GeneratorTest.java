package ass6;

import ass6.generators.Generator;
import org.junit.Assert;
import org.junit.Test;

public class GeneratorTest {

    private static final int STUDENTS_NUMBER = 100;
    private static final int GROUPS_NUMBER = 12;
    private static final int GROUP_SIZE = STUDENTS_NUMBER / GROUPS_NUMBER;
    private static final int EXTRA_STUDENTS = STUDENTS_NUMBER % GROUPS_NUMBER;

    private static final int DEPARTMENTS_NUMBER = 3;
    private static final int TEACHER_NUMBER = 10;
    private static final int DEPARTMENT_SIZE = TEACHER_NUMBER / DEPARTMENTS_NUMBER;
    private static final int EXTRA_TEACHERS = TEACHER_NUMBER % DEPARTMENTS_NUMBER;

    private static final int SUBJECTS_NUMBER = 10;
    private static final int STUDENTS_PER_SUBJECT = STUDENTS_NUMBER / SUBJECTS_NUMBER;
    private static final int EXTRA_SUBJECT_STUDENTS = STUDENTS_NUMBER % SUBJECTS_NUMBER;

    @Test
    public void generatorTest() {
        Generator generator = new Generator();
        generator.generateAndFillAll();
        Assert.assertEquals(STUDENTS_NUMBER, generator.getStudents().size());
        Assert.assertEquals(TEACHER_NUMBER, generator.getTeachers().size());
        Assert.assertEquals(GROUPS_NUMBER, generator.getStudentsGroups().size());
        Assert.assertEquals(DEPARTMENTS_NUMBER, generator.getDepartments().size());
        Assert.assertEquals(SUBJECTS_NUMBER, generator.getSubjects().size());
    }

}
