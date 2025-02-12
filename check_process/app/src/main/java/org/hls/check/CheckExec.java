package org.hls.check;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.IOException;

public class CheckExec {

    public static void main(String[] args) throws IOException {
        CommandLine cmdLine = new CommandLine("gradle.bat");
        cmdLine.addArgument("--version");

        DefaultExecutor executor = DefaultExecutor.builder()
            .setExecuteStreamHandler(new PumpStreamHandler(System.out, System.err))
            .get();
        executor.execute(cmdLine);
    }
}
