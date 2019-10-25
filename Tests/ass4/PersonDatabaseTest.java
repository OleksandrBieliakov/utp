package ass4;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class PersonDatabaseTest {

    private static final Person p1 = new Person("Name8", "Surname8", LocalDate.of(2001, 1, 1));
    private static final Person p2 = new Person("Name7", "Surname7", LocalDate.of(2002, 1, 1));
    private static final Person p3 = new Person("Name7", "Surname7", LocalDate.of(2002, 1, 1));
    private static final Person p4 = new Person("Name7", "Surname6", LocalDate.of(2003, 1, 1));
    private static final Person p5 = new Person("Name5", "Surname2", LocalDate.of(2001, 1, 1));
    private static final Person p6 = new Person("Name4", "Surname2", LocalDate.of(2002, 1, 1));
    private static final Person p7 = new Person("Name2", "Surname2", LocalDate.of(2003, 1, 1));
    private static final Person p8 = new Person("Name2", "Surname2", LocalDate.of(2001, 1, 1));
    private static final Person p9 = new Person("Name1", "Surname1", LocalDate.of(2002, 1, 1));

    private static final List<Person> people1 = Arrays.asList(p1, p3, p5, p7);
    private static final List<Person> people2 = Arrays.asList(p2, p4, p6, p8);

    private static final List<Person> unsorted = Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9);
    private static final List<Person> sortedByFirstName = Arrays.asList(p9, p7, p8, p6, p5, p2, p3, p4, p1);
    private static final List<Person> sortedBySurnameThenFirstNameThenBirthDate = Arrays.asList(p9, p8, p7, p6, p5, p4, p2, p3, p1);
    private static final List<Person> sortedByDate = Arrays.asList(p1, p5, p8, p2, p3, p6, p9, p4, p7);

    private static PersonDatabase database;

    @Test
    public void constructor() {
        database = new PersonDatabase();
        Assert.assertEquals(0, database.size());

        database = new PersonDatabase(p1);
        Assert.assertEquals(1, database.size());

        database = new PersonDatabase(people1);
        Assert.assertEquals(4, database.size());
    }

    @Test
    public void addAll() {
        database = new PersonDatabase();
        database.addAll(people1);
        Assert.assertEquals(4, database.size());
        database.addAll(people2);
        Assert.assertEquals(8, database.size());
    }

    @Test
    public void add() {
        database = new PersonDatabase();
        database.add(p9);
        Assert.assertEquals(1, database.size());
        database.add(p9);
        Assert.assertEquals(2, database.size());
    }

    @Test
    public void notSorted() {
        database = new PersonDatabase(unsorted);
        List<Person> people = database.notSorted();
        Assert.assertNotNull(people);
        Assert.assertEquals(unsorted.size(), people.size());
        System.out.println("notSorted:");
        for (int i = 0; i < people.size(); ++i) {
            System.out.println(unsorted.get(i) + " vs " + people.get(i));
            Assert.assertEquals(unsorted.get(i).firstName(), people.get(i).firstName());
            Assert.assertEquals(unsorted.get(i).surname(), people.get(i).surname());
            Assert.assertEquals(unsorted.get(i).birthDate(), people.get(i).birthDate());
        }
    }

    @Test
    public void sortedByFirstName() {
        database = new PersonDatabase(unsorted);
        List<Person> people = database.sortedByFirstName();
        Assert.assertNotNull(people);
        Assert.assertEquals(sortedByFirstName.size(), people.size());
        System.out.println("sortedByFirstName:");
        for (int i = 0; i < people.size(); ++i) {
            System.out.println(sortedByFirstName.get(i) + " vs " + people.get(i));
            Assert.assertEquals(sortedByFirstName.get(i).firstName(), people.get(i).firstName());
            Assert.assertEquals(sortedByFirstName.get(i).surname(), people.get(i).surname());
            Assert.assertEquals(sortedByFirstName.get(i).birthDate(), people.get(i).birthDate());
        }
    }

    @Test
    public void sortedBySurnameFirstNameAndBirthDate() {
        database = new PersonDatabase(unsorted);
        List<Person> people = database.sortedBySurnameFirstNameAndBirthDate();
        Assert.assertNotNull(people);
        Assert.assertEquals(sortedBySurnameThenFirstNameThenBirthDate.size(), people.size());
        System.out.println("sortedBySurnameFirstNameAndBirthDate:");
        for (int i = 0; i < people.size(); ++i) {
            System.out.println(sortedBySurnameThenFirstNameThenBirthDate.get(i) + " vs " + people.get(i));
            Assert.assertEquals(sortedBySurnameThenFirstNameThenBirthDate.get(i).firstName(), people.get(i).firstName());
            Assert.assertEquals(sortedBySurnameThenFirstNameThenBirthDate.get(i).surname(), people.get(i).surname());
            Assert.assertEquals(sortedBySurnameThenFirstNameThenBirthDate.get(i).birthDate(), people.get(i).birthDate());
        }
    }

    @Test
    public void sortedByBirthDate() {
        database = new PersonDatabase(unsorted);
        List<Person> people = database.sortedByBirthDate();
        Assert.assertNotNull(people);
        Assert.assertEquals(sortedByDate.size(), people.size());
        System.out.println("sortedByBirthDate:");
        for (int i = 0; i < people.size(); ++i) {
            System.out.println(sortedByDate.get(i) + " vs " + people.get(i));
            Assert.assertEquals(sortedByDate.get(i).firstName(), people.get(i).firstName());
            Assert.assertEquals(sortedByDate.get(i).surname(), people.get(i).surname());
            Assert.assertEquals(sortedByDate.get(i).birthDate(), people.get(i).birthDate());
        }
    }

    @Test
    public void bornOnDay() {
        database = new PersonDatabase(unsorted);
        List<Person> people1 = database.bornOnDay(LocalDate.of(2001, 1, 1));
        List<Person> people2 = database.bornOnDay(LocalDate.of(2002, 1, 1));
        List<Person> people3 = database.bornOnDay(LocalDate.of(2003, 1, 1));
        Assert.assertEquals(3, people1.size());
        Assert.assertEquals(4, people2.size());
        Assert.assertEquals(2, people3.size());
    }

}