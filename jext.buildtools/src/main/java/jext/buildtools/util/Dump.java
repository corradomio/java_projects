package jext.buildtools.util;

import jext.buildtools.Project;
import jext.buildtools.maven.MavenCoords;
import jext.buildtools.maven.MavenModule;

import java.util.List;

public class Dump {

    public static void project(Project project) {
        System.out.printf("Project %s\n", project.getName());
        project.getModules().forEach(module -> {
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
    }
}
