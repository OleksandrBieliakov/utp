package ass7;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class InZipSearchUtility {

    public static List<ZipEntry> findByName(ZipFile archive, String name) {
        Predicate<ZipEntry> hasTheName = entry -> new File(entry.getName()).getName().equals(name);
        return archive.stream().
                filter(hasTheName).
                collect(Collectors.toList());
    }

    public static List<ZipEntry> findByContent(ZipFile archive, String content) {
        Predicate<ZipEntry> isDirectory = ZipEntry::isDirectory;
        Predicate<ZipEntry> notArchive = entry -> ExtensionCheckerUtility.notArchive(entry.toString());
        Predicate<ZipEntry> hasTheContent = entry -> {
            try (InputStream in = new BufferedInputStream(archive.getInputStream(entry))) {
                return ContentSearchUtility.containsText(in, content);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        };
        return archive.stream().
                filter(isDirectory.negate().and(notArchive).and(hasTheContent)).
                collect(Collectors.toList());
    }

}
