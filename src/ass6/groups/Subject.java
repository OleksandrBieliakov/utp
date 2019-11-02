package ass6.groups;

import ass6.people.Student;
import ass6.people.Teacher;

import java.util.Collection;
import java.util.Objects;

public class Subject extends PeopleGroup<Student> {

    private Department department;
    private Teacher lecturer;

    public Subject(String name, Department department, Teacher lecturer, Collection<Student> students) {
        super(name, students);
        this.department = department;
        this.lecturer = lecturer;
    }

    public Department getDepartment() {
        return department;
    }

    public Teacher getLecturer() {
        return lecturer;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setLecturer(Teacher lecturer) {
        this.lecturer = lecturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject)) return false;
        if (!super.equals(o)) return false;
        Subject subject = (Subject) o;
        return Objects.equals(getDepartment(), subject.getDepartment()) &&
                Objects.equals(getLecturer(), subject.getLecturer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDepartment(), getLecturer());
    }

    @Override
    public int compareTo(PeopleGroup other) {
        int result = super.compareTo(other);
        if (result != 0) return result;
        result = department.compareTo(((Subject) other).department);
        if (result != 0) return result;
        return lecturer.compareTo(((Subject) other).lecturer);
    }

}

