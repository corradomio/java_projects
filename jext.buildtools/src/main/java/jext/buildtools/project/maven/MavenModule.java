package jext.buildtools.project.maven;

import jext.buildtools.Project;
import jext.maven.MavenCoords;
import jext.maven.MavenPom;
import jext.buildtools.project.BaseModule;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class MavenModule extends BaseModule {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private MavenPom pom;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public MavenModule(File moduleDir, Project project) {
        super(moduleDir, project);
        this.pom = new MavenPom(moduleDir);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public String getId() {
        return pom.getCoords().toString();
    }

    public List<MavenCoords> getMavenLibraries() {
        return pom.getDependencyCoords()
                .stream()
                .filter(this::isLibrary)
                .collect(Collectors.toList());
    }

    private boolean isLibrary(MavenCoords coords) {
        return project.findModule(coords.toString()) == null;
    }

}
