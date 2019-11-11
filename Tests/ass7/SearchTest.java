package ass7;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class SearchTest {

    private static final InDirectorySearch IN_DIRECTORY_SEARCH = new InDirectorySearch();
    private static final InZipSearch IN_ZIP_SEARCH = new InZipSearch();
    private static final InJarSearch IN_JAR_SEARCH = new InJarSearch();

    private static final Path DIRECTORY_1 = Path.of("");
    private static final String ZIP_PATH = "data\\ass7\\utp-pointed.zip";
    private static final String JAR_PATH = "data\\ass7\\utp-pointed.jar";

    private static final String NAME_1 = "src";
    private static final String PATH_1 = "src";
    private static final File FILE_1 = new File(PATH_1);
    private static final String NAME_1_ZIP = "utp-pointed/src/";
    private static final String NAME_1_JAR = "src/";

    private static final String NAME_2 = "Main.java";
    private static final String PATH_2_1 = "src\\ass4\\Main.java";
    private static final String PATH_2_2 = "src\\ass5\\Main.java";
    private static final File FILE_2_1 = new File(PATH_2_1);
    private static final File FILE_2_2 = new File(PATH_2_2);
    private static final String NAME_2_1_ZIP = "utp-pointed/src/ass4/Main.java";
    private static final String NAME_2_2_ZIP = "utp-pointed/src/ass5/Main.java";
    private static final String NAME_2_1_JAR = "src/ass4/Main.java";
    private static final String NAME_2_2_JAR = "src/ass5/Main.java";

    private static final Path DIRECTORY_2 = Path.of("data");
    private static final String CONTENT_1 = "some text\nsome text";
    private static final String NAME_3 = "text.txt";
    private static final String PATH_3 = "data\\ass7\\text.txt";
    private static final File FILE_3 = new File(PATH_3);
    private static final String NAME_3_ZIP = "utp-pointed/data/ass7/text.txt";
    private static final String NAME_3_JAR = "data/ass7/text.txt";


    @Test
    public void findByNameInDirectory() {
        System.out.println("findByNameInDirectory():");

        List<File> entries = IN_DIRECTORY_SEARCH.findByName(DIRECTORY_1, NAME_1);
        Assert.assertEquals(1, entries.size());
        Assert.assertEquals(FILE_1, entries.get(0));
        Assert.assertEquals(NAME_1, entries.get(0).getName());
        Assert.assertEquals(PATH_1, entries.get(0).getPath());
        System.out.println(entries);

        entries = IN_DIRECTORY_SEARCH.findByName(DIRECTORY_1, NAME_2);
        Assert.assertEquals(2, entries.size());
        Assert.assertEquals(FILE_2_1, entries.get(0));
        Assert.assertEquals(FILE_2_2, entries.get(1));
        Assert.assertEquals(NAME_2, entries.get(0).getName());
        Assert.assertEquals(NAME_2, entries.get(1).getName());
        Assert.assertEquals(PATH_2_1, entries.get(0).getPath());
        Assert.assertEquals(PATH_2_2, entries.get(1).getPath());
        System.out.println(entries + "\n");
    }

    @Test
    public void findByContentInDirectory() {
        System.out.println("findByContentInDirectory():");

        List<File> entries = IN_DIRECTORY_SEARCH.findByContent(DIRECTORY_2, CONTENT_1);
        Assert.assertEquals(1, entries.size());
        Assert.assertEquals(FILE_3, entries.get(0));
        Assert.assertEquals(NAME_3, entries.get(0).getName());
        Assert.assertEquals(PATH_3, entries.get(0).getPath());
        System.out.println(entries);
    }

    @Test
    public void findByNameInZip() {
        ZipFile zip = null;
        try {
            zip = new ZipFile(ZIP_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(zip);

        System.out.println("findByNameInZip():");

        List<ZipEntry> entries = IN_ZIP_SEARCH.findByName(zip, NAME_1);
        Assert.assertEquals(1, entries.size());
        Assert.assertEquals(NAME_1_ZIP, entries.get(0).getName());
        System.out.println(entries);

        entries = IN_ZIP_SEARCH.findByName(zip, NAME_2);
        Assert.assertEquals(2, entries.size());
        Assert.assertEquals(NAME_2_1_ZIP, entries.get(0).getName());
        Assert.assertEquals(NAME_2_2_ZIP, entries.get(1).getName());
        System.out.println(entries + "\n");
    }

    @Test
    public void findByContentInZip() {

    }

    @Test
    public void findByNameInJar() {
        JarFile zip = null;
        try {
            zip = new JarFile(JAR_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(zip);

        System.out.println("findByNameInJar():");

        List<JarEntry> entries = IN_JAR_SEARCH.findByName(zip, NAME_1);
        Assert.assertEquals(1, entries.size());
        Assert.assertEquals(NAME_1_JAR, entries.get(0).getName());
        System.out.println(entries);

        entries = IN_JAR_SEARCH.findByName(zip, NAME_2);
        Assert.assertEquals(2, entries.size());
        Assert.assertEquals(NAME_2_1_JAR, entries.get(0).getName());
        Assert.assertEquals(NAME_2_2_JAR, entries.get(1).getName());
        System.out.println(entries + "\n");
    }

    @Test
    public void findByContentInJar() {

    }


}
