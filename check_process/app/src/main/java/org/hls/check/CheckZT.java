package org.hls.check;

import jext.util.logging.LogManager;
import org.zeroturnaround.exec.ProcessExecutor;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class CheckZT {
    public static void main(String[] args) throws InterruptedException, TimeoutException, IOException {
        LogManager.configure(new File("logging.properties"));

        System.out.println("Hello Cruel World");

        new ProcessExecutor()
                .command("gradle.bat", "--version")
                .redirectOutput(System.out)
                .redirectError(System.err)
                .execute();
    }
}
