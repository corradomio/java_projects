package jext.sourcecode.resources.libraries;

import jext.lang.JavaUtils;
import jext.name.Name;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RuntimeLibrary;
import jext.util.MapUtils;

import java.io.File;
import java.util.Map;

public class JDKLibrary extends DirectoryLibrary implements RuntimeLibrary {

    public JDKLibrary(String libraryName, File libraryFile, Project project) {
        super(libraryName, libraryFile, project);
    }

    @Override
    public boolean contains(Name typeName) {
        if (JavaUtils.PRIMITIVE_TYPES.contains(typeName.getFullName()))
            return true;
        else
            return super.contains(typeName);
    }

    private static Map<String,String> ANDROID_VERSIONS = MapUtils.asMap(
        "honeycomb", "3.0.0",
        "icecream", "4.0.0",
        "jellybean", "4.1.0",
        "kitkat", "4.4.0",
        "lollipop", "5.0.0",
        "marshmallow", "6.0.0",
        "nougat", "7.0.0",
        "oreo", "8.0.0",
        "pie", "9.0.0"
    );

    @Override
    public String getVersion() {
        String name = getName().getName().toLowerCase();
        return getVersion(name);
    }

    public static String getVersion(String name) {

        if (name.startsWith("jdk"))
            return name.substring(3) + ".0.0";
        if (name.startsWith("adk"))
            return name.substring(3) + ".0.0";
        else
            return ANDROID_VERSIONS.getOrDefault(name, "");
    }

}
