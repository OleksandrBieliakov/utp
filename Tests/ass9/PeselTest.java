package ass9;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;

public class PeselTest {

    // https://en.wikipedia.org/wiki/PESEL#Checksum_calculation

    private static final String INVALID_PESEL_FROM_WIKI = "44051401358";

    private static final String VALID_PESEL_1 = "01210391539";
    private static final Pesel PESEL_1 = new Pesel(VALID_PESEL_1);
    private static final LocalDate DATE_1 = LocalDate.parse("2001-01-03");
    private static final String ID_1 = "9153";
    private static final Sex SEX_1 = Sex.MALE;
    private static final String INVALID_PESEL_1 = "11210391539";

    private static final String VALID_PESEL_2 = "00123119322";
    private static final Pesel PESEL_2 = new Pesel(VALID_PESEL_2);
    private static final LocalDate DATE_2 = LocalDate.parse("1900-12-31");
    private static final String ID_2 = "1932";
    private static final Sex SEX_2 = Sex.FEMALE;
    private static final String INVALID_PESEL_2 = "0012311932";


    @Test
    public void isValidPesel() {
        try {
            Method isValidPesel = Pesel.class.getDeclaredMethod("isValidPesel", String.class);
            isValidPesel.setAccessible(true);
            Assert.assertFalse((boolean) isValidPesel.invoke(Pesel.class, INVALID_PESEL_FROM_WIKI));
            Assert.assertTrue((boolean) isValidPesel.invoke(Pesel.class, VALID_PESEL_1));
            Assert.assertFalse((boolean) isValidPesel.invoke(Pesel.class, INVALID_PESEL_1));
            Assert.assertTrue((boolean) isValidPesel.invoke(Pesel.class, VALID_PESEL_2));
            Assert.assertFalse((boolean) isValidPesel.invoke(Pesel.class, INVALID_PESEL_2));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void extractBirthDate() {
        try {
            Method extractBirthDate = Pesel.class.getDeclaredMethod("extractBirthDate");
            extractBirthDate.setAccessible(true);
            Assert.assertEquals(DATE_1, extractBirthDate.invoke(PESEL_1));
            Assert.assertEquals(DATE_2, extractBirthDate.invoke(PESEL_2));
            Assert.assertNotEquals(DATE_1, extractBirthDate.invoke(PESEL_2));
            Assert.assertNotEquals(DATE_2, extractBirthDate.invoke(PESEL_1));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void extractSex() {
        try {
            Method extractSex = Pesel.class.getDeclaredMethod("extractSex");
            extractSex.setAccessible(true);
            Assert.assertEquals(SEX_1, extractSex.invoke(PESEL_1));
            Assert.assertEquals(SEX_2, extractSex.invoke(PESEL_2));
            Assert.assertNotEquals(SEX_1, extractSex.invoke(PESEL_2));
            Assert.assertNotEquals(SEX_2, extractSex.invoke(PESEL_1));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void extractId() {
        try {
            Method extractId = Pesel.class.getDeclaredMethod("extractId");
            extractId.setAccessible(true);
            Assert.assertEquals(ID_1, extractId.invoke(PESEL_1));
            Assert.assertEquals(ID_2, extractId.invoke(PESEL_2));
            Assert.assertNotEquals(ID_1, extractId.invoke(PESEL_2));
            Assert.assertNotEquals(ID_2, extractId.invoke(PESEL_1));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
