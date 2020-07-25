package jext.buildtools;

import java.util.List;

public interface Libraries {

    List<Name> getRoots();

    List<Library> getLibraries();
    List<Library> getLibraries(Name root);
}
