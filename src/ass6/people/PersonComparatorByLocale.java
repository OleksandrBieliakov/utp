package ass6.people;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

public class PersonComparatorByLocale implements Comparator<Person> {

    private Collator collator;

    public PersonComparatorByLocale(Locale locale) {
        collator = Collator.getInstance(locale);
    }

    @Override
    public int compare(Person person1, Person person2) {
        int result = collator.compare(person1.getSurname(), person2.getSurname());
        if (result != 0) return result;
        return collator.compare(person1.getName(), person2.getName());
    }

}
