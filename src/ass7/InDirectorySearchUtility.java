package ass7;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InDirectorySearchUtility {

    private InDirectorySearchUtility() {
    }

    public static List<File> findByName(Path path, String name) {
        Predicate<Path> hasTheName = p -> p.getFileName().toString().equals(name);
        List<File> entries = null;
        try {
            entries = Files.walk(path).
                    filter(hasTheName).
                    map(p -> new File(p.toString())).
                    collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entries;
    }

    public static List<File> findByContent(Path path, String content) {
        Predicate<Path> isRegularFile = Files::isRegularFile;
        Predicate<Path> notArchive = p -> ExtensionCheckerUtility.notArchive(p.toString());
        Predicate<Path> hasTheContent = p -> {
            try (InputStream in = new FileInputStream(p.toFile())) {
                return ContentSearchUtility.containsText(in, content);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        };
        List<File> entries = null;
        try {
            entries = Files.walk(path).
                    filter(isRegularFile.and(notArchive).and(hasTheContent)).
                    map(p -> new File(p.toString())).
                    collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entries;
    }

}
