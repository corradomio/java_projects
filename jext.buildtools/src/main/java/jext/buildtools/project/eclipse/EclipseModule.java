package jext.buildtools.project.eclipse;

import jext.buildtools.Project;
import jext.buildtools.project.maven.MavenProject;
import jext.buildtools.util.BaseModule;

import java.io.File;

public class EclipseModule extends BaseModule {

    public EclipseModule(File moduleDir, Project project) {
        super(moduleDir, project);

    }
}