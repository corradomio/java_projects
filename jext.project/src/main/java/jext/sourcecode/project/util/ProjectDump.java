package jext.sourcecode.project.util;

import jext.sourcecode.project.Project;
import jext.io.Console;

public class ProjectDump {

    public static void dump(Project project) {
        new ProjectDump().dumpProject(project);
    }

    private void dumpProject(Project p) {
        Console.printf("Project '%s'\n", p.getName());
        Console.printf("    type %s\n", p.getProjectType(), p.getProjectHome());
        Console.printf("    home %s\n", p.getProjectHome());

        Console.printf("  modules (%d):\n", p.getModules().size());
        p.getModules().forEach(m -> {
            Console.printf("    module '%s' (%s)\n", m.getName(), m.getId());
        });

        Console.printf("  module details:\n", p.getModules().size());
        p.getModules().forEach(m -> {
            Console.printf("    module '%s' (%s)\n", m.getName(), m.getId());
            Console.printf("        home '%s'\n", m.getModuleHome());
            // if (!m.getSourceRoots().isEmpty()) {
            //     Console.printf("      sources (%d):\n", m.getSourceRoots().size());
            //     // m.getSources().getRoots().forEach(root -> {
            //     //     Console.printf("        %s (%d)\n", root, m.getSources().getSources(root).size());
            //     // });
            // }
            if (!m.getSources().isEmpty()) {
                Console.printf("      sources (%d):\n", m.getSources().size());
                // m.getSources().getRoots().forEach(root -> {
                //     Console.printf("        %s (%d)\n", root, m.getSources().getSources(root).size());
                // });
            }
            if (!m.getLibraries().isEmpty()) {
                Console.printf("      libraries (%d):\n", m.getLibraries().size());
                m.getLibraries().forEach(library -> {
                    Console.printf("        %s\n", library.getName());
                });
            }
            if (!m.getResources().isEmpty()) {
                Console.printf("      resources (%d):\n", m.getResources().size());
                // m.getResources().forEach(resource -> {
                //     Console.printf("        %s\n", resource);
                // });
            }
            if (!m.getDependencies(false).isEmpty()) {
                Console.printf("      dependencies (%d):\n", m.getDependencies(false).size());
                m.getDependencies(false).forEach(d -> {
                    Console.printf("        '%s'\n", d.getName());
                });
            }
        });

        Console.printf("end\n");
    }
}
