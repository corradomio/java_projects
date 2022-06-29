package jext.sourcecode.project.java.ant;

import jext.sourcecode.project.Project;
import jext.sourcecode.project.java.JavaBaseModule;
import jext.sourcecode.project.java.ant.util.IvyFile;
import jext.util.FileUtils;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class AntModule extends JavaBaseModule {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private List<IvyFile> ivyFiles;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    AntModule(File moduleHome, Project project) {
        super(moduleHome, project);
        findIvyFile();
    }

    private void findIvyFile() {
        ivyFiles = FileUtils.asList(getModuleHome().listFiles((dir, name) -> name.startsWith("ivy")))
            .stream()
            .map(IvyFile::new)
            .collect(Collectors.toList());
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}