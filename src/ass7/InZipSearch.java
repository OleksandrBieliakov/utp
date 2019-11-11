package ass7;

import java.io.File;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class InZipSearch implements Search<ZipFile, ZipEntry> {

    @Override
    public List<ZipEntry> findByName(ZipFile archive, String name) {
        Predicate<ZipEntry> hasTheName = entry -> new File(entry.getName()).getName().equals(name);
        return archive.stream().filter(hasTheName).collect(Collectors.toList());
    }

    @Override
    public List<ZipEntry> findByContent(ZipFile archive, String content) {
        return null;
    }

}
