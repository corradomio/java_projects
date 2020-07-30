package jext.buildtools.project.eclipse;

import jext.buildtools.Libraries;
import jext.buildtools.Project;
import jext.buildtools.Sources;
import jext.buildtools.maven.MavenConst;
import jext.buildtools.maven.MavenCoords;
import jext.buildtools.project.eclipse.util.ClasspathFile;
import jext.buildtools.project.eclipse.util.EclipseLibraries;
import jext.buildtools.project.eclipse.util.EclipseSources;
import jext.buildtools.util.BaseModule;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class EclipseModule extends BaseModule {

    private ClasspathFile classpathFile;
    private EclipseSources sources;
    private EclipseLibraries libraries;

    public EclipseModule(File moduleDir, Project project) {
        super(moduleDir, project);
        this.classpathFile = new ClasspathFile(moduleDir);
        this.sources = new EclipseSources(this);
    }

    @Override
    public Sources getSources() {
        return sources;
    }

    @Override
    public Libraries getLibraries() {
        return libraries;
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    public ClasspathFile getClasspathFile() {
        return classpathFile;
    }

    public List<File> listLocalLibraries() {
        return classpathFile.getLocalLibraries();
    }

    public List<MavenCoords> listMavenLibraries() {
        return classpathFile.getMavenLibraries()
                .stream()
                .map(MavenCoords::new)
                .collect(Collectors.toList());
    }

}