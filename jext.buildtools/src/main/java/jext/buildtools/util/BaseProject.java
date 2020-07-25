package jext.buildtools.util;

import jext.buildtools.Module;
import jext.buildtools.Name;
import jext.buildtools.Project;

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

public abstract class BaseProject implements Project {

    public final String PROJECT_MODULE = "project.module";
    public static final String MODULE_FILE = "build.xml";

    protected File projectDir;
    protected Properties properties;
    protected List<Module> modules;

    protected BaseProject(File projectDir, Properties properties){
        this.projectDir = projectDir;
        this.properties = new Properties();
        this.properties.putAll(properties);
    }

    @Override
    public String getName() {
        return projectDir.getName();
    }

    @Override
    public String getType() {
        String type = getClass().getSimpleName();
        type = type.substring(0, type.length()-7).toLowerCase();
        return type;
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    @Override
    public File getDirectory() {
        return projectDir;
    }

    @Override
    public Module getModule(Name moduleName) {
        for (Module module : getModules())
            if (module.getName().equals(moduleName))
                return module;
        return null;
    }

    @Override
    public Module findModule(String name) {
        for (Module module : getModules()) {
            Name moduleName = module.getName();
            if (moduleName.getFullname().equals(name) || moduleName.getName().equals(name))
                return module;
        }
        return null;
    }

    // @Override
    // public List<Module> getModules() {
    //     throw new UnsupportedOperationException();
    // }

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
                .map(moduleDir -> newModule(moduleDir))
                .collect(Collectors.toList());

        return modules;
    }

    protected Module newModule(File moduleDir) {
        throw new UnsupportedOperationException();
    }
}
