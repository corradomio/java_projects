package jext.sourcecode.project.python;

import java.io.File;
import java.util.Properties;

public class GuessProjectType {

    public static String guessProjectType(File projectHome, Properties props) {
        return PythonProject.TYPE;
    }
}
