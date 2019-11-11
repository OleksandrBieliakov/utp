package ass7;

import java.util.List;

public interface Search<TContainer, TEntry> {

    List<TEntry> findByName(TContainer container, String name);

    List<TEntry> findByContent(TContainer container, String content);

}
