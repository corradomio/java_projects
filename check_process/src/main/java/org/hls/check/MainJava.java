package org.hls.check;

import java.io.File;
import java.io.IOException;

public class MainJava {

    public static void main(String[] args) throws IOException, InterruptedException {
        new ProcessBuilder()
                .redirectOutput(new File("out.txt"))
                .redirectError(new File("err.txt"))
                .command("gradle.cmd", "projects")
                .directory(new File("D:\\Projects.github\\other_projects\\spring-framework"))
                .start().waitFor();
    }
}
