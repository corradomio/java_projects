package jext.buildtools.util;

import jext.buildtools.Project;
import jext.io.Console;

public class ProjectDump {

    public static void dump(Project project) {
        new ProjectDump().dumpProject(project);
    }

    private void dumpProject(Project p) {
        Console.printf("Project '%s': %s\n", p.getName(), p.getDirectory());
        Console.printf("  modules:\n");
        p.getModules().forEach(m -> {
            Console.printf("    '%s'\n", m.getName());
            if (!m.getSources().isEmpty()) {
                Console.printf("      sources (%d):\n", m.getSources().size());
                m.getSources().getRoots().forEach(root -> {
                    Console.printf("        %s (%d)\n", root, m.getSources().getSources(root).size());
                });
            }
        });
    }
}
