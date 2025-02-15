package jext.process.scitools;

import jext.lang.OperatingSystem;
import jext.lang.OperatingSystemUtils;
import jext.util.logging.Logger;
import jext.process.OutputStreamToConsumer;
import jext.util.FileUtils;
import org.zeroturnaround.exec.ProcessExecutor;
import org.zeroturnaround.exec.ProcessResult;
import org.zeroturnaround.exec.StartedProcess;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

/**
 * Class used to check if SciTools 'und' command line application is correctly configured.
 * It is not necessary to check if the application is configured every time, but only
 * at the dependency analysis start.
 */
public abstract class SciTools {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    private static final String UNDERSTAND = "com.scitools.understand.Understand";

    // ----------------------------------------------------------------------
    // Global variables
    // ----------------------------------------------------------------------

    private static File installationDirectory = null;
    private static File pyappDirectory = null;
    private static OperatingSystem os = OperatingSystem.UNKNOWN;

    public static String undApp = "und";
    public static String upythonApp = "upython";

    private static boolean isInstallationValid;

    private static Logger logger = Logger.getLogger(SciTools.class);

    // ----------------------------------------------------------------------
    // Local variables
    // ----------------------------------------------------------------------

    private List<Consumer<String>> messageHandlers = new ArrayList<>();

    // ----------------------------------------------------------------------
    // Configuration
    // ----------------------------------------------------------------------

    public static void setInstallationDirectory(String homeDirectory) {
        File installationDirectory = null;
        checkOs();
        dumpEnvironment();

        if (homeDirectory != null)
            installationDirectory = new File(homeDirectory);
        else
            installationDirectory = guessInstallationDirectory();

        setInstallationDirectory(installationDirectory);
    }

    public static void setInstallationDirectory(File homeDirectory) {
        SciTools.installationDirectory = homeDirectory;
        setApps();
    }

    private static File guessInstallationDirectory() {
        File undPath = locateUnd();
        if (undPath == null)
            return null;

        // Windows:  installationDirectory, "bin/pc-win64/und.exe"
        // Linux:    installationDirectory, "bin/linux64/und"
        // MacOS:    installationDirectory, "Contents/MacOS/und"
        return undPath.getParentFile().getParentFile().getParentFile();
    }

    public static void setPythonAppDirectory(File pyappDirectory) {
        SciTools.pyappDirectory = pyappDirectory;
    }

    // ----------------------------------------------------------------------

    public static void checkInstallation() {
        if (SciTools.os == OperatingSystem.UNKNOWN) {
            logger.errorf("SciTools not installed: unknown operating system");
            return;
        }
        if (SciTools.installationDirectory == null) {
            logger.errorf("SciTools not installed: installation directory not defined");
            return;
        }
        if (!SciTools.installationDirectory.exists()) {
            logger.errorf("SciTools not installed: invalid directory '%s'",
                FileUtils.getAbsolutePath(SciTools.installationDirectory));
            return;
        }
        if (SciTools.pyappDirectory == null) {
            logger.errorf("SciTools not installed: SPL 'python app' directory not defined");
            return;
        }
        if (!SciTools.pyappDirectory.exists()) {
            logger.errorf("SciTools not installed: invalid SPL 'python app' '%s'",
                FileUtils.getAbsolutePath(SciTools.pyappDirectory));
            return;
        }
        File mainpy = new File(pyappDirectory, "main.py");
        if (!mainpy.exists()) {
            logger.errorf("SciTools not installed: SPL 'python app' 'main.py' not found at'%s'",
                FileUtils.getAbsolutePath(mainpy));
            return;
        }
        try {
            Class.forName(UNDERSTAND);
        } catch (ClassNotFoundException e) {
            logger.errorf("SciTools not installed: missing '%s' Java class", UNDERSTAND);
            return;
        }
        try {
            Process p = Runtime.getRuntime().exec(undApp + " help");
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            logger.errorf("SciTools not installed: unable to execute 'und help': %s", e);
            return;
        }
        try {
            Process p = Runtime.getRuntime().exec(upythonApp + " -c 'print()'");
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            logger.errorf("SciTools not installed: unable to execute 'upython -c 'print()': %s", e);
            return;
        }

        // Valid installation
        {
            logger.infof("SciTool installed:");
            logger.infof("    OS           : %s", SciTools.os);
            logger.infof("    installation : %s", FileUtils.getAbsolutePath(SciTools.installationDirectory));
            logger.infof("    und          : %s", SciTools.undApp);
            logger.infof("    upython      : %s", SciTools.upythonApp);
            logger.infof("    SPL app      : %s", FileUtils.getAbsolutePath(SciTools.pyappDirectory));
            logger.infof("end");
            isInstallationValid = true;
        }
    }

    private static void checkOs() {
        SciTools.os = OperatingSystemUtils.getOperatingSystem();
    }

    private static void setApps() {
        if (installationDirectory == null)
            return;

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
    }

    // ----------------------------------------------------------------------

    /**
     * Check if the SciTools 'und' command line application is correctly configured
     * @return true if the application is configured, false otherwise.
     */
    public static boolean isAvailable() {
        Process p;
        try {
            // If the installation is not valid, it is not useful to continue with
            // other tests.
            if (!isInstallationValid)
                return false;

            // The installation WAS valid.
            // Check if it is yet valid.

            if (os == OperatingSystem.UNKNOWN)
                throw new IOException("Unknown operating system");
            if (installationDirectory == null || !installationDirectory.exists() ||
                pyappDirectory == null || !pyappDirectory.exists())
                throw new IOException("Invalid installation or pyapp directories");

            File mainpy = new File(pyappDirectory, "main.py");
            if (!mainpy.exists())
                throw new FileNotFoundException(FileUtils.getAbsolutePath(mainpy));

            return true;
        } catch (/* ClassNotFoundException | */ IOException /* | InterruptedException */ e) {
            logger.error("SciTools not correctly configured", e);
            return false;
        }
    }

    // ----------------------------------------------------------------------
    // Create an instance
    // ----------------------------------------------------------------------

    private static class SciToolsUnd extends SciTools {

        @Override
        public void exec(String... args) throws IOException {
            super.und(args);
        }

        @Override
        public void exec(Collection<String> args) throws IOException {
            super.und(args);
        }

        @Override
        public StartedProcess start(String... sargs) throws IOException {
            Collection<String> uargs = Arrays.asList(sargs);

            logger.infof("und %s", uargs.toString());
            List<String> args = new ArrayList<>();
            args.add(undApp);
            args.addAll(uargs);

            ProcessExecutor pe = new ProcessExecutor();
            return pe.command(args)
                .redirectOutput(new OutputStreamToConsumer(this::handleMessage))
                .environment("PATH", "")
                .environment("JAVA_HOME", "")
                .timeout(3, TimeUnit.SECONDS)
                .start()
                ;
        }

    }

    private static class SciToolsUPython extends SciTools {

        @Override
        public void exec(String... args) throws IOException {
            int n = args.length;
            String[] rest = new String[n-1];
            System.arraycopy(args, 1, rest, 0, n-1);
            super.upython(args[0], rest);
        }

        @Override
        public void exec(Collection<String> args) throws IOException {
            int n = args.size();
            List<String> largs = new ArrayList<>(args);
            super.upython(largs.get(0), largs.subList(1, n));
        }

        @Override
        public StartedProcess start(String... sargs) throws IOException {
            String command = sargs[0];
            List<String> uargs = Arrays.asList(sargs);
            uargs = uargs.subList(1, uargs.size());
            // compose:  <scitools-dir>/upython <splpython-dir>/main.py arg1 ...
            List<String> args = new ArrayList<>();
            args.add(upythonApp);
            args.add(String.format("%s/%s", pyappDirectory, command));

            args.addAll(uargs);

            logger.infof("upython %s", args.toString());

            ProcessExecutor pe = new ProcessExecutor().directory(pyappDirectory);
            return pe.command(args)
                .redirectOutput(new OutputStreamToConsumer(this::handleMessage))
                .start();
        }
    }

    public static SciTools und() {
        return new SciToolsUnd();
    }

    public static SciTools upython() {
        return new SciToolsUPython();
    }

    public static SciTools upython(Consumer<String> consumer) {
        return upython().consume(consumer);
    }

    public SciTools consume(Consumer<String> consumer) {
        this.messageHandlers.add(consumer);
        return this;
    }

    public abstract void exec(String... args) throws IOException;

    public abstract void exec(Collection<String> args) throws IOException;

    public abstract StartedProcess start(String... args) throws IOException;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private SciTools() {
        messageHandlers.add(this::log);
    }

    private void log(String message) {

        if (message.startsWith("WARNING"))
            logger.warn(message.substring(7+1));
        else if (message.startsWith("WARN"))
            logger.warn(message.substring(4+1));
        else if (message.startsWith("ERROR"))
            logger.error(message.substring(5+1));
        else if (message.startsWith("INFO"))
            logger.info(message.substring(4+1));
        else if (message.startsWith("EVENT task"))
            logger.info(message);
        else if (message.startsWith("EVENT"))
            logger.debug(message);
        else if (message.contains("Warning:")) {
            logger.warn(message);
        }
        else if (message.contains("Error:")) {
            logger.error(message);
        }
        else
            logger.debug(message);
    }

    // ----------------------------------------------------------------------
    // Und execute commands
    // ----------------------------------------------------------------------

    protected /* static */ void und(String... uargs) throws IOException {
        und(Arrays.asList(uargs));
    }

    protected /* static */ void und(Collection<String> uargs) throws IOException {
        logger.infof("und %s", uargs.toString());
        try {
            List<String> args = new ArrayList<>();
            args.add(undApp);
            args.addAll(uargs);

            ProcessExecutor pe = new ProcessExecutor();
            ProcessResult pr = pe.command(args)
                .redirectOutput(new OutputStreamToConsumer(this::handleMessage))
                .environment("PATH", "")
                .environment("JAVA_HOME", "")
                .timeout(3, TimeUnit.SECONDS)
                .executeNoTimeout()
                ;

            if (pr.getExitValue() != 0)
                logger.errorf("Und process terminated with code %d", pr.getExitValue());

            // just to be sure that the process is terminated
        } catch (InterruptedException e) {
            throw new IOException(e);
        }
    }

    // ----------------------------------------------------------------------
    // Upython execute commands
    // ----------------------------------------------------------------------

    protected /* static */ void upython(String command, String... pargs) throws IOException {
        upython(command, Arrays.asList(pargs));
    }

    protected /* static */ void upython(String command, Collection<String> uargs) throws IOException {
        try {
            // compose:  <scitools-dir>/upython <splpython-dir>/main.py arg1 ...
            List<String> args = new ArrayList<>();
            args.add(upythonApp);
            args.add(String.format("%s/%s", pyappDirectory, command));

            args.addAll(uargs);

            logger.infof("upython %s", args.toString());

            ProcessExecutor pe = new ProcessExecutor().directory(pyappDirectory);
            ProcessResult pr = pe.command(args)
                .redirectOutput(new OutputStreamToConsumer(this::handleMessage))
                .execute();

            if (pr.getExitValue() != 0)
                throw new IOException(String.format("Python process terminated with code %d", pr.getExitValue()));

        } catch (InterruptedException | TimeoutException e) {
            throw new IOException(e);
        }
    }

    // ----------------------------------------------------------------------
    // Handle messages
    // ----------------------------------------------------------------------

    protected void handleMessage(String message) {
        this.messageHandlers.forEach(mh -> mh.accept(message));
    }

    // ----------------------------------------------------------------------
    // Locate und
    // ----------------------------------------------------------------------

    private static void dumpEnvironment() {
        ProcessExecutor pe = new ProcessExecutor();
        ByteArrayOutputStream outs = new ByteArrayOutputStream();
        ByteArrayOutputStream errs = new ByteArrayOutputStream();
        ProcessResult pr = null;

        try {
            switch (SciTools.os) {
                case WINDOWS:
                    pr = pe.command("cmd", "/c", "set")
                        .redirectOutput(outs)
                        .redirectError(errs)
                        .executeNoTimeout();
                    break;
                case LINUX:
                    pr = pe.command("printenv")
                        .redirectOutput(outs)
                        .redirectError(errs)
                        .executeNoTimeout();
                    break;
                case MACOS:
                    throw new IOException("MacOS: unsupported operating system");
                default:
                    throw new IOException("Unsupported operating system");
            }

            String env = (outs.toString().trim() + "\n" + errs.toString().trim()).trim();
            logger.warn(env);
        }
        catch (Exception e) {
            // none to do
        }
    }

    private static File locateUnd() {
        ProcessExecutor pe = new ProcessExecutor();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ProcessResult pr = null;

        try {
            switch (SciTools.os) {
                case WINDOWS:
                    pr = pe.command("cmd", "/c", "where", "und.exe")
                        .redirectOutput(baos)
                        .executeNoTimeout();
                    break;
                case MACOS:
                    pr = pe.command("which", "und")
                        .redirectOutput(baos)
                        .executeNoTimeout();
                    break;
                case LINUX:
                    pr = pe.command("bash", "which", "und")
                        .redirectOutput(baos)
                        .executeNoTimeout();
                    break;
                default:
                    throw new IOException("Unsupported operating system");
            }
            if (pr == null)
                throw new IOException("Unable to start 'shell' process");

            int ev = pr.getExitValue();
            if (ev != 0)
                throw new IOException(String.format("Shell process existed with error: %s (%d)",
                    pr.outputString(), pr.getExitValue()));

            String whereUnd = baos.toString().trim();
            if (whereUnd.length() == 0)
                throw new IOException("empty string");
            File undPath = new File(whereUnd);
            if (!undPath.exists())
                throw new IOException("Invalid path " + undPath);

            return undPath;
        }
        catch (Exception e) {
            logger.errorf("Unable to find 'und' executable: %s", e);
            return null;
        }
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
