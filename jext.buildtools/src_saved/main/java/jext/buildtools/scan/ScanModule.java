package jext.buildtools.scan;

import jext.buildtools.ModuleAnalyzer;
import jext.buildtools.Name;
import jext.buildtools.maven.MavenCoords;
import jext.buildtools.scan.rules.Template;
import jext.buildtools.util.PathName;
import jext.util.FileUtils;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ScanModule implements ModuleAnalyzer {

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

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public ScanProject getProject() {
        return project;
    }

    @Override
    public File getModuleDir() {
        return moduleDir;
    }

    @Override
    public boolean isValid() {
        return moduleDir.exists() && moduleDir.isDirectory();
    }

    @Override
    public List<Name> getModuleDependencies() {
        return template.getModuleDependencies()
            .stream()
            .map(name -> project.getModule(name))
            .filter(Objects::nonNull)
            .map(ModuleAnalyzer::getName)
            .collect(Collectors.toList());
    }

    @Override
    public List<MavenCoords> getMavenLibraries() {
        return template.getMavenDependencies();
    }

    @Override
    public List<File> getLocalLibraries() {
        return template.getLocalDependencies(moduleDir);
    }

    public List<File> getSources() {
        return template.getSources(moduleDir);
    }

    public List<File> getResources() {
        return template.getResources(moduleDir);
    }

    // ----------------------------------------------------------------------

    void setTemplate(Template template) {
        this.template = template;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        ModuleAnalyzer that = (ModuleAnalyzer) obj;
        return getName().equals(that.getName());
    }

    @Override
    public String toString() {
        return String.format("ScanModule[%s]", getName());
    }
}
