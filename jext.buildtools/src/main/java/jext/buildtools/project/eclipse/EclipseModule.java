package jext.buildtools.project.eclipse;

import jext.buildtools.Project;
import jext.buildtools.maven.MavenCoords;
import jext.buildtools.project.eclipse.util.ClasspathFile;
import jext.buildtools.project.eclipse.util.EclipseSources;
import jext.buildtools.project.BaseModule;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class EclipseModule extends BaseModule {

    private ClasspathFile classpathFile;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public EclipseModule(File moduleDir, Project project) {
        super(moduleDir, project);
        this.classpathFile = new ClasspathFile(moduleDir);
        this.sources = new EclipseSources(this);
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    public ClasspathFile getClasspathFile() {
        return classpathFile;
    }

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