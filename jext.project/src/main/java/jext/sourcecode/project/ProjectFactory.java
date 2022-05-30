package jext.sourcecode.project;

import jext.java.JavaConstants;
import jext.python.PythonConstants;
import jext.sourcecode.project.java.JavaProjectFactory;
import jext.sourcecode.project.python.PythonProjectFactory;

import java.io.File;
import java.util.Properties;

public abstract class ProjectFactory {

    public static ProjectFactory getFactory(String projectLanguage) {
        if (JavaConstants.JAVA.equals(projectLanguage))
            return new JavaProjectFactory();
        if (PythonConstants.PYTHON.equals(projectLanguage))
            return new PythonProjectFactory();
        throw new ProjectException("Unsupported project language " + projectLanguage);
    }

    public abstract Project newProject(String projectName, File projectHome, Properties properties);

}
