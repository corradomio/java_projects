package jext.buildtools.eclipse;

import jext.buildtools.Dependency;
import jext.buildtools.Name;
import jext.buildtools.Project;
import jext.buildtools.Module;
import jext.buildtools.util.PathName;
import jext.util.FileUtils;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class EclipseModule implements Module {

    private EclipseProject project;
    private File moduleDir;
    private Name name;

    public EclipseModule(EclipseProject project) {
        this(project.getProjectDir(), project);
    }

    public EclipseModule(String name, EclipseProject project) {
        this(new File(project.getProjectDir(), name), project);
    }

    public EclipseModule(File moduleDir, EclipseProject project) {
        this.project = project;
        this.moduleDir = moduleDir;
        this.name = new PathName(FileUtils.relativePath(project.getProjectDir(), moduleDir));
    }

    @Override
    public Project getProject() {
        return project;
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public boolean isValid() {
        return moduleDir.exists() && moduleDir.isDirectory();
    }

    @Override
    public List<EclipseModule> getModuleDependencies() {
        return Collections.emptyList();
    }

    @Override
    public List<Dependency> getDependencies() {
        return Collections.emptyList();
    }
}
