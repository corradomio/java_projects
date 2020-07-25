package jext.buildtools;

import java.util.List;

public interface Resources {

    List<Name> getRoots();

    List<Resource> getResources();
    List<Resource> getResources(Name root);
}
