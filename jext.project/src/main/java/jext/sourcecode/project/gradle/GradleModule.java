package jext.sourcecode.project.gradle;

import jext.name.Name;
import jext.sourcecode.project.gradle.util.BuildGradleFile;
import jext.sourcecode.project.maven.MavenLibrary;
import jext.sourcecode.project.gradle.collectors.DependenciesCollector;
import jext.sourcecode.project.gradle.collectors.ErrorsCollector;
import jext.sourcecode.project.gradle.collectors.LoggerCollector;
import jext.sourcecode.project.gradle.collectors.ProjectsCollector;
import jext.sourcecode.project.util.BaseModule;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.Module;
import jext.maven.MavenCoords;
import jext.maven.MavenDownloader;
import org.gradle.tooling.BuildException;
import org.gradle.tooling.ProjectConnection;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class GradleModule extends BaseModule {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static final String BUILD_GRADLE = "build.gradle";
    private static final String COMPILE_CLASSPATH = "compileClasspath";
    private static final String TEST_COMPILE_CLASSPATH = "testCompileClasspath";

    private List<Name> dmodules;
    private List<Library> dcoords;
    private BuildGradleFile buildGradle;

    // private SettingsGradleFile settingsGradle;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public GradleModule(GradleProject project) {
        super(project.getProjectHome(), project);
        init();
    }

    public GradleModule(File moduleHome, GradleProject project) {
        super(moduleHome, project);
        init();
    }

    public GradleModule(String name, GradleModule parent) {
        super(new File(parent.getModuleHome(), name), parent.getProject());
        init();
    }

    private void init() {
        File buildGradle = new File(moduleHome, BUILD_GRADLE);
        File directoryGradle = new File(moduleHome, moduleHome.getName() + ".gradle");

        if (directoryGradle.exists())
            this.buildGradle = new BuildGradleFile(directoryGradle);
        else
            this.buildGradle = new BuildGradleFile(buildGradle);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public List<Module> getDependencies() {
        if (dependencies != null)
            return dependencies;

        //
        // Override 'BaseModule::getDependencies()' to reorder the
        // dependencies based on the 'building system configuration file'
        //

        Set<Module> orderedDeps = new TreeSet<>(COMPARATOR);

        List<Name> dnames = getGradleDependencies();

        // speedup: dnames is empty
        if (dnames.isEmpty())
            return super.getDependencies();

        dnames.forEach(dname -> {
            Module dmodule = project.getModule(dname.toString());
            if (dmodule != null)
                orderedDeps.add(dmodule);
        });

        // speedup: Gradle dependencies is empty
        if (orderedDeps.isEmpty())
            return super.getDependencies();

        // add the missing dependencies based on the module types intersection
        orderedDeps.addAll(super.getDependencies());

        this.dependencies = orderedDeps.isEmpty()
            ? Collections.emptyList()
            : new ArrayList<>(orderedDeps);

        return this.dependencies;
    }

    // ----------------------------------------------------------------------
    // Libraries
    // ----------------------------------------------------------------------

    // ----------------------------------------------------------------------

    @Override
    protected List<String> getMavenRepositories() {
        return buildGradle.getRepositories();
    }

    @Override
    protected List<Library> getMavenLibraries() {
        if (dcoords == null)
            retrieveDependencies();
        return dcoords;
    }

    private List<Name> getGradleDependencies() {
        if (dmodules == null)
            retrieveDependencies();
        return dmodules;
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
                .withArguments("--continue")
                // .setStandardOutput(collector)            // this
                .setStandardOutput(logcoll)                 // OR this
                .setStandardError(err)
                .run();
        }
        catch (BuildException e) {
            String message = e.getCause().getMessage();
            if (!message.contains("not found in root project"))
                logger.error(e);
        }
        // catch (Throwable e) {
        //     String message = e.getCause().getMessage();
        //     if (!message.contains("not found in root project"))
        //         logger.error(e);
        // }
        finally {
            logcoll.close();
            collector.close();
            err.close();
        }

        String gradleConfiguration = project.getProperties().getProperty(GradleProject.GRADLE_CONFIGURATION, COMPILE_CLASSPATH);

        List<MavenCoords> coordList = collector.getLibraries(gradleConfiguration)
            .stream()
            .map(MavenCoords::new)
            .collect(Collectors.toList());

        MavenDownloader md = project.getLibraryDownloader();
        // md.checkArtifacts(coordList);

        dmodules = collector.getProjects(gradleConfiguration)
            .stream()
            .map(name -> project.getModule(name))
            .filter(Objects::nonNull)
            .map(Module::getName)
            .sorted()
            .collect(Collectors.toList());

        dcoords = coordList
            .stream()
            .sorted()
            .map(coords -> new MavenLibrary(coords, md, project))
            .collect(Collectors.toList());
    }

    // ----------------------------------------------------------------------
    // Modules
    // ----------------------------------------------------------------------

    // Used to retrieve the list of project modules
    public List<GradleModule> getModules(){

        List<GradleModule> modules = new ArrayList<>();

        String projectsTask = toTask("projects");
        ErrorsCollector err = new ErrorsCollector(logger);
        ProjectsCollector projects = new ProjectsCollector();
        LoggerCollector logcoll = new LoggerCollector(logger, projects);
        try(ProjectConnection connection = getGradleProject().getConnection()) {
            connection
                .newBuild().forTasks(projectsTask)
                    .withArguments("--continue")
                // .setStandardOutput(projects)             // thi
                .setStandardOutput(logcoll)
                .setStandardError(err)
                .run();
        }
        catch (BuildException e) {
            String message = e.getCause().getMessage();
            if (!message.contains("not found in root project"))
                logger.error(e, e);
        }
        // catch (Throwable t) {
        //     logger.error(t, t);
        // }
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

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

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