package jext.buildtools.project;

import jext.buildtools.Module;
import jext.buildtools.Name;
import jext.buildtools.Project;
import jext.maven.MavenDownloader;

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

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public static final String MODULE_FILE = "build.xml";

    protected File projectDir;
    protected Properties properties;
    protected List<Module> modules;
    protected MavenDownloader downloader;
    protected String projectType;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    protected BaseProject(File projectDir, Properties properties, String projectType){
        this.projectDir = projectDir;
        this.properties = new Properties();
        this.properties.putAll(properties);
        this.projectType = projectType;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    public String getName() {
        return projectDir.getName();
    }

    @Override
    public String getType() {
        return projectType;
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    @Override
    public File getDirectory() {
        return projectDir;
    }

    // -- Modules

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
            .map(this::newModule)
            .collect(Collectors.toList());

        return modules;
    }

    @Override
    public Module getModule(Name name) {
        for (Module module : getModules())
            if (module.getName().equals(name))
                return module;
        return null;
    }

    @Override
    public Module findModule(String name) {
        for (Module module : getModules()) {
            if (module.getId().equals(name)
                    || module.getName().getFullName().equals(name)
                    || module.getName().getName().equals(name))
                return module;
        }
        return null;
    }

    protected abstract Module newModule(File moduleDir);

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    public void setDownloader(MavenDownloader downloader) {
        this.downloader = downloader;
    }

    @Override
    public MavenDownloader getDownloader() {
        return downloader;
    }

}
