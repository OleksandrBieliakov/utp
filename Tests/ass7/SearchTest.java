package ass7;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class SearchTest {

    private static final InDirectorySearch IN_DIRECTORY_SEARCH = new InDirectorySearch();
    private static final InZipSearch IN_ZIP_SEARCH = new InZipSearch();
    private static final InJarSearch IN_JAR_SEARCH = new InJarSearch();

    private static final Path DIRECTORY = Path.of("D:/Projects/utp-pointed");
    private static final String ZIP_PATH = "D:\\Projects\\utp-pointed\\data\\ass7\\utp-pointed.zip";


    private static final String NAME_1 = "src";
    private static final String PATH_1 = "D:\\Projects\\utp-pointed\\src";


    private static final String NAME_2 = "Main.java";
    private static final String PATH_2_1 = "D:\\Projects\\utp-pointed\\src\\ass4\\Main.java";
    private static final String PATH_2_2 = "D:\\Projects\\utp-pointed\\src\\ass5\\Main.java";

    @Test
    public void findByNameInDirectory() {
        System.out.println("findByNameInDirectory():");

        List<File> entries = IN_DIRECTORY_SEARCH.findByName(DIRECTORY, NAME_1);
        Assert.assertEquals(1, entries.size());
        Assert.assertEquals(NAME_1, entries.get(0).getName());
        Assert.assertEquals(PATH_1, entries.get(0).getPath());
        System.out.println(entries);

        entries = IN_DIRECTORY_SEARCH.findByName(DIRECTORY, NAME_2);
        Assert.assertEquals(2, entries.size());
        Assert.assertEquals(NAME_2, entries.get(0).getName());
        Assert.assertEquals(NAME_2, entries.get(1).getName());
        Assert.assertEquals(PATH_2_1, entries.get(0).getPath());
        Assert.assertEquals(PATH_2_2, entries.get(1).getPath());
        System.out.println(entries + "\n");
    }

    @Test
    public void findByContentInDirectory() {

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
        Assert.assertEquals(NAME_1, new File(entries.get(0).getName()).getName());
        System.out.println(entries);

        entries = IN_ZIP_SEARCH.findByName(zip, NAME_2);
        Assert.assertEquals(2, entries.size());
        /*
        Assert.assertEquals(NAME_2, entries.get(0).getName());
        Assert.assertEquals(NAME_2, entries.get(1).getName());
         */
        System.out.println(entries + "\n");

    }

    @Test
    public void findByContentInZip() {

    }

    @Test
    public void findByNameInJar() {

    }

    @Test
    public void findByContentInJar() {

    }


}
