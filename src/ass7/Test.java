package ass7;

import java.io.IOException;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Test {

    private static final String CONTENT_1 = "some text\nsome text";
    private static final String JAR_PATH = "data\\ass7\\utp-pointed.jar";
    private static final InJarSearch IN_JAR_SEARCH = new InJarSearch();

    public static void main (String[] args) {

        JarFile zip = null;
        try {
            zip = new JarFile(JAR_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<JarEntry> entries = IN_JAR_SEARCH.findByContent(zip, CONTENT_1);
        System.out.println(entries);

    }

}
