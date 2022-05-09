package jext.sourcecode.project;

import jext.exception.InvalidParameterException;
import jext.name.Name;
import jext.name.PathName;
import jext.sourcecode.project.java.JavaProjectFactory;
import jext.sourcecode.project.python.PythonProjectFactory;
import jext.util.Parameters;

import java.io.File;
import java.util.Properties;

import static jext.sourcecode.project.GuessProjectLanguage.guessProjectLanguage;

public class Projects {

    public static Project newProject(String projectName, File projectHome, Properties properties) {

        String projectLanguage;

        // First step: which is the programming language used to implement the project?
        projectLanguage = guessProjectLanguage(projectHome, properties);

        // Second step: which the project type?
        // Nota: this step DEPENDS on the programming language

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

        if (Project.JAVA_PROJECT.equals(projectLanguage))
            return JavaProjectFactory.newProject(projectName, projectHome, properties);
        if (Project.PYTHON_PROJECT.equals(projectLanguage))
            return PythonProjectFactory.newProject(projectName, projectHome, properties);
        else
            throw new ProjectException("Unsupported project language " + projectLanguage);
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
