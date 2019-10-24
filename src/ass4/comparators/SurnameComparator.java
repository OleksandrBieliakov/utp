package ass4.comparators;

import ass4.Person;

import java.util.Comparator;

public class SurnameComparator implements Comparator<Person> {

    @Override
    public int compare(Person person1, Person person2) {
        return person1.surname().compareTo(person2.surname());
    }

}
