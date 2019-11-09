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

    private static final Random RANDOM = new Random();
    private static final Degree[] DEGREES = Degree.values();
    private static final int DEGREES_LENGTH = DEGREES.length;

    public static Degree generateDegree() {
        return DEGREES[RANDOM.nextInt(DEGREES_LENGTH)];
    }

}
