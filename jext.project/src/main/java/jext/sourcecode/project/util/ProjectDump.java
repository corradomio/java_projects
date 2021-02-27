package jext.sourcecode.project.util;

import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.Project;
import jext.util.FileUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class ProjectDump {

    public static void dump(Project project) {
        new ProjectDump().yamlProject(project, System.out);
    }

    public static void yaml(Project project, File yaml) {
        try(OutputStream w = new BufferedOutputStream(new FileOutputStream(yaml))) {
            yaml(project, w);
        }
        catch (IOException e) { }
    }

    public static void yaml(Project project, OutputStream yaml) {
        new ProjectDump().yamlProject(project, new PrintStream(yaml));
    }

    private static PrintStream spaces(PrintStream stream, int n) {
        for (int i=0; i<n; ++i)
            stream.print("    ");
        return stream;
    }

    private void yamlProject(Project project, PrintStream stream) {
        stream.printf("name: '%s'\n", project.getName().getName());
        stream.printf("fullname: '%s'\n", project.getName().getFullName());
        stream.printf("id: %s\n", project.getId());
        stream.printf("type: %s\n", project.getProjectType());
        stream.printf("home: '%s'\n", project.getProjectType());
        stream.printf("properties:\n");
        project.getProperties().forEach((n, v) -> {
            spaces(stream, 1).printf("%s: %s\n", n, v);
        });
        stream.printf("modules:\n");
        project.getModules().forEach(m -> {
            spaces(stream, 1).printf("'%s':\n", m.getName().getFullName());
            spaces(stream, 2).printf("name: '%s'\n", m.getName().getName());
            spaces(stream, 2).printf("fullname: '%s'\n", m.getName().getFullName());
            spaces(stream, 2).printf("id: %s\n", m.getId());
            spaces(stream, 2).printf("home: '%s'\n", m.getModuleHome());
            spaces(stream, 2).printf("path: '%s'\n", m.getPath());
            spaces(stream, 2).printf("properties:\n");
            m.getProperties().forEach((n, v) -> {
                spaces(stream, 3).printf("%s: %s\n", n, v);
            });
            spaces(stream, 2).printf("sourceRoots:\n");
            m.getSourceRoots().forEach(sr -> {
                spaces(stream, 3).printf("- %s\n", FileUtils.relativePath(m.getModuleHome(), sr));
            });
            spaces(stream, 2).printf("definedLibraries:\n");
            m.getLibraries().forEach(l -> {
                spaces(stream, 3).printf("- %s\n", l.getName().getFullName());
            });
            spaces(stream, 2).printf("dependencies:\n");
            m.getDependencies().forEach(d -> {
                spaces(stream, 3).printf("- %s\n", d.getName().getFullName());
            });
            spaces(stream, 2).printf("libraries:\n");
            m.getLibraries().forEach(l -> {
                spaces(stream, 3).printf("- %s\n", l.getName().getFullName());
            });
            spaces(stream, 2).printf("definedTypes:\n");
            m.getTypes().forEach(t -> {
                spaces(stream, 3).printf("- %s\n", t.getName().getFullName());
            });
            spaces(stream, 2).printf("usedTypes:\n");
            m.getUsedTypes().forEach(t -> {
                spaces(stream, 3).printf("- %s\n", t.getName().getFullName());
            });
        });
        stream.printf("libraries:\n");
        project.getLibraries().forEach(l -> {
            spaces(stream, 1).printf("'%s':\n", l.getName().getName());
            spaces(stream, 2).printf("name: '%s'\n", l.getName().getName());
            spaces(stream, 2).printf("fullname: '%s'\n", l.getName().getFullName());
            spaces(stream, 2).printf("id: %s\n", l.getId());
            spaces(stream, 2).printf("files:\n");
            l.getFiles().forEach(lf -> {
                spaces(stream, 3).printf("- %s\n", lf.getAbsolutePath());
            });
        });
    }
}
