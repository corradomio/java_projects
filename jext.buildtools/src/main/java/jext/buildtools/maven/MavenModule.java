package jext.buildtools.maven;

import jext.buildtools.ModuleAnalyzer;
import jext.buildtools.Name;
import jext.buildtools.util.PathName;
import jext.logging.Logger;
import jext.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MavenModule implements ModuleAnalyzer {

    private Logger logger;

    private MavenProject project;
    private File moduleDir;
    private MavenPom pom;
    private Name name;
    private List<MavenModule> modules;

    public MavenModule(MavenProject project) {
        this.moduleDir = project.getProjectDir();
        this.project = project;
        this.pom = new MavenPom(moduleDir);
        this.name = new PathName(FileUtils.relativePath(project.getProjectDir(), moduleDir));

        this.logger = Logger.getLogger(MavenModule.class, this.name.toString());
    }

    public MavenModule(File pomFile, MavenProject project) {
        this.moduleDir = pomFile.getParentFile();
        this.project = project;
        this.pom = new MavenPom(pomFile);
        this.name = new PathName(FileUtils.relativePath(project.getProjectDir(), moduleDir));

        this.logger = Logger.getLogger(MavenModule.class, this.name.toString());
    }

    public MavenModule(String relativePath, MavenModule parent) {
        this.moduleDir = new File(parent.getModuleDir(), relativePath);
        this.project = parent.getProject();
        this.pom = new MavenPom(moduleDir);
        this.name = new PathName(FileUtils.relativePath(project.getProjectDir(), moduleDir));
    }

    @Override
    public MavenProject getProject() {
        return project;
    }

    @Override
    public Name getName() {
        return name;
    }

    public String getCoords() {
        return pom.getCoords().toString();
    }

    @Override
    public File getModuleDir() {
        return moduleDir;
    }

    @Override
    public boolean isValid() {
        return pom.exists();
    }

    public List<MavenModule> getModules() {
        if (modules != null)
            return modules;

        modules = pom.getModules()
            .stream()
            .map(relativePath -> new MavenModule(relativePath, this))
            .collect(Collectors.toList());
        return modules;
    }

    @Override
    public List<Name> getModuleDependencies() {
        List<Name> dmodules = new ArrayList<>();

        pom.getDependencyCoords()
            .forEach(coords-> {
                MavenModule dmodule = project.getModule(coords.toString());
                if (dmodule != null)
                    dmodules.add(dmodule.getName());
            });
        return dmodules;
    }

    @Override
    public List<MavenCoords> getMavenLibraries() {
        List<MavenCoords> dependencies = new ArrayList<>();

        pom.getDependencyCoords()
            .forEach(coords-> {
                MavenModule dmodule = project.getModule(coords.toString());
                if (dmodule == null)
                    dependencies.add(coords);
            });

        return dependencies;
    }

    @Override
    public List<File> getLocalLibraries() {
        return Collections.emptyList();
    }

    public void initialize() {
        getModules();
        getMavenLibraries();
        getModuleDependencies();
    }

    public List<File> getSources() {
        return Collections.emptyList();
    }

    public List<File> getResources() {
        return FileUtils.listFiles(moduleDir, "pom.xml");
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
        return String.format("MavenModule[%s]", getName());
    }
}
