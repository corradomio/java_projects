package jext.sourcecode.project.util;

import jext.sourcecode.project.Library;
import jext.sourcecode.project.Project;
import jext.util.SetUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

public class ProjectDump {

    public static final int NO_LIBRARIES = 0x0001;
    public static final int NO_TYPES = 0x0002;
    public static final int NO_DEPENDENCIES = 0x0004;

    public static void dump(Project project, long noFlags) {
        new ProjectDump().yamlProject(project, System.out, noFlags);
    }

    public static void yaml(Project project, File yaml, long noFlags) {
        try(OutputStream w = new BufferedOutputStream(new FileOutputStream(yaml))) {
            yaml(project, w, noFlags);
        }
        catch (IOException e) { }
    }

    public static void yaml(Project project, OutputStream yaml, long noFlags) {
        new ProjectDump().yamlProject(project, new PrintStream(yaml), noFlags);
    }

    private static PrintStream spaces(PrintStream stream, int n) {
        for (int i=0; i<n; ++i)
            stream.print("    ");
        return stream;
    }

    private void yamlProject(Project project, PrintStream stream, long noFlags) {
        stream.printf("name: %s\n", project.getName().getName());
        stream.printf("fullname: %s\n", project.getName().getFullName());
        stream.printf("id: %s\n", project.getId());
        stream.printf("projectType: %s\n", project.getProjectType());
        stream.printf("home: '%s'\n", project.getProjectHome());
        stream.print("properties:\n");
        project.getProperties().forEach((n, v) -> {
            spaces(stream, 1).printf("%s: %s\n", n, v);
        });
        stream.print("modules:\n");
        project.getModules().forEach(m -> {
            if (m.getName().getFullName().isEmpty()) {
                spaces(stream, 1).printf("'%s':\n", m.getName().getFullName());
                spaces(stream, 2).printf("name: '%s'\n", m.getName().getName());
                spaces(stream, 2).printf("fullname: '%s'\n", m.getName().getFullName());
            }
            else {
                spaces(stream, 1).printf("%s:\n", m.getName().getFullName());
                spaces(stream, 2).printf("name: %s\n", m.getName().getName());
                spaces(stream, 2).printf("fullname: %s\n", m.getName().getFullName());
            }
            spaces(stream, 2).printf("id: %s\n", m.getId());
            spaces(stream, 2).printf("home: '%s'\n", m.getModuleHome());
            spaces(stream, 2).printf("path: '%s'\n", m.getPath());
            spaces(stream, 2).print("properties:\n");
            m.getProperties().forEach((n, v) -> {
                spaces(stream, 3).printf("%s: %s\n", n, v);
            });
            spaces(stream, 2).print("sourceRoots:\n");
            m.getSourceRoots().forEach(sr -> {
                spaces(stream, 3).printf("- %s\n", sr);
            });

            if ((noFlags & NO_DEPENDENCIES) == 0) {
                spaces(stream, 2).print("dependencies:\n");
                m.getDependencies().forEach(d -> {
                    spaces(stream, 3).printf("- %s\n", d.getName().getFullName());
                });
            }
            if ((noFlags & NO_LIBRARIES) == 0) {
                spaces(stream, 2).printf("runtimeLibrary: '%s'\n", m.getRuntimeLibrary().getName().getFullName());

                spaces(stream, 2).print("libraries:\n");
                m.getLibraries().forEach(l -> {
                    spaces(stream, 3).printf("- %s\n", l.getName().getFullName());
                });

                Set<Library> definedLibraries = SetUtils.differenceOrdered(
                    new HashSet<>(m.getDeclaredLibraries()),
                    new HashSet<>(m.getLibraries())
                );

                spaces(stream, 2).print("definedLibraries:\n");
                definedLibraries.forEach(l -> {
                    spaces(stream, 3).printf("- %s\n", l.getName().getFullName());
                });

            }
            if ((noFlags & NO_TYPES) == 0) {
                spaces(stream, 2).print("definedTypes:\n");
                m.getTypes().forEach(t -> {
                    spaces(stream, 3).printf("- %s\n", t.getName().getFullName());
                });
                spaces(stream, 2).print("usedTypes:\n");
                m.getUsedTypes().forEach(t -> {
                    spaces(stream, 3).printf("- %s\n", t.getName().getFullName());
                });
            }
        });

        if ((noFlags & NO_LIBRARIES) == 0) {
            stream.print("libraries:\n");
            project.getLibraries().forEach(l -> {
                spaces(stream, 1).printf("%s:\n", l.getName().getName());
                spaces(stream, 2).printf("name: %s\n", l.getName().getName());
                spaces(stream, 2).printf("fullname: %s\n", l.getName().getFullName());
                spaces(stream, 2).printf("id: %s\n", l.getId());
                spaces(stream, 2).printf("libraryType: %s\n", l.getLibraryType());
                spaces(stream, 2).print("files:\n");
                l.getFiles().forEach(lf -> {
                    spaces(stream, 3).printf("- %s\n", lf.getAbsolutePath());
                });
            });
            stream.print("runtimeLibraries:\n");
            ProjectUtils.getRuntimeLibraries(project).forEach(l -> {
                spaces(stream, 1).printf("%s:\n", l.getName().getName());
                spaces(stream, 2).printf("name: %s\n", l.getName().getName());
                spaces(stream, 2).printf("fullname: %s\n", l.getName().getFullName());
                spaces(stream, 2).printf("id: %s\n", l.getId());
                spaces(stream, 2).printf("libraryType: %s\n", l.getLibraryType());
                spaces(stream, 2).print("files:\n");
                l.getFiles().forEach(lf -> {
                    spaces(stream, 3).printf("- %s\n", lf.getAbsolutePath());
                });
            });
        }
    }
}
