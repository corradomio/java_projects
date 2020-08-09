package jext.buildtools;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public interface Sources {

    boolean isEmpty();
    int size();

    Set<Name> getRoots();

    List<Source> getSources();
    List<Source> getSources(Name root);

    void setIncludes(Collection<String> includes);
    void setExcludes(Collection<String> excludes);

    void forEach(Consumer<Source> consumer);
}
