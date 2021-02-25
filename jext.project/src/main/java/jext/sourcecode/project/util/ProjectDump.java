package jext.sourcecode.project.util;

import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.Project;
import jext.util.FileUtils;

import java.io.PrintStream;

public class ProjectDump {

    public static void dump(Project project) {
        new ProjectDump().dumpProject(project, System.out);
    }

    private void dumpProject(Project p, PrintStream Console) {
        Console.printf("Project '%s' (%s)\n", p.getName(), p.getProjectType());
        Console.printf("    home %s\n", p.getProjectHome());

        Console.printf("  modules (%d):\n", p.getModules().size());
        p.getModules().forEach(m -> {
            Console.printf("    module '%s' (%s)\n", m.getName(), m.getId());
        });

        Console.printf("  libraries (%d):\n", p.getLibraries().size());
        p.getLibraries().forEach(l-> {
            LibraryType ltype = l.getLibraryType();
            switch(ltype) {
                case LOCAL:
                    Console.printf("    local   %s (%s)\n", l.getName(), l.getId()); break;
                case MAVEN:
                    Console.printf("    maven   %s (%s)\n", l.getName(), l.getId()); break;
                case RUNTIME:
                    Console.printf("    runtime %s (%s)\n", l.getName(), l.getId()); break;
                default:
                    Console.printf("    library %s (%s)\n", l.getName(), l.getId()); break;
            }
        });

        Console.printf("  module details:\n", p.getModules().size());
        p.getModules().forEach(m -> {
            Console.printf("    module '%s' (%s)\n", m.getName(), m.getId());
            Console.printf("        home '%s'\n", m.getModuleHome());
            if (!m.getSourceRoots().isEmpty()) {
                Console.printf("        sroots '%s'\n", m.getModuleHome());
                m.getSourceRoots().forEach(sr -> {
                    Console.printf("          %s\n", FileUtils.relativePath(m.getModuleHome(), sr));
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
                    Console.printf("        %s\n", library.getName());
                });
            }
            if (!m.getResources().isEmpty()) {
                Console.printf("      resources (%d):\n", m.getResources().size());
                // m.getResources().forEach(resource -> {
                //     Console.printf("        %s\n", resource);
                // });
            }
            if (!m.getDependencies().isEmpty()) {
                Console.printf("      dependencies (%d):\n", m.getDependencies().size());
                m.getDependencies().forEach(d -> {
                    Console.printf("        '%s'\n", d.getName());
                });
            }
        });

        Console.printf("end\n");
    }
}
