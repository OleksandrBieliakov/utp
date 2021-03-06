package ass7;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class SearchTest {

    private static final Path DIRECTORY_1 = Path.of("");
    private static final String ZIP_PATH = "data\\ass7\\utp-pointed.zip";
    private static final String JAR_PATH = "data\\ass7\\utp-pointed.jar";
    private static final String JAR_PATH_2 = "data\\ass7\\data.jar";
    private static final String ZIP_PATH_2 = "data\\ass7\\data.zip";

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
    private static final String NAME_3_1 = "text.txt";
    private static final String NAME_3_2 = "text2.txt";
    private static final String PATH_3_1 = "data\\ass7\\text.txt";
    private static final String PATH_3_2 = "data\\ass7_2\\text2.txt";
    private static final File FILE_3_1 = new File(PATH_3_1);
    private static final File FILE_3_2 = new File(PATH_3_2);
    private static final String NAME_3_1_ZIP = "data/ass7/text.txt";
    private static final String NAME_3_2_ZIP = "data/ass7_2/text2.txt";
    private static final String NAME_3_1_JAR = "ass7/text.txt";
    private static final String NAME_3_2_JAR = "ass7_2/text2.txt";

    private static String CONTENT_1 = getContent();

    private static String getContent() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(PATH_3_1)));
            StringBuilder sb = new StringBuilder();
            int next;
            while ((next = br.read()) != -1) {
                sb.append((char) next);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void findByNameInDirectory() {
        System.out.println("findByNameInDirectory():");

        double start = System.currentTimeMillis();
        List<File> entries = InDirectorySearchUtility.findByName(DIRECTORY_1, NAME_1);
        System.out.println("time (ms): " + (System.currentTimeMillis() - start));

        Assert.assertEquals(1, entries.size());
        Assert.assertEquals(FILE_1, entries.get(0));
        Assert.assertEquals(NAME_1, entries.get(0).getName());
        Assert.assertEquals(PATH_1, entries.get(0).getPath());
        System.out.println(entries);

        start = System.currentTimeMillis();
        entries = InDirectorySearchUtility.findByName(DIRECTORY_1, NAME_2);
        System.out.println("time (ms): " + (System.currentTimeMillis() - start));

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

        double start = System.currentTimeMillis();
        List<File> entries = InDirectorySearchUtility.findByContent(DIRECTORY_2, CONTENT_1);
        System.out.println("time (ms): " + (System.currentTimeMillis() - start));

        Assert.assertEquals(2, entries.size());
        Assert.assertEquals(FILE_3_1, entries.get(0));
        Assert.assertEquals(FILE_3_2, entries.get(1));
        Assert.assertEquals(NAME_3_1, entries.get(0).getName());
        Assert.assertEquals(NAME_3_2, entries.get(1).getName());
        Assert.assertEquals(PATH_3_1, entries.get(0).getPath());
        Assert.assertEquals(PATH_3_2, entries.get(1).getPath());
        System.out.println(entries);
    }

    @Test
    public void findByNameInDirectoryParallel() {
        System.out.println("findByNameInDirectoryParallel():");

        double start = System.currentTimeMillis();
        List<File> entries = InDirectorySearchUtility.findByNameParallel(DIRECTORY_1, NAME_1);
        System.out.println("time (ms): " + (System.currentTimeMillis() - start));

        Assert.assertEquals(1, entries.size());
        Assert.assertEquals(FILE_1, entries.get(0));
        Assert.assertEquals(NAME_1, entries.get(0).getName());
        Assert.assertEquals(PATH_1, entries.get(0).getPath());
        System.out.println(entries);

        start = System.currentTimeMillis();
        entries = InDirectorySearchUtility.findByNameParallel(DIRECTORY_1, NAME_2);
        System.out.println("time (ms): " + (System.currentTimeMillis() - start));

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
    public void findByContentInDirectoryParallel() {
        System.out.println("findByContentInDirectoryParallel():");

        double start = System.currentTimeMillis();
        List<File> entries = InDirectorySearchUtility.findByContentParallel(DIRECTORY_2, CONTENT_1);
        System.out.println("time (ms): " + (System.currentTimeMillis() - start));

        Assert.assertEquals(2, entries.size());
        Assert.assertEquals(FILE_3_1, entries.get(0));
        Assert.assertEquals(FILE_3_2, entries.get(1));
        Assert.assertEquals(NAME_3_1, entries.get(0).getName());
        Assert.assertEquals(NAME_3_2, entries.get(1).getName());
        Assert.assertEquals(PATH_3_1, entries.get(0).getPath());
        Assert.assertEquals(PATH_3_2, entries.get(1).getPath());
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

        double start = System.currentTimeMillis();
        List<ZipEntry> entries = InZipSearchUtility.findByName(zip, NAME_1);
        System.out.println("time (ms): " + (System.currentTimeMillis() - start));

        Assert.assertEquals(1, entries.size());
        Assert.assertEquals(NAME_1_ZIP, entries.get(0).getName());
        System.out.println(entries);

        start = System.currentTimeMillis();
        entries = InZipSearchUtility.findByName(zip, NAME_2);
        System.out.println("time (ms): " + (System.currentTimeMillis() - start));

        Assert.assertEquals(2, entries.size());
        Assert.assertEquals(NAME_2_1_ZIP, entries.get(0).getName());
        Assert.assertEquals(NAME_2_2_ZIP, entries.get(1).getName());
        System.out.println(entries + "\n");
    }

    @Test
    public void findByContentInZip() {
        ZipFile zip = null;
        try {
            zip = new ZipFile(ZIP_PATH_2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(zip);

        System.out.println("findByContentInZip():");

        double start = System.currentTimeMillis();
        List<ZipEntry> entries = InZipSearchUtility.findByContent(zip, CONTENT_1);
        System.out.println("time (ms): " + (System.currentTimeMillis() - start));

        Assert.assertEquals(2, entries.size());
        Assert.assertEquals(NAME_3_1_ZIP, entries.get(0).getName());
        Assert.assertEquals(NAME_3_2_ZIP, entries.get(1).getName());
        System.out.println(entries + "\n");
    }

    @Test
    public void findByNameInZipParallel() {
        ZipFile zip = null;
        try {
            zip = new ZipFile(ZIP_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(zip);

        System.out.println("findByNameInZipParallel():");

        double start = System.currentTimeMillis();
        List<ZipEntry> entries = InZipSearchUtility.findByNameParallel(zip, NAME_1);
        System.out.println("time (ms): " + (System.currentTimeMillis() - start));

        Assert.assertEquals(1, entries.size());
        Assert.assertEquals(NAME_1_ZIP, entries.get(0).getName());
        System.out.println(entries);

        start = System.currentTimeMillis();
        entries = InZipSearchUtility.findByNameParallel(zip, NAME_2);
        System.out.println("time (ms): " + (System.currentTimeMillis() - start));

        Assert.assertEquals(2, entries.size());
        Assert.assertEquals(NAME_2_1_ZIP, entries.get(0).getName());
        Assert.assertEquals(NAME_2_2_ZIP, entries.get(1).getName());
        System.out.println(entries + "\n");
    }

    @Test
    public void findByContentInZipParallel() {
        ZipFile zip = null;
        try {
            zip = new ZipFile(ZIP_PATH_2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(zip);

        System.out.println("findByContentInZipParallel():");

        double start = System.currentTimeMillis();
        List<ZipEntry> entries = InZipSearchUtility.findByContentParallel(zip, CONTENT_1);
        System.out.println("time (ms): " + (System.currentTimeMillis() - start));

        Assert.assertEquals(2, entries.size());
        Assert.assertEquals(NAME_3_1_ZIP, entries.get(0).getName());
        Assert.assertEquals(NAME_3_2_ZIP, entries.get(1).getName());
        System.out.println(entries + "\n");
    }

    @Test
    public void findByNameInJar() {
        JarFile jar = null;
        try {
            jar = new JarFile(JAR_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(jar);

        System.out.println("findByNameInJar():");

        double start = System.currentTimeMillis();
        List<JarEntry> entries = InJarSearchUtility.findByName(jar, NAME_1);
        System.out.println("time (ms): " + (System.currentTimeMillis() - start));

        Assert.assertEquals(1, entries.size());
        Assert.assertEquals(NAME_1_JAR, entries.get(0).getName());
        System.out.println(entries);

        start = System.currentTimeMillis();
        entries = InJarSearchUtility.findByName(jar, NAME_2);
        System.out.println("time (ms): " + (System.currentTimeMillis() - start));

        Assert.assertEquals(2, entries.size());
        Assert.assertEquals(NAME_2_1_JAR, entries.get(0).getName());
        Assert.assertEquals(NAME_2_2_JAR, entries.get(1).getName());
        System.out.println(entries + "\n");
    }

    @Test
    public void findByContentInJar() {
        JarFile jar = null;
        try {
            jar = new JarFile(JAR_PATH_2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(jar);

        System.out.println("findByContentInJar():");

        double start = System.currentTimeMillis();
        List<JarEntry> entries = InJarSearchUtility.findByContent(jar, CONTENT_1);
        System.out.println("time (ms): " + (System.currentTimeMillis() - start));

        Assert.assertEquals(2, entries.size());
        Assert.assertEquals(NAME_3_1_JAR, entries.get(0).getName());
        Assert.assertEquals(NAME_3_2_JAR, entries.get(1).getName());
        System.out.println(entries + "\n");
    }

    @Test
    public void findByNameInJarParallel() {
        JarFile jar = null;
        try {
            jar = new JarFile(JAR_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(jar);

        System.out.println("findByNameInJarParallel():");

        double start = System.currentTimeMillis();
        List<JarEntry> entries = InJarSearchUtility.findByNameParallel(jar, NAME_1);
        System.out.println("time (ms): " + (System.currentTimeMillis() - start));

        Assert.assertEquals(1, entries.size());
        Assert.assertEquals(NAME_1_JAR, entries.get(0).getName());
        System.out.println(entries);

        start = System.currentTimeMillis();
        entries = InJarSearchUtility.findByNameParallel(jar, NAME_2);
        System.out.println("time (ms): " + (System.currentTimeMillis() - start));

        Assert.assertEquals(2, entries.size());
        Assert.assertEquals(NAME_2_1_JAR, entries.get(0).getName());
        Assert.assertEquals(NAME_2_2_JAR, entries.get(1).getName());
        System.out.println(entries + "\n");
    }

    @Test
    public void findByContentInJarParallel() {
        JarFile jar = null;
        try {
            jar = new JarFile(JAR_PATH_2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(jar);

        System.out.println("findByContentInJarParallel():");

        double start = System.currentTimeMillis();
        List<JarEntry> entries = InJarSearchUtility.findByContentParallel(jar, CONTENT_1);
        System.out.println("time (ms): " + (System.currentTimeMillis() - start));

        Assert.assertEquals(2, entries.size());
        Assert.assertEquals(NAME_3_1_JAR, entries.get(0).getName());
        Assert.assertEquals(NAME_3_2_JAR, entries.get(1).getName());
        System.out.println(entries + "\n");
    }


}
