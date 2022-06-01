package jext.sourcecode.project.csharp;

import jext.sourcecode.project.Module;

public class GuessRuntimeLibrary {

    public static final String NO_RUNTIME_LIBRARY = "";
    public static final String DEFAULT_RUNTIME_LIBRARY = ".NET Core 6";

    public static String guessRuntimeLibrary(Module module) {
        return DEFAULT_RUNTIME_LIBRARY;
    }
}
