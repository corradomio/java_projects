package jext.buildtools.util;

import java.io.File;
import java.io.FileFilter;

public class FileFilters {

    public static final FileFilter IS_JAVA = file -> file.getName().endsWith(".java");
}
