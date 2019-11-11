package ass7;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public interface Search<TContainer, TEntry, TChecked> {

    List<String> ARCHIVE_EXTENSIONS = new LinkedList<>(Arrays.asList(".zip", ".tar", ".jar", ".rar", ".7z"));

    List<TEntry> findByName(TContainer container, String name);

    List<TEntry> findByContent(TContainer container, String content);

    default boolean notArchive(TChecked entry) {
        for (String extension : ARCHIVE_EXTENSIONS)
            if (entry.toString().endsWith(extension)) return false;
        return true;
    }

}
