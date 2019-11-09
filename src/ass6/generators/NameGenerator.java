package ass6.generators;

import java.util.Random;

public class NameGenerator {

    private static final Random RANDOM = new Random();

    private static final String[] MALE_NAMES =
            {"ANTONI", "JAKUB", "JAN", "SZYMON", "ALEKSANDER", "FRANCISZEK", "FILIP", "WOJCIECH", "MIKOŁAJ", "KACPER",
                    "ADAM", "STANISŁAW", "MARCEL", "MICHAŁ", "MIŁOSZ", "PAWEŁ", "BARTŁOMIEJ", "WIKTOR", "TYMON", "IGOR"};

    private static final int MALE_NAMES_LENGTH = MALE_NAMES.length;

    public static String generateMaleName() {
        return MALE_NAMES[RANDOM.nextInt(MALE_NAMES_LENGTH)];
    }

}
