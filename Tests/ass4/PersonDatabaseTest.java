package ass4;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class PersonDatabaseTest {

    private static final Person P1 = new Person("Name8", "Surname8", LocalDate.of(2001, 1, 1));
    private static final Person P2 = new Person("Name7", "Surname7", LocalDate.of(2002, 1, 1));
    private static final Person P3 = new Person("Name7", "Surname7", LocalDate.of(2002, 1, 1));
    private static final Person P4 = new Person("Name7", "Surname6", LocalDate.of(2003, 1, 1));
    private static final Person P5 = new Person("Name5", "Surname2", LocalDate.of(2001, 1, 1));
    private static final Person P6 = new Person("Name4", "Surname2", LocalDate.of(2002, 1, 1));
    private static final Person P7 = new Person("Name2", "Surname2", LocalDate.of(2003, 1, 1));
    private static final Person P8 = new Person("Name2", "Surname2", LocalDate.of(2001, 1, 1));
    private static final Person P9 = new Person("Name1", "Surname1", LocalDate.of(2002, 1, 1));

    private static final List<Person> PEOPLE_1 = Arrays.asList(P1, P3, P5, P7);
    private static final List<Person> PEOPLE_2 = Arrays.asList(P2, P4, P6, P8);

    private static final List<Person> UNSORTED = Arrays.asList(P1, P2, P3, P4, P5, P6, P7, P8, P9);
    private static final List<Person> SORTED_BY_FIRST_NAME = Arrays.asList(P9, P7, P8, P6, P5, P2, P3, P4, P1);
    private static final List<Person> SORTED_BY_SURNAME_THEN_FIRST_NAME_THEN_BIRTH_DATE = Arrays.asList(P9, P8, P7, P6, P5, P4, P2, P3, P1);
    private static final List<Person> SORTED_BY_DATE = Arrays.asList(P1, P5, P8, P2, P3, P6, P9, P4, P7);

    private static PersonDatabase database;

    @Test
    public void constructor() {
        database = new PersonDatabase();
        Assert.assertEquals(0, database.size());

        database = new PersonDatabase(P1);
        Assert.assertEquals(1, database.size());

        database = new PersonDatabase(PEOPLE_1);
        Assert.assertEquals(4, database.size());
    }

    @Test
    public void addAll() {
        database = new PersonDatabase();
        database.addAll(PEOPLE_1);
        Assert.assertEquals(4, database.size());
        database.addAll(PEOPLE_2);
        Assert.assertEquals(8, database.size());
    }

    @Test
    public void add() {
        database = new PersonDatabase();
        database.add(P9);
        Assert.assertEquals(1, database.size());
        database.add(P9);
        Assert.assertEquals(2, database.size());
    }

    @Test
    public void notSorted() {
        database = new PersonDatabase(UNSORTED);
        List<Person> people = database.notSorted();
        Assert.assertNotNull(people);
        Assert.assertEquals(UNSORTED.size(), people.size());
        System.out.println("notSorted:");
        for (int i = 0; i < people.size(); ++i) {
            System.out.println(UNSORTED.get(i) + " vs " + people.get(i));
            Assert.assertEquals(UNSORTED.get(i).firstName(), people.get(i).firstName());
            Assert.assertEquals(UNSORTED.get(i).surname(), people.get(i).surname());
            Assert.assertEquals(UNSORTED.get(i).birthDate(), people.get(i).birthDate());
        }
    }

    @Test
    public void sortedByFirstName() {
        database = new PersonDatabase(UNSORTED);
        List<Person> people = database.sortedByFirstName();
        Assert.assertNotNull(people);
        Assert.assertEquals(SORTED_BY_FIRST_NAME.size(), people.size());
        System.out.println("sortedByFirstName:");
        for (int i = 0; i < people.size(); ++i) {
            System.out.println(SORTED_BY_FIRST_NAME.get(i) + " vs " + people.get(i));
            Assert.assertEquals(SORTED_BY_FIRST_NAME.get(i).firstName(), people.get(i).firstName());
            Assert.assertEquals(SORTED_BY_FIRST_NAME.get(i).surname(), people.get(i).surname());
            Assert.assertEquals(SORTED_BY_FIRST_NAME.get(i).birthDate(), people.get(i).birthDate());
        }
    }

    @Test
    public void sortedBySurnameFirstNameAndBirthDate() {
        database = new PersonDatabase(UNSORTED);
        List<Person> people = database.sortedBySurnameFirstNameAndBirthDate();
        Assert.assertNotNull(people);
        Assert.assertEquals(SORTED_BY_SURNAME_THEN_FIRST_NAME_THEN_BIRTH_DATE.size(), people.size());
        System.out.println("sortedBySurnameFirstNameAndBirthDate:");
        for (int i = 0; i < people.size(); ++i) {
            System.out.println(SORTED_BY_SURNAME_THEN_FIRST_NAME_THEN_BIRTH_DATE.get(i) + " vs " + people.get(i));
            Assert.assertEquals(SORTED_BY_SURNAME_THEN_FIRST_NAME_THEN_BIRTH_DATE.get(i).firstName(), people.get(i).firstName());
            Assert.assertEquals(SORTED_BY_SURNAME_THEN_FIRST_NAME_THEN_BIRTH_DATE.get(i).surname(), people.get(i).surname());
            Assert.assertEquals(SORTED_BY_SURNAME_THEN_FIRST_NAME_THEN_BIRTH_DATE.get(i).birthDate(), people.get(i).birthDate());
        }
    }

    @Test
    public void sortedByBirthDate() {
        database = new PersonDatabase(UNSORTED);
        List<Person> people = database.sortedByBirthDate();
        Assert.assertNotNull(people);
        Assert.assertEquals(SORTED_BY_DATE.size(), people.size());
        System.out.println("sortedByBirthDate:");
        for (int i = 0; i < people.size(); ++i) {
            System.out.println(SORTED_BY_DATE.get(i) + " vs " + people.get(i));
            Assert.assertEquals(SORTED_BY_DATE.get(i).firstName(), people.get(i).firstName());
            Assert.assertEquals(SORTED_BY_DATE.get(i).surname(), people.get(i).surname());
            Assert.assertEquals(SORTED_BY_DATE.get(i).birthDate(), people.get(i).birthDate());
        }
    }

    @Test
    public void bornOnDay() {
        database = new PersonDatabase(UNSORTED);
        List<Person> people1 = database.bornOnDay(LocalDate.of(2001, 1, 1));
        List<Person> people2 = database.bornOnDay(LocalDate.of(2002, 1, 1));
        List<Person> people3 = database.bornOnDay(LocalDate.of(2003, 1, 1));
        Assert.assertEquals(3, people1.size());
        Assert.assertEquals(4, people2.size());
        Assert.assertEquals(2, people3.size());
    }

}