package jext.buildtools.project.eclipse;

import jext.buildtools.Project;
import jext.buildtools.Source;
import jext.maven.MavenCoords;
import jext.buildtools.project.eclipse.util.ClasspathFile;
import jext.buildtools.project.BaseModule;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EclipseModule extends BaseModule {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private ClasspathFile classpathFile;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public EclipseModule(File moduleDir, Project project) {
        super(moduleDir, project);
        this.classpathFile = new ClasspathFile(moduleDir);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public List<Source> getSources() {
        if (sources != null)
            return sources;

        sources = new ArrayList<>();

        classpathFile.getSourceDirs().forEach(dir -> {
            List<Source> srclist = getBaseProject().getSources(dir, this);
            sources.addAll(srclist);
        });

        return sources;
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    public List<File> getLocalLibraries() {
        return classpathFile.getLocalLibraries();
    }

    public List<MavenCoords> getMavenLibraries() {
        return classpathFile.getMavenLibraries()
                .stream()
                .map(MavenCoords::new)
                .collect(Collectors.toList());
    }

}