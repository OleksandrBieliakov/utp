package ass4;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.time.LocalDate;

public class PersonTest {

    private static final String PATH = "data\\ass8\\people";
    private static final Person PERSON = new Person("Name1", "Surname1", LocalDate.of(2002, 1, 1));

    @Test
    public void serializeAndDeserialize() {
        Person person = null;
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(PATH));
             DataInputStream in = new DataInputStream(new FileInputStream(PATH))) {
            PERSON.serialize(out);
            person = Person.deserialize(in);
        } catch (IOException | Assignment08Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(PERSON, person);
        System.out.println(person);
    }

}
