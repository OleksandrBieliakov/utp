package ass9;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pesel {

    //https://en.wikipedia.org/wiki/PESEL#Checksum_calculation

    private static final int[] MULTIPLIERS = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
    private static final int MULTIPLIERS_LENGTH = 10;
    private static final int PESEL_LENGTH = 11;

    private static final int YEAR_START_INDEX = 0;
    private static final int YEAR_END_INDEX = 2;
    private static final int MONTH_START_INDEX = 2;
    private static final int MONTH_END_INDEX = 4;
    private static final int DAY_START_INDEX = 4;
    private static final int DAY_END_INDEX = 6;
    private static final int ID_START_INDEX = 6;
    private static final int ID_END_INDEX = 10;
    private static final int SEX_INDEX = 9;
    private static final int CHECK_INDEX = 10;

    private String pesel;

    Pesel(String pesel) throws IllegalArgumentException {
        if (!isValidPesel(pesel)) throw new IllegalArgumentException("Invalid PESEL");
        this.pesel = pesel;
    }

    public String getPesel() {
        return pesel;
    }

    private static boolean isValidPesel(String pesel) {
        char[] digits = pesel.toCharArray();
        if (digits.length != PESEL_LENGTH) return false;

        int sum = 0;
        for (int i = 0; i < MULTIPLIERS_LENGTH; i++) {
            try {
                sum += Integer.parseInt(digits[i] + "") * MULTIPLIERS[i];
            } catch (NumberFormatException e) {
                return false;
            }
        }

        int checkDigit;
        try {
            checkDigit = Integer.parseInt(pesel.substring(CHECK_INDEX));
        } catch (NumberFormatException e) {
            return false;
        }

        int sumLastDigit = sum % 10;
        int checksum = sumLastDigit == 0 ? 0 : 10 - sumLastDigit;

        return checksum == checkDigit;
    }

    private LocalDate extractBirthDate() {
        String year = pesel.substring(YEAR_START_INDEX, YEAR_END_INDEX);
        String month = pesel.substring(MONTH_START_INDEX, MONTH_END_INDEX);
        String day = pesel.substring(DAY_START_INDEX, DAY_END_INDEX);

        int monthNum = Integer.parseInt(month);

        int century;
        if (monthNum - 80 > 0) {
            century = 19;
            monthNum -= 80;
        } else if (monthNum - 60 > 0) {
            century = 23;
            monthNum -= 60;
        } else if (monthNum - 40 > 0) {
            century = 22;
            monthNum -= 40;
        } else if (monthNum - 20 > 0) {
            century = 21;
            monthNum -= 20;
        } else {
            century = 20;
        }

        year = (century - 1) + year;

        return LocalDate.parse(year + String.format("-%02d-", monthNum) + day);
    }

    private Sex extractSex() {
        int sexDigit = Integer.parseInt(pesel.charAt(SEX_INDEX) + "");
        return sexDigit % 2 == 0 ? Sex.FEMALE : Sex.MALE;
    }

    private String extractId() {
        return pesel.substring(ID_START_INDEX, ID_END_INDEX);
    }

    @Override
    public String toString() {
        return pesel + ": " + extractBirthDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +
                " id:" + extractId() + " " + extractSex();
    }

}
