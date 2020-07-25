package jext.buildtools;

import java.util.List;
import java.util.Set;

public interface Libraries {

    boolean isEmpty();
    int size();

    Set<Name> getRoots();

    List<Library> getLibraries();
    List<Library> getLibraries(Name root);
}
