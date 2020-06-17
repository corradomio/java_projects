package jext.buildtools.gradle;

import jext.buildtools.gradle.collectors.DependenciesCollector;
import jext.buildtools.gradle.collectors.ErrorsCollector;
import jext.buildtools.gradle.collectors.LoggerCollector;
import jext.buildtools.gradle.collectors.ProjectsCollector;
import jext.buildtools.gradle.util.GradleUtils;
import jext.buildtools.util.Name;
import jext.buildtools.util.PathName;
import jext.logging.Logger;
import jext.buildtools.maven.MavenCoords;
import jext.buildtools.maven.MavenModule;
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
    private List<GradleModule> modules;
    private List<GradleModule> dmodules;
    private List<MavenCoords> dependencies;
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

    public Name getName() {
        return name;
    }

    public File getModuleDir() {
        return moduleDir;
    }

    public boolean isValid() {
        return moduleDir.exists() && moduleDir.isDirectory();
    }

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
