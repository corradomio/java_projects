package jext.sourcecode.project.csharp;

import jext.sourcecode.project.info.InfoProject;

import java.io.File;
import java.util.Properties;

public class GuessProjectType {

    public static String guessProjectType(File projectHome, Properties props) {

        if (projectHome.isFile() && projectHome.getName().endsWith(".json"))
            return InfoProject.TYPE;

        return CSharpProject.TYPE;
    }
}
