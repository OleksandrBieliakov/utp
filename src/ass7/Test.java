package ass7;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    private static final InDirectorySearch IN_DIRECTORY_SEARCH = new InDirectorySearch();
    private static final Path DIRECTORY_2 = Path.of("data");
    private static final String CONTENT_1 = "some text\nsome text";


    public static void main (String[] args) {

        List<File> entries = IN_DIRECTORY_SEARCH.findByContent(DIRECTORY_2, CONTENT_1);
        System.out.println(entries);

        String string = "";
        try {
            string = Files.lines(Path.of("data/ass7/text.txt")).collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(string.equals(CONTENT_1));

    }

}
