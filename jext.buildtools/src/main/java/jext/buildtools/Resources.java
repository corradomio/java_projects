package jext.buildtools;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public interface Resources {

    boolean isEmpty();
    int size();

    Set<Name> getRoots();

    List<Resource> getResources();
    List<Resource> getResources(Name root);

    void forEach(Consumer<Resource> consumer);
}
