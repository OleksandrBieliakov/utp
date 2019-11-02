package ass6.people;

import java.util.Random;

public enum Degree {

    PROFESSOR,
    DOCTOR_HABILITATED,
    DOCTOR,
    MASTER_ENGINEER,
    MASTER,
    ENGINEER,
    BACHELOR;

    public static Degree generateDegree() {
        Degree[] degrees = Degree.values();
        return degrees[new Random().nextInt(degrees.length)];
    }

}
