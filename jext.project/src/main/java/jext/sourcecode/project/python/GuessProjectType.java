package jext.sourcecode.project.python;

import jext.sourcecode.project.info.InfoProject;
import jext.sourcecode.project.python.simple.SimpleProject;

import java.io.File;
import java.util.Properties;

public class GuessProjectType {

    public static String guessProjectType(File projectHome, Properties props) {
        if (projectHome.isFile() && projectHome.getName().endsWith(".json"))
            return InfoProject.TYPE;

        return SimpleProject.TYPE;
    }
}
