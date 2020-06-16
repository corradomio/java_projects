package jext.gradle;

import jext.gradle.collectors.DependenciesCollector;
import jext.gradle.collectors.ErrorsCollector;
import jext.gradle.collectors.LoggerCollector;
import jext.gradle.collectors.ProjectsCollector;
import jext.gradle.util.GradleUtils;
import jext.logging.Logger;
import jext.maven.MavenCoords;
import jext.util.FileUtils;
import org.gradle.tooling.ProjectConnection;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GradleModule {

    private Logger logger;

    private GradleProject project;
    private GradleModule parent;
    private List<GradleModule> modules;
    private List<GradleModule> dmodules;
    private List<MavenCoords> dependencies;
    private File moduleDir;
    private String name;

    public GradleModule(GradleProject project) {
        this.project = project;
        this.moduleDir = project.getProjectDir();
        this.name = FileUtils.relativePath(project.getProjectDir(), moduleDir);
        this.logger = Logger.getLogger(getClass(), name);
    }

    public GradleModule(String name, GradleModule parent) {
        this.parent = parent;
        this.project = parent.getProject();
        this.moduleDir = new File(parent.getModuleDir(), name);
        this.name = FileUtils.relativePath(project.getProjectDir(), moduleDir);
        this.logger = Logger.getLogger(getClass(), name);
    }

    public String getName() {
        return name;
    }

    public File getModuleDir() {
        return moduleDir;
    }

    public GradleProject getProject() {
        return project;
    }

    public List<GradleModule> getModules(){
        if (modules == null)
            retrieveModules();
        return modules;
    }

    public List<GradleModule> getModuleDependencies() {
        if (dmodules == null)
            retrieveDependencies();
        return dmodules;
    }

    public List<MavenCoords> getDependencies() {
        if (dependencies == null)
            retrieveDependencies();
        return dependencies;
    }

    public void analyzeStructure() {
        getDependencies();
        getModuleDependencies();
        getModules().forEach(GradleModule::analyzeStructure);
    }

    private void retrieveModules() {
        logger.debugf("[%s] retrieveModules", getName());

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
        logger.debugf("[%s] retrieveDependencies", getName());

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
                .collect(Collectors.toList());

        dependencies = collector.getLibraries()
                .stream()
                .map(MavenCoords::new)
                .collect(Collectors.toList());
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
        GradleModule that = (GradleModule) obj;
        return getName().equals(that.getName());
    }

    @Override
    public String toString() {
        return String.format("GradleModule[%s]", getName());
    }

}
