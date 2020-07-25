package jext.buildtools.gradle;

import jext.buildtools.ModuleAnalyzer;
import jext.buildtools.Name;
import jext.buildtools.gradle.collectors.DependenciesCollector;
import jext.buildtools.gradle.collectors.ErrorsCollector;
import jext.buildtools.gradle.collectors.LoggerCollector;
import jext.buildtools.gradle.collectors.ProjectsCollector;
import jext.buildtools.gradle.util.GradleUtils;
import jext.buildtools.maven.MavenCoords;
import jext.buildtools.maven.MavenModule;
import jext.buildtools.util.PathName;
import jext.logging.Logger;
import jext.util.FileUtils;
import org.gradle.tooling.ProjectConnection;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GradleModule implements ModuleAnalyzer {

    private Logger logger;

    private GradleProject project;
    private List<GradleModule> modules;
    private List<Name> dmodules;
    private List<MavenCoords> dcoords;
    private File moduleDir;
    private Name name;

    public GradleModule(GradleProject project) {
        this.project = project;
        this.moduleDir = project.getProjectDir();
        this.name = new PathName(FileUtils.relativePath(project.getProjectDir(), moduleDir));

        this.logger = Logger.getLogger(MavenModule.class, this.name.toString());
    }

    public GradleModule(String name, GradleModule parent) {
        this.project = parent.getProject();
        this.moduleDir = new File(parent.getModuleDir(), name);
        this.name = new PathName(FileUtils.relativePath(project.getProjectDir(), moduleDir));

        this.logger = Logger.getLogger(MavenModule.class, this.name.toString());
    }

    @Override
    public Name getName() {
        return name;
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
    public GradleProject getProject() {
        return project;
    }

    public List<GradleModule> getModules(){
        if (modules == null) {
            retrieveModules();
            retrieveDependencies();
        }
        return modules;
    }

    @Override
    public List<Name> getModuleDependencies() {
        if (dmodules == null)
            retrieveDependencies();
        return dmodules;
    }

    @Override
    public List<MavenCoords> getMavenLibraries() {
        if (dcoords == null)
            retrieveDependencies();
        return dcoords;
    }

    @Override
    public List<File> getLocalLibraries() {
        return Collections.emptyList();
    }

    private void retrieveModules() {
        logger.debugf("retrieveModules", getName());

        modules = new ArrayList<>();

        String projectsTask = GradleUtils.toTask(name, "projects");
        ErrorsCollector err = new ErrorsCollector(logger);
        ProjectsCollector projects = new ProjectsCollector();
        try(ProjectConnection connection = project.getConnection()) {
            connection
                .newBuild().forTasks(projectsTask)
                .setStandardOutput(projects)
                .setStandardError(err)
                .run();
        }
        catch (Throwable t) {
            logger.error(t, t);
        }
        finally {
            projects.close();
            err.close();
        }

        projects.forEach(name -> {
            modules.add(new GradleModule(name, this));
        });
    }

    private void retrieveDependencies() {
        logger.debugf("retrieveDependencies");

        String moduleName = getName().toString();
        String dependenciesTask = GradleUtils.toTask(name, "dependencies");
        ErrorsCollector err = new ErrorsCollector(logger);
        DependenciesCollector collector = new DependenciesCollector();
        LoggerCollector logcoll = new LoggerCollector(logger, collector);
        try(ProjectConnection connection = project.getConnection()) {
            connection
                .newBuild().forTasks(dependenciesTask)
                .setStandardOutput(collector)
                // .setStandardOutput(logcoll)
                .setStandardError(err)
                .run();
        }
        catch (Throwable t) {
            logger.error(t, t);
        }
        finally {
            logcoll.close();
            collector.close();
            err.close();
        }

        dmodules = collector.getProjects()
            .stream()
            .map(name -> project.getModule(name))
            .filter(Objects::nonNull)
            .map(GradleModule::getName)
            .collect(Collectors.toList());

        dcoords = collector.getLibraries()
            .stream()
            .map(MavenCoords::new)
            .collect(Collectors.toList());
    }

    public List<File> getSources() {
        return Collections.emptyList();
    }

    public List<File> getResources() {
        return FileUtils.listFiles(moduleDir, ".gradle");
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
        return String.format("GradleModule[%s]", getName());
    }

}
