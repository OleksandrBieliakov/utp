package ass7;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;

public class InJarSearchUtility {

    private InJarSearchUtility() {
    }

    public static List<JarEntry> findByName(JarFile archive, String name) {
        Predicate<JarEntry> hasTheName = entry -> new File(entry.getName()).getName().equals(name);
        return archive.stream().
                filter(hasTheName).
                collect(Collectors.toList());
    }

    public static List<JarEntry> findByContent(JarFile archive, String content) {
        Predicate<JarEntry> isDirectory = JarEntry::isDirectory;
        Predicate<JarEntry> notArchive = entry -> ExtensionCheckerUtility.notArchive(entry.toString());
        Predicate<JarEntry> hasTheContent = entry -> {
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
