package jext.buildtools.project.simple;

import jext.buildtools.Module;
import jext.buildtools.util.BaseProject;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class SimpleProject extends BaseProject {

    public static final String TYPE = "simple";
    public static final String PROJECT_RESOURCES = "project.resources";

    public SimpleProject(File projectDir, Properties properties) {
        super(projectDir, properties);
    }


    @Override
    public List<Module> getModules() {
        if (modules != null)
            return modules;

        String moduleFile = getProperties().getProperty(PROJECT_MODULE, MODULE_FILE);
        List<File> moduleDirs = new ArrayList<>();
        try {
            Files.walkFileTree(projectDir.toPath(), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes attrs) {
                    File dir = path.toFile();
                    File isModule = new File(dir, moduleFile);
                    if (isModule.exists())
                        moduleDirs.add(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) { }

        modules = moduleDirs.stream()
                .map(moduleDir -> new SimpleModule(moduleDir, this))
                .collect(Collectors.toList());

        return modules;
    }
}
