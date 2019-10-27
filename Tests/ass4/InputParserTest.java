package ass4;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public final class InputParserTest {

    private static final String PERSON_1_LINE = "Rachel Green 1967-05-06";
    private static final String PERSON_1_NAME = "Rachel", PERSON_1_SURNAME = "Green";
    private static final LocalDate PERSON_1_BIRTH_DATE = LocalDate.of(1967, 5, 6);

    private static final String PERSON_2_LINE = "Chandler    Bing        2003-11-08";
    private static final String PERSON_2_NAME = "Chandler", PERSON_2_SURNAME = "Bing";
    private static final LocalDate PERSON_2_BIRTH_DATE = LocalDate.of(2003, 11, 8);

    private static final File FILE = new File("data/ass4/inputParserTestData");
    private static final File FILE_NOT_EXISTING = new File("data/ass4/inputParserTestDataNotExisting");

    @Test
    public void parsePerson() {
        Person parsedPerson = InputParser.parsePerson(PERSON_1_LINE);
        Assert.assertNotNull(parsedPerson);
        Assert.assertEquals(PERSON_1_NAME, parsedPerson.firstName());
        Assert.assertEquals(PERSON_1_SURNAME, parsedPerson.surname());
        Assert.assertEquals(PERSON_1_BIRTH_DATE, parsedPerson.birthDate());

        parsedPerson = InputParser.parsePerson(PERSON_2_LINE);
        Assert.assertNotNull(parsedPerson);
        Assert.assertEquals(PERSON_2_NAME, parsedPerson.firstName());
        Assert.assertEquals(PERSON_2_SURNAME, parsedPerson.surname());
        Assert.assertEquals(PERSON_2_BIRTH_DATE, parsedPerson.birthDate());
    }

    @Test
    public void parse() {
        List<Person> parsedPeople = InputParser.parse(FILE_NOT_EXISTING);
        Assert.assertNull(parsedPeople);

        parsedPeople = InputParser.parse(FILE);
        Assert.assertNotNull(parsedPeople);
        Assert.assertEquals(2, parsedPeople.size());

        Person parsedPerson = parsedPeople.get(0);
        Assert.assertNotNull(parsedPerson);
        Assert.assertEquals(PERSON_1_NAME, parsedPerson.firstName());
        Assert.assertEquals(PERSON_1_SURNAME, parsedPerson.surname());
        Assert.assertEquals(PERSON_1_BIRTH_DATE, parsedPerson.birthDate());

        parsedPerson = parsedPeople.get(1);
        Assert.assertNotNull(parsedPerson);
        Assert.assertEquals(PERSON_2_NAME, parsedPerson.firstName());
        Assert.assertEquals(PERSON_2_SURNAME, parsedPerson.surname());
        Assert.assertEquals(PERSON_2_BIRTH_DATE, parsedPerson.birthDate());
    }

}