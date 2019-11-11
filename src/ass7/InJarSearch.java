package ass7;

import java.util.List;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class InJarSearch implements Search<JarFile, JarEntry> {

    @Override
    public List<JarEntry> findByName(JarFile archive, String name) {
        Predicate<JarEntry> hasTheName = entry -> entry.getName().equals(name);
        return archive.stream().filter(hasTheName).collect(Collectors.toList());
    }

    @Override
    public List<JarEntry> findByContent(JarFile archive, String content) {
        return null;
    }

}
