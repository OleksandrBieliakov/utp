package ass4;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class Main {

    private static void printList(List<Person> people) {
        people.forEach(System.out::println);
    }

    public static void main(String[] args) {

        PersonDatabase database = new PersonDatabase(InputParser.parse(new File("data/ass4/mainInput")));
        List<Person> notSorted = database.notSorted();
        List<Person> sortedByFirstName = database.sortedByFirstName();
        List<Person> sortedBySurnameFirstNameAndBirthDate = database.sortedBySurnameFirstNameAndBirthDate();
        List<Person> sortedByBirthDate = database.sortedByBirthDate();
        List<Person> peopleBornOn_2003_11_08 = database.bornOnDay(LocalDate.of(2003, 11, 8));

        System.out.println("- - - notSorted:");
        printList(notSorted);

        System.out.println("\n\n- - - sortedByFirstName:");
        printList(sortedByFirstName);

        System.out.println("\n\n- - - sortedBySurnameFirstNameAndBirthDate:");
        printList(sortedBySurnameFirstNameAndBirthDate);

        System.out.println("\n\n- - - sortedByBirthDate:");
        printList(sortedByBirthDate);

        System.out.println("\n\n- - - peopleBornOn2002_01_01:");
        printList(peopleBornOn_2003_11_08);

    }

}
