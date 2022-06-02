package jext.sourcecode.project;

import jext.sourcecode.project.csharp.CSharpConstants;
import jext.sourcecode.project.java.JavaConstants;
import jext.sourcecode.project.python.PythonConstants;
import jext.sourcecode.project.csharp.CSharpProjectFactory;
import jext.sourcecode.project.java.JavaProjectFactory;
import jext.sourcecode.project.python.PythonProjectFactory;
import jext.util.MapUtils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Properties;

public abstract class ProjectFactory {

    private static Map<String, Class<ProjectFactory>> PROJECT_FACTORIES = MapUtils.asMap(
        JavaConstants.JAVA, JavaProjectFactory.class,
        PythonConstants.PYTHON, PythonProjectFactory.class,
        CSharpConstants.CSHARP, CSharpProjectFactory.class
    );

    public static ProjectFactory getFactory(String projectLanguage) {
        Class<ProjectFactory> factoryClass = PROJECT_FACTORIES.get(projectLanguage);
        if (factoryClass == null)
            throw new ProjectException("Unsupported project language " + projectLanguage);

        try {
            return factoryClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new ProjectException("Unable to instantiate project factory " + factoryClass.getName(), e);
        }

        // if (JavaConstants.JAVA.equals(projectLanguage))
        //     return new JavaProjectFactory();
        // if (PythonConstants.PYTHON.equals(projectLanguage))
        //     return new PythonProjectFactory();
        // if (CSharpConstants.CSHARP.equals(projectLanguage))
        //     return new CSharpProjectFactory();
        // else
        //     throw new ProjectException("Unsupported project language " + projectLanguage);
    }

    public abstract Project newProject(String projectName, File projectHome, Properties properties);

}
