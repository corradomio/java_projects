package jext.buildtools.gradle.util;

import jext.buildtools.Name;

public class GradleUtils {

    public static String toTask(Name name, String taskName) {
        String moduleName = name.toString().replace('/',':');
        if (moduleName.isEmpty())
            return taskName;
        else
            return String.format("%s:%s", moduleName, taskName);
    }
}
