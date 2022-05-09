package jext.sourcecode.project.java;

import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import org.gradle.internal.impldep.org.apache.commons.lang.StringUtils;

import java.util.Set;

public class JavaGuessRuntimeLibrary {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    public static final String DEFAULT_JAVA_RUNTIME_LIBRARY = "jdk11";
    public static final String NO_RUNTIME_LIBRARY = "";
    private static final String DEFAULT_ANDROID_RUNTIME_LIBRARY = "pie";
    private static final String ANDROID_NS  ="android.";
    private static final String ANDROIDX_NS  ="androidx.";

    // ----------------------------------------------------------------------
    // Static method
    // ----------------------------------------------------------------------

    public static String guessRuntimeLibrary(Module module) {

        String runtimeName;
        JavaGuessRuntimeLibrary grtl = new JavaGuessRuntimeLibrary(module);

        runtimeName = grtl.checkConfiguration();
        if (!StringUtils.isEmpty(runtimeName))
            return runtimeName;

        runtimeName = grtl.checkAndroidProject();
        if (!StringUtils.isEmpty(runtimeName))
            return runtimeName;

        runtimeName = grtl.checkJavaProject();
        if (!StringUtils.isEmpty(runtimeName))
            return runtimeName;

        return NO_RUNTIME_LIBRARY;
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private Module module;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private JavaGuessRuntimeLibrary(Module module) {
        this.module = module;
    }

    private String checkConfiguration() {
        Project project = module.getProject();
        String runtimeLibrary = module.getProperties().getProperty(Project.RUNTIME_LIBRARY, NO_RUNTIME_LIBRARY);
        if (StringUtils.isEmpty(runtimeLibrary))
            runtimeLibrary = project.getProperties().getProperty(Project.RUNTIME_LIBRARY, NO_RUNTIME_LIBRARY);
        return runtimeLibrary;
    }

    private String checkAndroidProject() {
        int androidCount = 0;
        Set<RefType> usedTypes = module.getUsedTypes();
        for (RefType refType : usedTypes) {
            if (refType.getName().getFullName().startsWith(ANDROID_NS) ||
                refType.getName().getFullName().startsWith(ANDROIDX_NS) )
                androidCount += 1;
        }

        if (androidCount > 0)
            return DEFAULT_ANDROID_RUNTIME_LIBRARY;
        else
            return NO_RUNTIME_LIBRARY;
    }

    private String checkJavaProject() {
        return NO_RUNTIME_LIBRARY;
    }
}
