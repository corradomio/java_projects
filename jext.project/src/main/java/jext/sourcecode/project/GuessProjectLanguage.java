package jext.sourcecode.project;

import jext.util.FileUtils;
import jext.util.MapUtils;
import jext.util.StringUtils;

import java.io.File;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import static jext.sourcecode.project.csharp.CSharpConstants.CSHARP;
import static jext.sourcecode.project.csharp.CSharpConstants.CSHARP_EXT;
import static jext.java.JavaConstants.JAVA;
import static jext.java.JavaConstants.JAVA_EXT;
import static jext.sourcecode.project.python.PythonConstants.PYTHON;
import static jext.sourcecode.project.python.PythonConstants.PYTHON_EXT;
import static jext.sourcecode.project.Project.AUTO;
import static jext.sourcecode.project.Project.PROJECT_LANGUAGE;

public class GuessProjectLanguage {

    /**
     * Given the directory, this method retrieve the programming language
     * used to implement the project.
     * The 'trick' used is to count the file extensions and:
     *
     *      1) if there are a lot of '.java' files, it is a Java project
     *      2) if there are a lot of '.py' files, it is a Python project
     *      3) other programming languages will be available in the future
     *
     * @param projectHome project home
     * @param properties project properties
     * @return programming language used in the project
     */
    public static String guessProjectLanguage(File projectHome, Properties properties) {
        String projectLanguage = properties.getProperty(PROJECT_LANGUAGE, AUTO);

        // NOT "" or "auto"
        if (!StringUtils.isEmpty(projectLanguage) && !AUTO.equals(projectLanguage))
            return projectLanguage;

        // projectHome can be a 'JSON' file
        //
        // NO: the check for file is ALREADY done in "newProject(...)"
        //
        // if (projectHome.isFile()) {
        //     try {
        //         Map<String, Object> data = JSONUtils.load(projectHome, HashMap.class);
        //         projectLanguage = MapUtils.getString(data, "properties", PROJECT_LANGUAGE);
        //         return StringUtils.isEmpty(projectLanguage) ? JAVA : projectLanguage;
        //     } catch (IOException e) {
        //         throw new ProjectException(e);
        //     }
        // }

        // the map supports 'default values'
        Map<String, AtomicInteger> extCounts = FileUtils.countExtensions(projectHome);

        Map<String, Integer> sourcesCount = MapUtils.asMap(
            JAVA,   extCounts.get(JAVA_EXT).get(),
            PYTHON, extCounts.get(PYTHON_EXT).get(),
            CSHARP, extCounts.get(CSHARP_EXT).get()
        );

        // the programming languages is selected based on the number of files with the
        // specific extension.
        // IF there are no source files (all counts are 0) 'argMax' returns 'null'
        projectLanguage = argMax(sourcesCount);
        if (projectLanguage == null)
            throw new ProjectException("Unable to guess the programming language used for " + projectHome);

        // save/replace the programming language in the properties
        properties.put(PROJECT_LANGUAGE, projectLanguage);
        return projectLanguage;
    }

    private static String argMax(Map<String, Integer> sourceCounts) {
        String name = null;
        int value = -1;
        for (Map.Entry<String, Integer> entry : sourceCounts.entrySet()) {
            if (entry.getValue() > value) {
                name = entry.getKey();
                value = entry.getValue();
            }
        }

        return value > 0 ? name : null;
    }

}
