package jext.buildtools.project.eclipse;

import jext.buildtools.Libraries;
import jext.buildtools.Project;
import jext.buildtools.Sources;
import jext.buildtools.util.BaseModule;

import java.io.File;

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

    ClasspathFile getClasspathFile() {
        return classpathFile;
    }
}