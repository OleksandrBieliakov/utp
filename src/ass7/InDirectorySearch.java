package ass7;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InDirectorySearch implements Search<Path, File, Path> {

    @Override
    public List<File> findByName(Path path, String name) {
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

    @Override
    public List<File> findByContent(Path path, String content) {
        Predicate<Path> isRegularFile = Files::isRegularFile;
        Predicate<Path> notArchive = this::notArchive;
        Predicate<Path> hasTheContent = p -> {
            try {
                return Files.lines(p).collect(Collectors.joining("\n")).equals(content);
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
