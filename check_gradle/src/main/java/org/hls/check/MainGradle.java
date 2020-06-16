package org.hls.check;


import jext.buildtools.gradle.GradleProject;
import jext.logging.Logger;

import java.io.File;
import java.util.Properties;

public class MainGradle {

    public static void main(String[] args) throws Exception {
        Logger.configure();

        GradleProject p = new GradleProject(new File("D:\\Projects.github\\other_projects\\hibernate-orm"))
                .properties(new Properties(){{
            put(GradleProject.GRADLE_VERSION, "6.4");
        }});

        // p.analyzeStructure();

        // p.connect();

        p.getModules().forEach(module -> {
            System.out.printf("Module %s (%s)\n", module.getName(), module.isValid());
            System.out.println("... dmodules");
            module.getModuleDependencies()
                    .forEach(dep -> {
                        System.out.printf("... ... %s\n", dep);
                    });
            System.out.println("... dependencies");
            module.getDependencies()
                    .forEach(dep -> {
                        System.out.printf("... ... %s\n", dep);
                    });
        });
        System.out.println("Done");

        // p.close();
    }
}
