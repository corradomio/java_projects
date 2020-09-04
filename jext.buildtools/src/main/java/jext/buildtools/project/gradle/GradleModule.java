package jext.buildtools.project.gradle;

import jext.buildtools.Module;
import jext.buildtools.Name;
import jext.buildtools.Named;
import jext.buildtools.project.BaseModule;
import jext.buildtools.project.gradle.collectors.DependenciesCollector;
import jext.buildtools.project.gradle.collectors.ErrorsCollector;
import jext.buildtools.project.gradle.collectors.LoggerCollector;
import jext.buildtools.project.gradle.collectors.ProjectsCollector;
import jext.maven.MavenCoords;
import jext.util.OrderedHashSet;
import org.gradle.tooling.ProjectConnection;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class GradleModule extends BaseModule {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private List<GradleModule> modules;
    private List<Name> dmodules;
    private List<MavenCoords> dcoords;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public GradleModule(GradleProject project) {
        super(project.getDirectory(), project);
    }

    public GradleModule(File moduleDir, GradleProject project) {
        super(moduleDir, project);
    }

    public GradleModule(String name, GradleModule parent) {
        super(new File(parent.getDirectory(), name), parent.getProject());
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    protected List<Module> getDependencies() {
        //
        // Override 'BaseModule::getDependencies()' to reorder the
        // dependencies based on the 'building system configuration file'
        //

        Set<Module> dependencies = new OrderedHashSet<>();
        List<Name> dnames = getGradleDependencies();

        // speedup: dnames is empty
        if (dnames.isEmpty())
            return super.getDependencies();

        dnames.forEach(dname -> {
            Module dmodule = project.getModule(dname.toString());
            if (dmodule != null)
                dependencies.add(dmodule);
        });

        // speedup: Gradle dependencies is empty
        if (dependencies.isEmpty())
            return super.getDependencies();

        // add the missing dependencies based on the module types intersection
        dependencies.addAll(super.getDependencies());

        // return a list
        return new ArrayList<>(dependencies);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public List<GradleModule> getModules(){
        if (modules != null)
            return modules;

        logger.debugf("retrieveModules %s", getName());

        modules = new ArrayList<>();

        String projectsTask = toTask("projects");
        ErrorsCollector err = new ErrorsCollector(logger);
        ProjectsCollector projects = new ProjectsCollector();
        try(ProjectConnection connection = getGradleProject().getConnection()) {
            connection
                .newBuild().forTasks(projectsTask)
                .setStandardOutput(projects)
                .setStandardError(err)
                .run();
        }
        // catch (BuildException e) {
        //     String message = e.getCause().getMessage();
        //     if (!message.contains("not found in root project"))
        //         logger.error(e);
        // }
        catch (Throwable e) {
            String message = e.getCause().getMessage();
            if (!message.contains("not found in root project"))
                logger.error(e);
        }
        finally {
            projects.close();
            err.close();
        }

        projects.forEach(name -> {
            GradleModule module = (GradleModule) project.getModule(name);
            if (module == null) {
                module = new GradleModule(name, this);
                getGradleProject().addGradleModule(module);
            }
            modules.add(module);
        });

        return modules;
    }

    public List<Name> getGradleDependencies() {
        if (dmodules == null)
            retrieveDependencies();
        return dmodules;
    }

    public List<MavenCoords> getMavenLibraries() {
        if (dcoords == null)
            retrieveDependencies();
        return dcoords;
    }

    private void retrieveDependencies() {
        logger.debugf("retrieveDependencies");

        String dependenciesTask = toTask("dependencies");
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
        // catch (BuildException e) {
        //     String message = e.getCause().getMessage();
        //     if (!message.contains("not found in root project"))
        //         logger.error(e);
        // }
        catch (Throwable e) {
            String message = e.getCause().getMessage();
            if (!message.contains("not found in root project"))
                logger.error(e);
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
            .map(Named::getName)
            .sorted()
            .collect(Collectors.toList());

        dcoords = collector.getLibraries()
            .stream()
            .map(MavenCoords::new)
            .sorted()
            .collect(Collectors.toList());
    }

    private GradleProject getGradleProject() {
        return (GradleProject) getProject();
    }

    private String toTask(String taskName) {
        String moduleName = getName().toString().replace('/',':');
        if (moduleName.isEmpty())
            return taskName;
        else
            return String.format("%s:%s", moduleName, taskName);
    }
}
