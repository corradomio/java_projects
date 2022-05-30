package jext.sourcecode.project;

import jext.java.JavaConstants;
import jext.util.FileUtils;
import jext.util.JSONUtils;
import jext.util.MapUtils;
import jext.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import static jext.sourcecode.project.Project.AUTO;
import static jext.sourcecode.project.Project.PROJECT_LANGUAGE;

public class GuessProjectLanguage {

    public static final String JAVA_LANGUAGE = "java";
    public static final String PYTHON_LANGUAGE = "python";
    public static final String UNKNOWN_LANGUAGE = "python";

    private static final String EXT_JAVA = ".java";
    private static final String EXT_PYTHON = ".py";

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
     * @param props project properties
     * @return programming language used in the project
     */
    public static String guessProjectLanguage(File projectHome, Properties props) {
        String projectLanguage = props.getProperty(PROJECT_LANGUAGE, AUTO);
        if (!StringUtils.isEmpty(projectLanguage) && !AUTO.equals(projectLanguage))
            return projectLanguage;

        // if projectHome is not a file or a directory, return a default language
        if (!projectHome.exists())
            return JavaConstants.JAVA;

        // projectHome can be a 'JSON' file
        if (projectHome.isFile()) {
            try {
                Map<String, Object> data = JSONUtils.load(projectHome, HashMap.class);
                projectLanguage = MapUtils.getString(data, "properties", PROJECT_LANGUAGE);
                return StringUtils.isEmpty(projectLanguage) ? JavaConstants.JAVA : projectLanguage;
            } catch (IOException e) {
                throw new ProjectException(e);
            }
        }

        // it supports 'default values'
        Map<String, AtomicInteger> extCounts = FileUtils.countExtensions(projectHome);

        int javaCount   = extCounts.get(EXT_JAVA  ).get();
        int pythonCount = extCounts.get(EXT_PYTHON).get();

        // if more programming languages will be used, the alternative implementation
        // is to ORDER in decreased order the source files, and to select the highest
        // programming language.

        if (javaCount > pythonCount)
            projectLanguage = JAVA_LANGUAGE;
        else if (pythonCount > 0)
            projectLanguage = PYTHON_LANGUAGE;
        else
            throw new ProjectException("Unable to guess the programming language used for " + projectHome);

        props.put(PROJECT_LANGUAGE, projectLanguage);
        return projectLanguage;
    }

}
