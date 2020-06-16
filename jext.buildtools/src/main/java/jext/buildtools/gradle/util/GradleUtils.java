package jext.buildtools.gradle.util;

public class GradleUtils {

    public static String toTask(String moduleName, String taskName) {
        if (moduleName.isEmpty())
            return taskName;
        else
            return String.format("%s:%s", moduleName, taskName);
    }
}
