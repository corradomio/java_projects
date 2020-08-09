package jext.buildtools;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public interface Resources {

    boolean isEmpty();
    int size();

    Set<Name> getRoots();

    List<Resource> getResources();
    List<Resource> getResources(Name root);

    void setIncludes(Collection<String> includes);
    void setExcludes(Collection<String> excludes);

    void forEach(Consumer<Resource> consumer);
}
