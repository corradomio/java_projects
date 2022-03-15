package jext.scitools;

import org.zeroturnaround.exec.ProcessExecutor;
import org.zeroturnaround.exec.ProcessResult;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;

public class SciTools {

    private enum OperatingSystem {
        UNKNOWN,
        WINDOWS,
        LINUX,
        MACOS
    }

    private static OperatingSystem os = OperatingSystem.UNKNOWN;

    private static class StreamGobbler implements Runnable {
        private InputStream inputStream;
        private Consumer<String> consumer;

        public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
            this.inputStream = inputStream;
            this.consumer = consumer;
        }

        @Override
        public void run() {
            new BufferedReader(new InputStreamReader(inputStream)).lines()
                    .forEach(consumer);
        }
    }

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

        // logger.infof("Operating system: %s", SciTools.os);
    }


    public static String locateExecutable(String exe) throws IOException, InterruptedException {
        ProcessExecutor pe = new ProcessExecutor();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ProcessResult pr;

        checkOs();
        switch(SciTools.os) {
            case WINDOWS:
                //process = Runtime.getRuntime().exec(String.format("cmd.exe /c where %s", exe));
                pr = pe.command("cmd", "/c", "where", exe)
                        .redirectOutput(baos)
                        .executeNoTimeout();
                break;
            case LINUX:
                //process = Runtime.getRuntime().exec(String.format("sh -c which %s", exe));
                pr = pe.command("bash", "which", exe)
                        .redirectOutput(baos)
                        .executeNoTimeout();
                break;
            case MACOS:
                throw new IOException("MacOS: unsupported operating system");
        }

        return baos.toString().trim();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.printf("'%s'", locateExecutable("und"));
    }
}
