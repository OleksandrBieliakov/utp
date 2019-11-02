package ass6.people;

import java.time.LocalDate;
import java.util.Objects;

public class Teacher extends Person {

    private final LocalDate hireDate;
    private Degree degree;

    public Teacher(String PESEL, String name, String surname, LocalDate birthDate, Nationality nationality, LocalDate hireDate, Degree degree) {
        super(PESEL, name, surname, birthDate, nationality);
        this.hireDate = hireDate;
        this.degree = degree;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(getHireDate(), teacher.getHireDate()) &&
                getDegree() == teacher.getDegree();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getHireDate(), getDegree());
    }

    @Override
    public int compareTo(Person other) {
        int result = super.compareTo(other);
        if (result != 0 || !(other instanceof Teacher)) return result;
        result = degree.compareTo(((Teacher) other).degree);
        if (result != 0) return result;
        return hireDate.compareTo(((Teacher) other).hireDate);
    }

}
