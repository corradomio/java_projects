package jext.buildtools.project.gradle;

import jext.buildtools.Name;
import jext.buildtools.Named;
import jext.buildtools.maven.MavenCoords;
import jext.buildtools.project.gradle.collectors.DependenciesCollector;
import jext.buildtools.project.gradle.collectors.ErrorsCollector;
import jext.buildtools.project.gradle.collectors.LoggerCollector;
import jext.buildtools.project.gradle.collectors.ProjectsCollector;
import jext.buildtools.resource.FileResources;
import jext.buildtools.source.java.JavaSources;
import jext.buildtools.util.BaseModule;
import jext.logging.Logger;
import org.gradle.tooling.BuildException;
import org.gradle.tooling.ProjectConnection;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GradleModule extends BaseModule {

    private List<GradleModule> modules;
    private List<Name> dmodules;
    private List<MavenCoords> dcoords;

    public GradleModule(GradleProject project) {
        super(project.getDirectory(), project);
        this.sources = new JavaSources(this);
        this.resources = new FileResources(this);
    }

    public GradleModule(File moduleDir, GradleProject project) {
        super(moduleDir, project);
        this.sources = new JavaSources(this);
        this.resources = new FileResources(this);
    }


    public GradleModule(String name, GradleModule parent) {
        super(new File(parent.getDirectory(), name), parent.getProject());

        this.logger = Logger.getLogger(GradleModule.class, this.getName().toString());
    }

    public List<GradleModule> getModules(){
        if (modules != null)
            return modules;

        logger.debugf("retrieveModules", getName());

        modules = new ArrayList<>();

        String projectsTask = toTask(getName(), "projects");
        ErrorsCollector err = new ErrorsCollector(logger);
        ProjectsCollector projects = new ProjectsCollector();
        try(ProjectConnection connection = getGradleProject().getConnection()) {
            connection
                .newBuild().forTasks(projectsTask)
                .setStandardOutput(projects)
                .setStandardError(err)
                .run();
        }
        catch (BuildException e) {
            String message = e.getCause().getMessage();
            if (!message.contains("not found in root project"))
                logger.error(e, e);
        }
        catch (Throwable t) {
            logger.error(t, t);
        }
        finally {
            projects.close();
            err.close();
        }

        projects.forEach(name -> {
            GradleModule module = (GradleModule) project.findModule(name);
            if (module == null) {
                module = new GradleModule(name, this);
                getGradleProject().addGradleModule(module);
            }
            modules.add(module);
        });

        return modules;
    }

    // public List<Name> getModuleDependencies() {
    //     if (dmodules == null)
    //         retrieveDependencies();
    //     return dmodules;
    // }

    // public List<MavenCoords> getMavenLibraries() {
    //     if (dcoords == null)
    //         retrieveDependencies();
    //     return dcoords;
    // }

    // public List<File> getLocalLibraries() {
    //     return Collections.emptyList();
    // }

    public List<MavenCoords> getMavenLibraries() {
        if (dcoords != null)
            return dcoords;

        logger.debugf("retrieveDependencies");

        String dependenciesTask = toTask(getName(), "dependencies");
        ErrorsCollector err = new ErrorsCollector(logger);
        DependenciesCollector collector = new DependenciesCollector();
        LoggerCollector logcoll = new LoggerCollector(logger, collector);
        try(ProjectConnection connection = getGradleProject().getConnection()) {
            connection
                .newBuild().forTasks(dependenciesTask)
                .setStandardOutput(collector)
                // .setStandardOutput(logcoll)
                .setStandardError(err)
                .run();
        }
        catch (BuildException e) {
            String message = e.getCause().getMessage();
            if (!message.contains("not found in root project"))
                logger.error(e, e);
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
            .map(name -> project.findModule(name))
            .filter(Objects::nonNull)
            .map(Named::getName)
            .collect(Collectors.toList());

        dcoords = collector.getLibraries()
            .stream()
            .map(MavenCoords::new)
            .collect(Collectors.toList());

        return dcoords;
    }

    private GradleProject getGradleProject() {
        return (GradleProject) getProject();
    }

    private static String toTask(Name name, String taskName) {
        String moduleName = name.toString().replace('/',':');
        if (moduleName.isEmpty())
            return taskName;
        else
            return String.format("%s:%s", moduleName, taskName);
    }
}
