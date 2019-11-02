package ass6.people;

import java.time.LocalDate;
import java.util.Objects;

public class Student extends Person {

    private static int nextID = 0;

    private final int studentID;

    public Student(String PESEL, String name, String surname, LocalDate birthDate, Nationality nationality) {
        super(PESEL, name, surname, birthDate, nationality);
        studentID = nextID++;
    }

    public int getStudentID() {
        return studentID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return getStudentID() == student.getStudentID();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getStudentID());
    }

    @Override
    public int compareTo(Person person) {
        int result = super.compareTo(person);
        if (result != 0 || !(person instanceof Student)) return result;
        return studentID - ((Student) person).studentID;
    }

    @Override
    public String toString() {
        return super.toString() + " studentID:" + studentID;
    }

}
