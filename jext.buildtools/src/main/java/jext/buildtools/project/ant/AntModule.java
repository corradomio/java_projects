package jext.buildtools.project.ant;

import jext.buildtools.Project;
import jext.buildtools.maven.MavenCoords;
import jext.buildtools.project.ant.util.IvyFile;
import jext.buildtools.project.BaseModule;
import jext.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AntModule extends BaseModule {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private List<IvyFile> ivyFiles;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public AntModule(File moduleDir, Project project) {
        super(moduleDir, project);
        findIvyFile();
    }

    private void findIvyFile() {
        ivyFiles = FileUtils.asList(moduleDir.listFiles((dir, name) -> name.startsWith("ivy")))
                .stream()
                .map(IvyFile::new)
                .collect(Collectors.toList());
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public List<MavenCoords> getMavenLibraries(){
        if (ivyFiles.isEmpty())
            return Collections.emptyList();

        List<MavenCoords> coords = new ArrayList<>();
        ivyFiles.forEach(ivyFile -> {
            coords.addAll(ivyFile.getDependencyCoords());
        });
        return coords;
    }
}