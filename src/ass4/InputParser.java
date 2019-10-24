package ass4;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class InputParser {

    // 1. Use regular expressions (Pattern) for validating input data
    //
    // 2. Convert input string representing date using SimpleDateFormat "yyyy-MM-dd"

    private static final String NAME_REGEX = "(?<name>[A-Z][a-z]*)";
    private static final String SURNAME_REGEX = "(?<surname>[A-Z][a-z]*)";
    private static final String YEAR_REGEX = "(?<year>19[0-9][0-9]|20[0-9][0-9])";
    private static final String MONTH_REGEX = "(?<month>0[1-9]|1[0-2])";
    private static final String DAY_REGEX = "(?<day>0[1-9]|[12][0-9]|3[01])";
    private static final String DATE_REGEX = "(?<date>" + YEAR_REGEX + "-" + MONTH_REGEX + "-" + DAY_REGEX + ")";
    private static final String PERSON_REGEX = NAME_REGEX + "\\s+" + SURNAME_REGEX + "\\s+" + DATE_REGEX;

    private static final Pattern PERSON_PATTERN = Pattern.compile(PERSON_REGEX);

    static Person parsePerson(String line) {
        Matcher matcher = PERSON_PATTERN.matcher(line);
        if (!matcher.matches()) return null;
        return new Person(matcher.group("name"), matcher.group("surname"), LocalDate.parse(matcher.group("date")));
    }

    static List<Person> parse(File file) {
        List<Person> people = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            Person person;
            while ((line = reader.readLine()) != null) {
                person = parsePerson(line);
                if (person != null) people.add(person);
            }
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(people.size() == 0) return null;
        return people;
    }

}