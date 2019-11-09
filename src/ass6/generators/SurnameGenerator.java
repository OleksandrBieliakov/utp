package ass6.generators;

import java.util.Random;

public class SurnameGenerator {

    private static final Random RANDOM = new Random();

    private static final String[] MALE_SURNAMES =
            {"Nowak", "Kowalski", "Wiśniewski", "Wójcik", "Kowalczyk", "Kamiński", "Lewandowski", "Zieliński", "Szymański",
                    "Woźniak", "Dąbrowski", "Kozłowski", "Jankowski", "Mazur", "Wojciechowski", "Kwiatkowski", "Krawczyk",
                    "Kaczmarek", "Piotrowski", "Grabowski"};

    private static final int MALE_SURNAMES_LENGTH = MALE_SURNAMES.length;

    public static String generateMaleSurname() {
        return MALE_SURNAMES[RANDOM.nextInt(MALE_SURNAMES_LENGTH)];
    }

}
