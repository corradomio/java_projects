package jext.scitools;

import jext.logging.Logger;
import jext.scitools.util.OutputStreamToLogger;
import jext.util.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.zeroturnaround.exec.ProcessExecutor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Class used to check if SciTools 'un' command line application is correctly configured.
 * It is not necessary to check if the application is configured every time, but only
 * at the dependency analysis start.
 */
public abstract class SciTools {

    private static final String UNDERSTAND = "com.scitools.understand.Understand";

    private enum OperatingSystem {
        UNKNOWN,
        WINDOWS,
        LINUX,
        MACOS
    }

    private static File installationDirectory = new File(".");
    private static File pyappDirectory = new File("main.py");
    private static OperatingSystem os = OperatingSystem.UNKNOWN;

    private static String undApp = "und";
    private static String upythonApp = "upython";

    private static Logger logger = Logger.getLogger(SciTools.class);

    // ----------------------------------------------------------------------
    // Configuration
    // ----------------------------------------------------------------------

    public static void setInstallationDirectory(String homeDirectory) {
        if (homeDirectory != null)
            setInstallationDirectory(new File(homeDirectory));
    }

    public static void setInstallationDirectory(File homeDirectory) {
        SciTools.installationDirectory = homeDirectory;
        checkOs();
        setApps();
    }

    // ----------------------------------------------------------------------

    public static void setPythonAppDirectory(String pyappDirectory) {
        setPythonAppDirectory(new File(pyappDirectory));
    }

    public static void setPythonAppDirectory(File pyappDirectory) {
        SciTools.pyappDirectory = pyappDirectory;
    }

    // ----------------------------------------------------------------------

    private static void checkOs() {
        String osname = System.getProperty("os.name");
        if (osname.contains("Windows"))
            SciTools.os = OperatingSystem.WINDOWS;
        else if (osname.contains("Mac"))
            SciTools.os = OperatingSystem.MACOS;
        else if (osname.contains("Linux"))
            SciTools.os = OperatingSystem.LINUX;
        else
            SciTools.os = OperatingSystem.UNKNOWN;

        logger.infof("Operating system: %s", SciTools.os);
    }

    private static void setApps() {
        switch (os) {
            case WINDOWS:
                undApp = FileUtils.getAbsolutePath(new File(installationDirectory,"bin/pc-win64/und.exe"));
                upythonApp = FileUtils.getAbsolutePath(new File(installationDirectory,"bin/pc-win64/upython.exe"));
                break;
            case LINUX:
                undApp = FileUtils.getAbsolutePath(new File(installationDirectory, "bin/linux64/und"));
                upythonApp = FileUtils.getAbsolutePath(new File(installationDirectory,"bin/linux64/upython"));
                break;
            case MACOS:
                undApp = FileUtils.getAbsolutePath(new File(installationDirectory, "Contents/MacOS/und"));
                upythonApp = FileUtils.getAbsolutePath(new File(installationDirectory, "Contents/MacOS/upython"));
                break;
            default:
                undApp = "app";
                upythonApp = "upython";
        }

        logger.infof("und app    : %s", undApp);
        logger.infof("upython app: %s", upythonApp);
    }

    // ----------------------------------------------------------------------

    /**
     * Check if the SciTools 'und' command line application is correctly configured
     * @return true if the application is configured, false otherwise.
     */
    public static boolean isAvailable() {
        Process p;
        try {
            // check if the class 'com.scitools.understand.Understand' can be loaded
            Class.forName(UNDERSTAND);

            // check for 'und' application
            p = Runtime.getRuntime().exec(undApp + " help");
            p.waitFor();

            // check for 'upython' application
            p = Runtime.getRuntime().exec(upythonApp + " -c 'print()'");
            p.waitFor();

            File mainpy = new File(pyappDirectory, "main.py");
            if (!mainpy.exists())
                throw new FileNotFoundException(FileUtils.getAbsolutePath(mainpy));

            return true;
        } catch (ClassNotFoundException | IOException | InterruptedException e) {
            logger.error("SciTools not correctly configured", e);
            return false;
        }
    }

    // ----------------------------------------------------------------------
    // Und execute commands
    // ----------------------------------------------------------------------

    public static void und(String... uargs) throws IOException {
        und(Arrays.asList(uargs));
    }

    public static void und(Collection<String> uargs) throws IOException {
        logger.infof("und %s", uargs.toString());
        try {
            List<String> args = new ArrayList<>();
            args.add(undApp);
            args.addAll(uargs);

            ProcessExecutor pe = new ProcessExecutor();
            pe.command(args)
                    // .redirectOutput(new OutputStream() {
                    //     @Override
                    //     public void write(int b) {
                    //         System.out.write(b);
                    //     }
                    // })
                    .redirectOutput(new OutputStreamToLogger(logger))
                    .execute();
        } catch (InterruptedException | TimeoutException e) {
            throw new IOException(e);
        }
    }

    // ----------------------------------------------------------------------
    // Upython execute commands
    // ----------------------------------------------------------------------

    public static void upython(String command, String... pargs) throws IOException {
        upython(command, Arrays.asList(pargs));
    }

    public static void upython(String command, Collection<String> uargs) throws IOException {
        // logger.infof("upython %s", uargs.toString());
        try {
            // compose:  upython main.py arg1 ...
            List<String> args = new ArrayList<>();
            args.add(upythonApp);
            args.add(String.format(command, pyappDirectory));

            args.addAll(uargs);

            logger.infof("upython %s", args.toString());

            ProcessExecutor pe = new ProcessExecutor().directory(pyappDirectory);
            pe.command(args)
                    // .redirectOutput(new OutputStream() {
                    //     @Override
                    //     public void write(int b) {
                    //         System.out.write(b);
                    //     }
                    // })
                    .redirectOutput(new OutputStreamToLogger(logger))
                    .execute();
        } catch (InterruptedException | TimeoutException e) {
            throw new IOException(e);
        }
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
