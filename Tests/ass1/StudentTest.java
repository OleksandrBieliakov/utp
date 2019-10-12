package ass1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentTest {

    private static final String NAME = "Sanya";
    private static final Double GRADE = 4.;

    private Student student;

    @Before
    public void before() {
        student = new Student(NAME, GRADE);
        Assert.assertEquals(NAME, student.getName());
        Assert.assertEquals(GRADE, student.getAverageGrade(), 0.01);
    }

    @Test
    public void aggregate() {
        Assert.assertEquals(GRADE, student.aggregate(null), 0.01);
        Assert.assertEquals(4.5, student.aggregate(4.5), 0.01);
        Assert.assertEquals(GRADE, student.aggregate(3.), 0.01);
    }

    @Test
    public void deepClone() {
        Student clone = student.deepClone();
        Assert.assertEquals(student.getName(), clone.getName());
        Assert.assertEquals(student.getAverageGrade(), clone.getAverageGrade(), 0.01);
        Assert.assertNotSame(student, clone);
    }
}
