package jext.buildtools.project;

import jext.buildtools.Module;
import jext.buildtools.Name;
import jext.buildtools.Project;
import jext.buildtools.Resource;
import jext.buildtools.Source;
import jext.buildtools.resource.ResourceFile;
import jext.buildtools.source.SourceFactory;
import jext.io.file.FilePatterns;
import jext.maven.MavenDownloader;
import jext.nio.file.FilteredFileVisitor;
import jext.util.FileUtils;
import jext.util.PropertiesUtils;

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

    protected static final String GLOBAL_MODULE_SOURCES = "module.sources.$";
    protected static final String GLOBAL_MODULE_RESOURCES = "module.resources.$";
    protected static final String PROJECT_MODULE_RESOURCES = "module.resources.config";
    protected static final String GLOBAL_MODULE_EXCLUDE = "module.exclude.$";

    private static final String DEFAULT_SOURCES = ".java";
    private static final String DEFAULT_RESOURCES = ".xml,.properties,.json,.gradle,.project,.classpath";
    private static final String DEFAULT_EXCLUDES = "target,build,out,.*";

    protected File projectDir;
    protected Properties properties;
    protected List<Module> modules;
    protected String projectType;

    protected FilePatterns sources;
    protected FilePatterns resources;
    protected FilePatterns excludes;

    protected MavenDownloader downloader;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    protected BaseProject(File projectDir, Properties properties, String projectType){
        this.projectDir = projectDir;
        this.properties = new Properties();
        this.properties.putAll(properties);
        this.projectType = projectType;

        if (!this.properties.containsKey(PROJECT_TYPE))
            this.properties.put(PROJECT_TYPE, getProjectType());
        if (!this.properties.containsKey(GLOBAL_MODULE_SOURCES))
            this.properties.put(GLOBAL_MODULE_SOURCES, DEFAULT_SOURCES);
        if (!this.properties.containsKey(GLOBAL_MODULE_RESOURCES))
            this.properties.put(GLOBAL_MODULE_RESOURCES, DEFAULT_RESOURCES);
        if (!this.properties.containsKey(GLOBAL_MODULE_EXCLUDE))
            this.properties.put(GLOBAL_MODULE_EXCLUDE, DEFAULT_EXCLUDES);

        List<String> sources = PropertiesUtils.getValues(this.getProperties(), MODULE_SOURCES);
        List<String> resources = PropertiesUtils.getValues(this.getProperties(), MODULE_RESOURCES);
        List<String> excludes = PropertiesUtils.getValues(this.getProperties(), MODULE_EXCLUDE);

        this.sources = new FilePatterns().addAll(sources);
        this.excludes = new FilePatterns().addAll(excludes);
        this.resources = new FilePatterns().addAll(resources);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    public String getName() {
        return projectDir.getName();
    }

    @Override
    public String getProjectType() {
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

                    if (excludes.accept(dir.getName(), FileUtils.getAbsolutePath(dir)))
                        return FileVisitResult.SKIP_SUBTREE;

                    File isModule = new File(dir, moduleFile);
                    if (isModule.exists())
                        moduleDirs.add(dir);

                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) { }

        modules = moduleDirs.stream()
            .map(this::newModule)
            .sorted()
            .collect(Collectors.toList());

        return modules;
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

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public List<File> getDirectories(File baseDirectory) {

        List<File> moduleDirs = new ArrayList<>();

        try {
            Files.walkFileTree(baseDirectory.toPath(), new FilteredFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes attrs) {
                    File dir = path.toFile();

                    if (dir.equals(baseDirectory))
                        return FileVisitResult.CONTINUE;

                    if (excludes.accept(baseDirectory, dir))
                        return FileVisitResult.SKIP_SUBTREE;

                    if (isModuleDir(dir))
                        return FileVisitResult.SKIP_SUBTREE;

                    moduleDirs.add(dir);

                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) { }

        return moduleDirs;
    }

    private boolean isModuleDir(File directory) {
        if (directory.equals(projectDir))
            return false;
        for (Module module : this.getModules())
            if (directory.equals(module.getDirectory()))
                return true;
        return false;
    }

    // ----------------------------------------------------------------------
    // Sources
    // Resources
    // ----------------------------------------------------------------------

    public List<Source> getSources(File dir, Module module) {
        File moduleDir = module.getDirectory();
        return FileUtils.asList(dir.listFiles(source ->
            sources.accept(moduleDir, source))
        ).stream()
            .map(file -> SourceFactory.newSource(file, module))
            .collect(Collectors.toList());
    }

    // ----------------------------------------------------------------------
    // Resources
    // ----------------------------------------------------------------------

    public List<Resource> getResources(File dir, Module module) {
        File moduleDir = module.getDirectory();
        return FileUtils.asList(dir.listFiles(resource ->
            resources.accept(moduleDir, resource))
        ).stream()
            .map(file -> new ResourceFile(file, module))
            .collect(Collectors.toList());
    }

}
