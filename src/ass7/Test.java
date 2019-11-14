package ass7;

import java.io.IOException;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Test {

    private static final String CONTENT_1 = "some text\r\nsome text";
    private static final String JAR_PATH_2 = "data\\ass7\\data.jar";

    public static void main(String[] args) {

        JarFile jarFile = null;
        try {
            jarFile = new JarFile(JAR_PATH_2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<JarEntry> entries = InJarSearchUtility.findByContent(jarFile, CONTENT_1);
        System.out.println(entries);

    }

}
