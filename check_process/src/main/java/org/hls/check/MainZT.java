package org.hls.check;

import org.apache.log4j.BasicConfigurator;
import org.zeroturnaround.exec.ProcessExecutor;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MainZT {
    public static void main(String[] args) throws InterruptedException, TimeoutException, IOException {
        BasicConfigurator.configure();

        System.out.println("Hello Cruel World");

        new ProcessExecutor()
                .command("gradle.cmd", "--version")
                .redirectOutput(System.out)
                .redirectError(System.err)
                .execute();
    }
}
