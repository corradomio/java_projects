package jext.io.util;

import java.io.FileFilter;

public class FileFilters {

    public static final FileFilter IS_JAR = file -> (file.getName().endsWith(".jar") || file.getName().endsWith(".aar"));
    public static final FileFilter IS_JMOD = file -> file.getName().endsWith(".jmod");
    public static final FileFilter IS_SPL = file -> file.getName().endsWith(".spl");

}
