package ass7;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;

public class InJarSearch implements Search<JarFile, JarEntry, JarEntry> {

    @Override
    public List<JarEntry> findByName(JarFile archive, String name) {
        Predicate<JarEntry> hasTheName = entry -> new File(entry.getName()).getName().equals(name);
        return archive.stream().
                filter(hasTheName).
                collect(Collectors.toList());
    }

    @Override
    public List<JarEntry> findByContent(JarFile archive, String content) {
        Predicate<JarEntry> isDirectory = ZipEntry::isDirectory;
        Predicate<JarEntry> notArchive = this::notArchive;
        Predicate<JarEntry> hasTheContent = entry -> {
            System.out.println(entry);
            try (InputStream in = new BufferedInputStream(archive.getInputStream(entry))) {
                StringBuilder sb = new StringBuilder();
                byte[] bytes = in.readAllBytes();
                for(byte b : bytes)
                    sb.append((char) b);
                System.out.println(sb.toString() + "\n" + content);
                System.out.println(sb.toString().equals(content));
                return sb.toString().equals(content);
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
