package jext.sourcecode.project;

import jext.exception.InvalidParameterException;
import jext.name.Name;
import jext.name.PathName;
import jext.util.Parameters;

import java.io.File;
import java.util.Properties;

import static jext.sourcecode.project.GuessProjectLanguage.guessProjectLanguage;

public class Projects {

    public static Project newProject(String projectName, File projectHome, Properties properties) {

        // First step: which is the programming language used to implement the project?
        String projectLanguage = guessProjectLanguage(projectHome, properties);

        // Second step: which the project type?
        // Nota: this step DEPENDS on the programming language
        ProjectFactory projectFactory = ProjectFactory.getFactory(projectLanguage);
        return projectFactory.newProject(projectName, projectHome, properties);
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

    public static Project newProject(File projectHome, Properties properties) {
        if (!projectHome.exists())
            throw new InvalidParameterException("projectHome", String.format("Invalid project home %s", projectHome.getAbsolutePath()));

        String projectName = projectHome.getName();
        String repositoryName = projectHome.getAbsoluteFile().getParentFile().getName();
        return newProject(PathName.of(repositoryName, projectName), projectHome, properties);
    }
}
