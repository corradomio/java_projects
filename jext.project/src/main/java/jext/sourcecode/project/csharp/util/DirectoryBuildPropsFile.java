package jext.sourcecode.project.csharp.util;

import java.io.File;

public class DirectoryBuildPropsFile extends CSharpProjectFile {

    public static DirectoryBuildPropsFile props(File projectHome) {
        return new DirectoryBuildPropsFile(projectHome, new File(projectHome, DIRECTORY_BUILD + PROPS));
    }
    public static DirectoryBuildPropsFile targets(File projectHome) {
        return new DirectoryBuildPropsFile(projectHome, new File(projectHome, DIRECTORY_BUILD + TARGETS));
    }
    public static DirectoryBuildPropsFile props(File projectHome, File moduleHome) {
        return new DirectoryBuildPropsFile(projectHome, new File(moduleHome, DIRECTORY_BUILD + PROPS));
    }
    public static DirectoryBuildPropsFile targets(File projectHome, File moduleHome) {
        return new DirectoryBuildPropsFile(projectHome, new File(moduleHome, DIRECTORY_BUILD + TARGETS));
    }


    private static final String PROPS = ".props";
    private static final String TARGETS = ".targets";
    private static final String DIRECTORY_BUILD = "Directory.Build";

    private DirectoryBuildPropsFile(File projectHome, File directoryBuildFile) {
        super(projectHome, directoryBuildFile);
    }
}
