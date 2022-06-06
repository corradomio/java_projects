package jext.sourcecode.project;

import jext.exception.InvalidParameterException;
import jext.name.Name;
import jext.name.PathName;
import jext.sourcecode.project.info.InfoProject;
import jext.util.Parameters;

import java.io.File;
import java.util.Properties;

import static jext.sourcecode.project.GuessProjectLanguage.guessProjectLanguage;

public class Projects {

    public static Project newProject(String projectName, File projectHome, Properties properties,
                                     LibraryFinderManager lfm) {

        Project project = createProject(projectName, projectHome, properties);
        setLibraryFinder(project, lfm);
        return project;
    }

    private static Project createProject(String projectName, File projectHome, Properties properties) {
        if (projectHome.getName().endsWith(".json"))
            return new InfoProject(projectName, projectHome, properties);

        // First step: which is the programming language used to implement the project?
        String projectLanguage = guessProjectLanguage(projectHome, properties);

        // Second step: which the project type?
        // Nota: this step DEPENDS on the programming language
        ProjectFactory projectFactory = ProjectFactory.getFactory(projectLanguage);
        return projectFactory.newProject(projectName, projectHome, properties);
    }

    private static void setLibraryFinder(Project project, LibraryFinderManager lfm) {
        if (lfm != null) {
            String language = project.getProjectLanguage();
            LibraryFinder lfinder = lfm.newLibraryFinder(language);
            project.setLibraryFinder(lfinder);
    }
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public static Project newProject(Name name, File projectHome, Properties properties,
                                     LibraryFinderManager lfm) {
        return newProject(name.getFullName(), projectHome, properties, lfm);
    }

    public static Project newProject(Name name, File projectHome, Parameters parameters,
                                     LibraryFinderManager lfm) {
        return newProject(name.getFullName(), projectHome, parameters.toProperties(), lfm);
    }

    // ----------------------------------------------------------------------
    // DEBUG
    // ----------------------------------------------------------------------

    public static Project newProject(File projectHome, Properties properties,
                                     LibraryFinderManager lfm) {
        if (!projectHome.exists())
            throw new InvalidParameterException("projectHome",
                String.format("Invalid project home %s", projectHome.getAbsolutePath()));

        String projectName = projectHome.getName();
        String repositoryName = projectHome.getAbsoluteFile().getParentFile().getName();
        return newProject(PathName.of(repositoryName, projectName), projectHome, properties, lfm);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
