package ass4.comparators;

import ass4.Person;

import java.util.Comparator;

public final class BirthDateComparator implements Comparator<Person> {

    @Override
    public int compare(Person person1, Person person2) {
        return person1.birthDate().compareTo(person2.birthDate());
    }

}