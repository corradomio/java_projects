package jext.sourcecode.project;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Sources extends ArrayList<Source> {

    Set<String> getSourceRoots() {
        return Collections.emptySet();
    }

    List<File> getSourceRootDirectories() {
        return Collections.emptyList();
    }

}
