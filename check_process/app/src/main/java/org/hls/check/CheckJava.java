package org.hls.check;

import java.io.File;
import java.io.IOException;

public class CheckJava {

    public static void main(String[] args) throws IOException, InterruptedException {

        ProcessBuilder.Redirect r;

        new ProcessBuilder()
            // File or Redirect
            // .redirectOutput(new File("out.txt"))
            // .redirectError(new File("err.txt"))
            .redirectOutput(ProcessBuilder.Redirect.to(new File("out.txt")))
            .redirectError(ProcessBuilder.Redirect.to(new File("err.txt")))
            .command("gradle.bat", "projects")
            .directory(new File("D:\\Projects.github\\java_projects\\check_process"))
            .start().waitFor();
    }
}
