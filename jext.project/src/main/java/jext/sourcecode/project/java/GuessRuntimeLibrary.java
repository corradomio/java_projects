package jext.sourcecode.project.java;

import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import org.gradle.internal.impldep.org.apache.commons.lang.StringUtils;

import java.util.Optional;
import java.util.Set;

public class GuessRuntimeLibrary {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    public static final String NO_RUNTIME_LIBRARY = "";
    public static final String DEFAULT_JAVA_RUNTIME_LIBRARY = "jdk11";

    private static final String DEFAULT_ANDROID_RUNTIME_LIBRARY = "pie";
    private static final String ANDROID_NS  ="android.";
    private static final String ANDROIDX_NS  ="androidx.";

    // ----------------------------------------------------------------------
    // Static method
    // ----------------------------------------------------------------------

    public static String guessRuntimeLibrary(Module module) {

        Optional<String> runtimeName;
        GuessRuntimeLibrary grtl = new GuessRuntimeLibrary(module);

        runtimeName = grtl.checkConfiguration();
        if (runtimeName.isPresent())
            return runtimeName.get();

        runtimeName = grtl.checkAndroidProject();
        if (runtimeName.isPresent())
            return runtimeName.get();

        runtimeName = grtl.checkJavaProject();
        if (runtimeName.isPresent())
            return runtimeName.get();
        else
            return NO_RUNTIME_LIBRARY;
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

    private Optional<String> checkConfiguration() {
        Project project = module.getProject();
        String runtimeLibrary = module.getProperties().getProperty(Project.RUNTIME_LIBRARY, NO_RUNTIME_LIBRARY);
        if (StringUtils.isEmpty(runtimeLibrary))
            runtimeLibrary = project.getProperties().getProperty(Project.RUNTIME_LIBRARY, NO_RUNTIME_LIBRARY);
        return Optional.of(runtimeLibrary);
    }

    private Optional<String> checkAndroidProject() {
        int androidCount = 0;
        Set<RefType> usedTypes = module.getUsedTypes();
        for (RefType refType : usedTypes) {
            if (refType.getName().getFullName().startsWith(ANDROID_NS) ||
                refType.getName().getFullName().startsWith(ANDROIDX_NS) )
                androidCount += 1;
        }

        if (androidCount > 0)
            return Optional.of(DEFAULT_ANDROID_RUNTIME_LIBRARY);
        else
            return Optional.empty();
    }

    private Optional<String> checkJavaProject() {
        return Optional.empty();
    }
}
