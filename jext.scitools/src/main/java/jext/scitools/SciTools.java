package jext.scitools;

import org.apache.logging.log4j.LogManager;

import java.io.IOException;

/**
 * Class used to check if SciTools 'un' command line application is correctly configured.
 * It is not necessary to check if the application is configured every time, but only
 * at the dependency analysis start.
 */
public abstract class SciTools {

    private static final String UNDERSTAND = "com.scitools.understand.Understand";
    private static String undApp = "und";

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    /**
     * Set the path of SciTools 'und' command line application
     *
     * @param undApp full path of SciTools 'und' commadn line application
     * @throws IllegalArgumentException if it the external library is not configured
     *      or the path is invalid
     */
    public static void undApp(String undApp) throws IllegalArgumentException {
        try {
            // check if the class 'com.scitools.understand.Understand' can be loaded
            Class.forName(UNDERSTAND);
            // check if the command line tool 'und help' can be executed
            Process p = Runtime.getRuntime().exec(undApp + " help");
            p.waitFor();
        } catch (ClassNotFoundException | IOException | InterruptedException e) {
            throw new IllegalArgumentException("Unable to set SciTools 'und' app", e);
        }
    }

    /**
     * SciTools 'und' application path  (based on PATH, or the absolute path)
     * @return the 'und' application path
     */
    public static String undApp() {
        return undApp;
    }

    /**
     * Check if the SciTools 'und' command line application is correctly configured
     * @return true if the application is configured, false otherwise.
     */
    public static boolean isPresent() {
        try {
            // check if the class 'com.scitools.understand.Understand' can be loaded
            Class.forName(UNDERSTAND);
            // check if the command line tool 'und help' can be executed
            Process p = Runtime.getRuntime().exec(undApp + " help");
            p.waitFor();
            return true;
        } catch (ClassNotFoundException | IOException | InterruptedException e) {
            LogManager.getLogger(SciTools.class).error("SciTools 'und' application not configured", e);
            return false;
        }
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
