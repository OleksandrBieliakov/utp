package ass6.generators;

import java.time.LocalDate;
import java.util.Formatter;
import java.util.Random;

// What is PESEL and its format: https://en.wikipedia.org/wiki/PESEL
public class peselGenerator {

    private static final int YEARS_IN_CENTURY = 100;

    private static final int ID_LENGTH = 3;
    private static final int ID_DIGIT_RANGE = 10;

    private static final int[] MALE_ODD = {1, 2, 3, 4, 5};
    private static final int[] FEMALE_EVEN = {0, 2, 4, 6, 8};
    private static final int SEX_LENGTH = MALE_ODD.length;
    private static final int FEMALE = 0;

    private static final int[] MULTIPLIERS = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
    private static final int LENGTH = MULTIPLIERS.length;

    private static final Random RANDOM = new Random();

    private static String birthDatePart(LocalDate birthDate) {
        int year = birthDate.getYear();
        int month = birthDate.getMonthValue() + 1;
        int day = birthDate.getDayOfMonth();
        int century = year / YEARS_IN_CENTURY + 1;
        year = year % YEARS_IN_CENTURY;

        switch (century) {
            case 19:
                month += 80;
                break;
            case 20:
                break;
            case 21:
                month += 20;
                break;
            case 22:
                month += 40;
                break;
            case 23:
                month += 60;
                break;
        }

        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        String format = "%02d";
        formatter.format(format, year);
        formatter.format(format, month);
        formatter.format(format, day);
        System.out.println(sb.toString());
        return sb.toString();
    }

    private static String idPart() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ID_LENGTH; i++)
            sb.append(RANDOM.nextInt(ID_DIGIT_RANGE));
        return sb.toString();
    }

    private static String sexPart(int sex) {
        return sex == FEMALE ? "" + FEMALE_EVEN[RANDOM.nextInt(SEX_LENGTH)] : "" + MALE_ODD[RANDOM.nextInt(SEX_LENGTH)];
    }

    private static String checkDigitPart(String peselParts) {
        int sum = 0;
        char[] digits = peselParts.toCharArray();
        for (int i = 0; i < LENGTH; i++)
            sum = +Integer.parseInt(digits[i] + "") * MULTIPLIERS[i];
        String sumString = sum + "";
        String sumLastDigitString = sumString.substring(sumString.length() - 1);
        int sumLastDigit = Integer.parseInt(sumLastDigitString);
        if (sumLastDigit == 0) return "0";
        return 10 - sumLastDigit + "";
    }

    /**
     * @param sex If == 0 FEMALE else MALE
     */
    private static String generatePesel(LocalDate birthDate, int sex) {
        StringBuilder sb = new StringBuilder();
        sb.append(birthDatePart(birthDate));
        sb.append(idPart());
        sb.append(sexPart(sex));
        sb.append(checkDigitPart(sb.toString()));
        return sb.toString();
    }

    public static String generateMalePesel(LocalDate birthDate) {
        return generatePesel(birthDate, 1);
    }

    public static String generateFemalePesel(LocalDate birthDate) {
        return generatePesel(birthDate, 0);
    }

    public static boolean checkPesel(String pesel) {
        int len = pesel.length();
        if (len != LENGTH + 1) return false;
        return pesel.substring(len - 1).equals(checkDigitPart(pesel.substring(0, len - 1)));
    }

}
