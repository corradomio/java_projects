package jext.scitools;

import java.io.IOException;

public class SciTools {

    private static final String UNDERSTAND = "com.scitools.understand.Understand";
    private static final String UND_APP = "und";

    public static boolean isPresent() {
        try {
            // check if the class 'com.scitools.understand.Understand' can be loaded
            Class.forName(UNDERSTAND);
            // check if the command line tool 'und help' can be executed
            Process p = Runtime.getRuntime().exec(UND_APP + " help");
            p.waitFor();
            return true;
        } catch (ClassNotFoundException | IOException | InterruptedException e) {
            return false;
        }
    }
}
