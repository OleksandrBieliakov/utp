package ass6.people;

import java.time.LocalDate;
import java.util.Objects;

public class Student extends Person {

    private final String studentID;

    public Student(String PESEL, String name, String surname, LocalDate birthDate, Nationality nationality, String studentID) {
        super(PESEL, name, surname, birthDate, nationality);
        this.studentID = studentID;
    }

    public String getStudentID() {
        return studentID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return getStudentID().equals(student.getStudentID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getStudentID());
    }

    @Override
    public int compareTo(Person other) {
        if (!(other instanceof Student)) return super.compareTo(other);
        int result = studentID.compareTo(((Student) other).studentID);
        if (result != 0) return result;
        return super.compareTo(other);
    }

    @Override
    public String toString() {
        return super.toString() + ", student ID - " + studentID;
    }

}
