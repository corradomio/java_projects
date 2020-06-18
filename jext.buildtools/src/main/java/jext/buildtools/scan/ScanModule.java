package jext.buildtools.scan;

import jext.buildtools.Dependency;
import jext.buildtools.Module;
import jext.buildtools.Name;
import jext.buildtools.scan.rules.Template;
import jext.buildtools.util.PathName;
import jext.util.FileUtils;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class ScanModule implements Module {

    private ScanProject project;
    private File moduleDir;
    private Name name;
    private Template template;

    public ScanModule(ScanProject project) {
        this("", project.getProjectDir(), project);
    }

    public ScanModule(File moduleDir, ScanProject project) {
        this(FileUtils.relativePath(project.getProjectDir(), moduleDir),
                moduleDir,
                project);
    }

    public ScanModule(String name, File moduleDir, ScanProject project) {
        this.project = project;
        this.moduleDir = moduleDir;
        this.name = new PathName(name);
    }

    public Name getName() {
        return name;
    }

    public ScanProject getProject() {
        return project;
    }

    @Override
    public boolean isValid() {
        return moduleDir.exists() && moduleDir.isDirectory();
    }

    @Override
    public List<? extends Module> getModuleDependencies() {
        return Collections.emptyList();
    }

    @Override
    public List<? extends Dependency> getDependencies() {
        return Collections.emptyList();
    }

    void setTemplate(Template template) {
        this.template = template;
    }

}
