package jext.sourcecode.project;

import jext.name.Name;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public interface Sources extends List<Source> {

    Source getSource(Name name);
    Source getSource(String nameOrId);

    List<File> getSourceFiles();

    Set<String> getSourceRoots();
    List<File>  getSourceRootDirectories();

}
