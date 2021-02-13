package jext.sourcecode.project;

import java.util.Set;

public class GuessRuntimeLibrary {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    public static final String DEFAULT_JAVA_RUNTIME_LIBRARY = "jdk8";
    private static final String DEFAULT_ANDROID_RUNTIME_LIBRARY = "pie";
    private static final String ANDROID_NS  ="android.";
    private static final String ANDROIDX_NS  ="androidx.";

    // ----------------------------------------------------------------------
    // Static method
    // ----------------------------------------------------------------------


    public static String guessRuntimeLibrary(Module module) {

        String runtimeName;
        GuessRuntimeLibrary grtl = new GuessRuntimeLibrary(module);

        runtimeName = grtl.checkConfiguration();
        if (runtimeName != null)
            return runtimeName;

        runtimeName = grtl.checkAndroidProject();
        if (runtimeName != null)
            return runtimeName;

        runtimeName = grtl.checkJavaProject();
        if (runtimeName != null)
            return runtimeName;

        return DEFAULT_JAVA_RUNTIME_LIBRARY;
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private Module module;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private GuessRuntimeLibrary(Module module) {
        this.module = module;
    }

    private String checkConfiguration() {
        String runtimeLibrary = module.getProperties().getProperty(Project.RUNTIME_LIBRARY, null);
        if (runtimeLibrary == null)
            runtimeLibrary = module.getProject().getProperties().getProperty(Project.RUNTIME_LIBRARY, null);
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
            return null;
    }

    private String checkJavaProject() {
        return null;
    }
}
