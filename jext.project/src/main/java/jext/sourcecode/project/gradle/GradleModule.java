package jext.sourcecode.project.gradle;

import jext.lang.JavaUtils;
import jext.maven.MavenCoords;
import jext.maven.MavenDownloader;
import jext.name.Name;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.RuntimeLibrary;
import jext.sourcecode.project.gradle.collectors.DependenciesCollector;
import jext.sourcecode.project.gradle.collectors.ErrorsCollector;
import jext.sourcecode.project.gradle.collectors.LoggerCollector;
import jext.sourcecode.project.gradle.collectors.ProjectsCollector;
import jext.sourcecode.project.gradle.util.BuildGradleFile;
import jext.sourcecode.project.maven.MavenLibrary;
import jext.sourcecode.project.util.BaseModule;
import jext.util.StringUtils;
import org.gradle.tooling.BuildException;
import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.ProjectConnection;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class GradleModule extends BaseModule {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static final String BUILD_GRADLE = GradleProject.BUILD_GRADLE;

    private List<Name> dmodules;
    private List<Library> dcoords;
    private BuildGradleFile buildGradle;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    GradleModule(GradleProject project) {
        super(project.getProjectHome(), project);
        init();
    }

    GradleModule(File moduleHome, GradleProject project) {
        super(moduleHome, project);
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
            Module dmodule = project.getModules().getModule(dname.toString());
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

    @Override
    public RuntimeLibrary getRuntimeLibrary() {
        // try to retrieve the JDK from the current configuration file
        String javaVersion = buildGradle.getJavaVersion();
        if (!StringUtils.isEmpty(javaVersion))
            return super.getRuntimeLibrary(JavaUtils.toJDK(javaVersion));

        // try to retrieve the Java version using other methods
        return super.getRuntimeLibrary();
    }

    @Override
    public Set<String> getMavenRepositories() {
        Set<String> repos;
        if (getGradleProject().isDependenciesResolved())
            repos = getGradleProject().getMavenRepositories(this);
        else
            repos = buildGradle.getRepositories();
        repos.add("https://repo.maven.apache.org/maven2");
        return repos;
    }

    @Override
    protected void collectGradleLibraries(Set<Library> collectedLibraries) {
        if (getGradleProject().isDependenciesResolved())
            collectedLibraries.addAll(getGradleProject().getMavenLibraries(this));

        if (dcoords == null)
            retrieveDependencies();

        collectedLibraries.addAll(dcoords);
    }

    // @Override
    // protected List<Library> getMavenLibraries() {
    //     if (getGradleProject().isDependenciesResolved())
    //         return getGradleProject().getMavenLibraries(this);
    //     if (dcoords == null)
    //         retrieveDependencies();
    //     return dcoords;
    // }

    private List<Name> getGradleDependencies() {
        if (dmodules == null)
            retrieveDependencies();
        return dmodules;
    }

    private void retrieveDependencies() {

        // initialize to avoid NullPointerException(s)
        dmodules = Collections.emptyList();
        dcoords = Collections.emptyList();

        if (project.isAborted()) return;

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
        finally {
            logcoll.close();
            collector.close();
            err.close();
        }

        // String gradleConfiguration = project.getProperties().getProperty(GradleProject.GRADLE_CONFIGURATION, COMPILE_CLASSPATH);
        // 2021/02/22: it is necessary to collect MULTIPLE gradle configurations. At minimum:
        //
        //  1) all configurations containing 'default'
        //  2) all configurations containing 'compile'
        //  3) all configurations containing 'implementation' because 'compile' is deprecated
        //  4) all configurations containing 'test' because we are include also the source files used for tests
        //

        Set<String> dmods = new HashSet<>();
        Set<String> dlibs = new HashSet<>();

        for (String configurationName : collector.getConfigurationNames()) {
            if (!isConfigurationValid(configurationName))
                continue;

            dlibs.addAll(collector.getLibraries(configurationName));
            dmods.addAll(collector.getProjects(configurationName));
        }

        MavenDownloader md = project.getLibraryDownloader();

        List<MavenCoords> coords = dlibs.stream()
            .map(MavenCoords::of)
            .collect(Collectors.toList());
        md.checkArtifacts(coords, false, true);

        dmodules = dmods
            .stream()
            .map(name -> project.getModules().getModule(name))
            .filter(Objects::nonNull)
            .map(Module::getName)
            .sorted()
            .collect(Collectors.toList());

        dcoords = coords
            .stream()
            .sorted()
            .map(dcoords -> new MavenLibrary(dcoords, md, project))
            .collect(Collectors.toList());
    }

    private static boolean isConfigurationValid(String configurationName) {
        for (String validName : GradleProject.VALID_CONFIGURATIONS)
            if (configurationName.contains(validName))
                return true;
        return false;
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

        // File javaHome = findJavaHome();

        try(ProjectConnection connection = getGradleProject().getConnection()) {
            BuildLauncher launcher = connection
                .newBuild()
                .forTasks(projectsTask)
                .withArguments("--continue")
                // .setStandardOutput(projects)             // this
                .setStandardOutput(logcoll)                 // OR this
                .setStandardError(err);

            launcher.run();
        }
        catch (BuildException e) {
            String message = e.getCause().getMessage();
            if (!message.contains("not found in root project"))
                logger.error(e, e);
        }
        finally {
            projects.close();
            err.close();
        }

        projects.forEach(name -> {
            GradleModule module = (GradleModule) getGradleProject().newModule(name);
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
        String gradleProject = getPath().replace('/',':');
        if (gradleProject.isEmpty())
            return taskName;
        else
            return String.format("%s:%s", gradleProject, taskName);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}