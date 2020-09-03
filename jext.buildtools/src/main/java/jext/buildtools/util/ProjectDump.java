package jext.buildtools.util;

import jext.buildtools.Project;
import jext.io.Console;

public class ProjectDump {

    public static void dump(Project project) {
        new ProjectDump().dumpProject(project);
    }

    private void dumpProject(Project p) {
        Console.printf("Project '%s'::%s  [%s]\n", p.getName(), p.getProjectType(), p.getDirectory());

        Console.printf("  modules (%d):\n", p.getModules().size());
        p.getModules().forEach(m -> {
            Console.printf("    module '%s' (%s)\n", m.getName(), m.getId());
        });

        Console.printf("  module details:\n", p.getModules().size());
        p.getModules().forEach(m -> {
            Console.printf("    module '%s' (%s)\n", m.getName(), m.getId());
            Console.printf("           '%s'\n", m.getDirectory());
            if (!m.getDependencies(false).isEmpty()) {
                Console.printf("      dependencies (%d):\n", m.getDependencies(false).size());
                m.getDependencies(false).forEach(d -> {
                    Console.printf("        '%s'\n", d.getName());
                });
            }
            if (!m.getSources().isEmpty()) {
                Console.printf("      sources (%d):\n", m.getSources().size());
                // m.getSources().getRoots().forEach(root -> {
                //     Console.printf("        %s (%d)\n", root, m.getSources().getSources(root).size());
                // });
            }
            if (!m.getLibraries().isEmpty()) {
                Console.printf("      libraries (%d):\n", m.getLibraries().size());
                m.getLibraries().forEach(library -> {
                    Console.printf("        %s\n", library);
                });
            }
            if (!m.getResources().isEmpty()) {
                Console.printf("      resources (%d):\n", m.getResources().size());
                m.getResources().forEach(resource -> {
                    Console.printf("        %s\n", resource);
                });
            }
        });

        Console.printf("end\n");
    }
}
