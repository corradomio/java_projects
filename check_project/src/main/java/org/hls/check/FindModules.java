package org.hls.check;

import jext.logging.Logger;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Projects;

import java.io.File;
import java.util.Properties;

public class FindModules {

    public static void main(String[] args) {
        Logger.configure();

        Project p = Projects.newProject(new File("D:\\Projects.github\\java_projects\\check_code"), new Properties());
        p.getModules().forEach(module -> {
            System.out.printf("%s: %s\n", module.getName(), module.getModuleHome());
        });
    }
}
