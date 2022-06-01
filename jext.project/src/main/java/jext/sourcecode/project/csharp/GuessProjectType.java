package jext.sourcecode.project.csharp;

import java.io.File;
import java.util.Properties;

public class GuessProjectType {

    public static String guessProjectType(File projectHome, Properties props) {
        return CSharpProject.TYPE;
    }
}
