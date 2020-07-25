package jext.buildtools.util;

import java.io.FileFilter;

public class FileFilters {

    public static final FileFilter IS_JAVA = file -> file.getName().endsWith(".java");
    public static final FileFilter IS_JAR = file -> file.getName().endsWith(".jar");
}
