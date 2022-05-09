package jext.sourcecode.project;

import jext.util.FileUtils;
import jext.util.StringUtils;

import java.io.File;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import static jext.sourcecode.project.Project.AUTO;
import static jext.sourcecode.project.Project.PROJECT_LANGUAGE;

public class GuessProjectLanguage {

    public static final String JAVA_PROJECT = "java";
    public static final String PYTHON_PROJECT = "python";

    private static final String EXT_JAVA = ".java";
    private static final String EXT_PYTHON = ".python";

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

        Map<String, AtomicInteger> extCounts = FileUtils.countExtensions(projectHome);
        int javaCount = getExtCounts(extCounts, EXT_JAVA);
        int pythonCount = getExtCounts(extCounts, EXT_PYTHON);

        // if more programming languages will be used, the alternative implementation
        // is to ORDER in decreased order the source files, and to select the highest
        // programming language.

        if (javaCount > pythonCount)
            projectLanguage = JAVA_PROJECT;
        else
            projectLanguage = PYTHON_PROJECT;

        props.put(PROJECT_LANGUAGE, projectLanguage);
        return projectLanguage;
    }

    private static int getExtCounts(Map<String, AtomicInteger> extCounts, String ext) {
        if (!extCounts.containsKey(ext))
            return 0;
        else
            return extCounts.get(ext).get();
    }

}
