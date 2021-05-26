package jext.sourcecode.project;

import jext.logging.Logger;
import jext.maven.MavenDownloader;
import jext.name.Name;
import jext.name.PathName;
import jext.sourcecode.project.ant.AntProject;
import jext.sourcecode.project.eclipse.EclipseProject;
import jext.sourcecode.project.gradle.GradleProject;
import jext.sourcecode.project.info.InfoProject;
import jext.sourcecode.project.maven.MavenProject;
import jext.sourcecode.project.simple.SimpleProject;
import jext.sourcecode.resources.libraries.JavaLibraryFinder;
import jext.util.Parameters;
import jext.util.StringUtils;

import java.io.File;
import java.util.Properties;

import static jext.sourcecode.project.GuessProjectType.guessProjectType;
import static jext.sourcecode.project.Project.PROJECT_TYPE;

public class ProjectFactory {

    private static final String AUTO = "auto";
    private static final Logger logger = Logger.getLogger(ProjectFactory.class);

    // ----------------------------------------------------------------------
    // Static Methods
    // ----------------------------------------------------------------------

    // public static List<Project> findProjects(File directory, Properties props) {
    //     List<Project> projects = new ArrayList<>();
    //     Properties nprops = new Properties();
    //     nprops.putAll(nprops);
    //     nprops.setProperty("check.moduleOnly", "true");
    //
    //     try {
    //         Files.walkFileTree(directory.toPath(), new SimpleFileVisitor<Path>() {
    //
    //             @Override
    //             public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
    //             {
    //                 File projectHome = dir.toFile();
    //                 if (guessProjectType(projectHome, nprops).equals(SimpleProject.TYPE))
    //                     return FileVisitResult.CONTINUE;
    //
    //                 projects.add(newProject(projectHome, props));
    //                 return FileVisitResult.SKIP_SUBTREE;
    //             }
    //         });
    //     } catch (IOException e) {
    //         Logger.getLogger(ProjectFactory.class).error(e, e);
    //     }
    //
    //     return projects;
    // }


    public static Project newProject(String projectName, File projectHome, Properties properties) {

        Project project;
        String projectType;
        String runtimeLibrary;

        //
        // If the project type is not specified, we try to understand it based on
        // presence of 'building system configuration files' (for example 'pom.xml',
        // 'build.gradle', etc) following the SPECIFIC order:
        //
        //      1) Gradle
        //      2) Maven
        //      3) Eclipse
        //      4) Ant
        //      5) Simple
        //

        projectType = properties.getProperty(PROJECT_TYPE, AUTO);
        if (StringUtils.isEmpty(projectName) || AUTO.equals(projectType));
            projectType = guessProjectType(projectHome, properties);

        if (AntProject.TYPE.equals(projectType))
            project = new AntProject(projectName, projectHome, properties);
        else if (MavenProject.TYPE.equals(projectType))
            project = new MavenProject(projectName, projectHome, properties);
        else if (GradleProject.TYPE.equals(projectType))
            project = new GradleProject(projectName, projectHome, properties);
        else if (EclipseProject.TYPE.equals(projectType))
            project = new EclipseProject(projectName, projectHome, properties);
        else if (SimpleProject.TYPE.equals(projectType))
            project = new SimpleProject(projectName, projectHome, properties);
        else if (InfoProject.TYPE.equals(projectType))
            project = new InfoProject(projectName, projectHome, properties);
        else if (SimpleProject.UNKNOWN.equals(projectType))
            project = new SimpleProject(projectName, projectHome, properties);
        else {
            logger.errorf("Project type %s unknown", projectType);
            project = new SimpleProject(projectName, projectHome, properties);
        }

        //
        // Check the runtimeLibrary to use
        // In theory it is possible to identify the correct version of Java or Android
        // But this can be expensive.
        //

        // runtimeLibrary = project.getProperties().getProperty(RUNTIME_LIBRARY, null);
        // if (runtimeLibrary == null)  {
        //     runtimeLibrary = guessRuntimeLibrary(project);
        //     project.getProperties().setProperty(RUNTIME_LIBRARY, runtimeLibrary);
        // }

        MavenDownloader downloader = new MavenDownloader();
        LibraryFinder lfinder = new JavaLibraryFinder().setDownloader(downloader);
        project.setLibraryFinder(lfinder);

        return project;
    }

    public static Project newProject(Name name, File projectHome, Properties properties) {
        return newProject(name.getFullName(), projectHome, properties);
    }

    public static Project newProject(Name name, File projectHome, Parameters parameters) {
        return newProject(name.getFullName(), projectHome, parameters.toProperties());
    }

    // ----------------------------------------------------------------------
    // DEBUG
    // ----------------------------------------------------------------------

    // public static Project newProject(File projectHome) {
    //     return newProject(projectHome, Parameters.empty());
    // }

    public static Project newProject(File projectHome, Properties properties) {
        String projectName = projectHome.getName();
        String repositoryName = projectHome.getAbsoluteFile().getParentFile().getName();
        return newProject(new PathName(repositoryName, projectName), projectHome, properties);
    }

    // public static Project newProject(File projectHome, Parameters params) {
    //     return newProject(projectHome.getName(), projectHome, params.toProperties());
    // }
}
