package jext.buildtools;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public interface Libraries {

    boolean isEmpty();
    int size();

    Set<Name> getRoots();

    List<Library> getLibraries();
    List<Library> getLibraries(Name root);

    void forEach(Consumer<Library> consumer);
}
