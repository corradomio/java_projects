package jext.buildtools;

import java.util.List;
import java.util.Set;

public interface Resources {

    boolean isEmpty();
    int size();

    Set<Name> getRoots();

    List<Resource> getResources();
    List<Resource> getResources(Name root);
}
