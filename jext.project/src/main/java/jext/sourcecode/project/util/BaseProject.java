package jext.sourcecode.project.util;

import jext.name.PathName;
import jext.sourcecode.project.maven.LibrarySet;
import jext.sourcecode.resources.ResourceFile;
import jext.sourcecode.resources.SourceCode;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Resource;
import jext.sourcecode.project.Source;
import jext.io.file.FilePatterns;
import jext.logging.Logger;
import jext.maven.MavenDownloader;
import jext.nio.file.FilteredFileVisitor;
import jext.util.Bag;
import jext.util.FileUtils;
import jext.util.HashBag;
import jext.util.PropertiesUtils;
import jext.java.FastJavaParser;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class BaseProject extends NamedObject implements Project {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    private static final String ROOT_MODULE_NAME = "";

    public static final String MODULE_FILE = "build.xml";

    protected static final String GLOBAL_MODULE_SOURCES = "module.sources.$";
    protected static final String GLOBAL_MODULE_RESOURCES = "module.resources.$";
    protected static final String GLOBAL_MODULE_EXCLUDE = "module.exclude.$";

    private static final String DEFAULT_SOURCES = ".java";
    private static final String DEFAULT_RESOURCES = ".xml,.properties,.json,.gradle,.project,.classpath";
    private static final String DEFAULT_EXCLUDES = "target,out,.*";
    private static final String JAVA_EXT = ".java";

    // ----------------------------------------------------------------------
    // Protected fields
    // ----------------------------------------------------------------------

    protected Logger logger;
    protected File projectHome;
    protected String projectType;
    protected Properties properties;

    protected List<Module> modules;
    protected LibrarySet libraries;

    protected LibraryFinder lfinder;
    protected MavenDownloader downloader;

    protected FilePatterns sources;
    protected FilePatterns resources;
    protected FilePatterns excludes;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    protected BaseProject(String projectName, File projectHome, Properties properties, String projectType){
        super(new PathName(projectName));
        this.projectHome = projectHome;
        this.projectType = projectType;

        this.properties = new Properties();
        this.properties.putAll(properties);

        // Default properties:
        //
        //      project.type
        //      module.exclude.$
        //      module.sources.$
        //      module.resources.$
        //
        if (!this.properties.containsKey(PROJECT_TYPE))
            this.properties.put(PROJECT_TYPE, getProjectType());
        if (!this.properties.containsKey(GLOBAL_MODULE_EXCLUDE))
            this.properties.put(GLOBAL_MODULE_EXCLUDE, DEFAULT_EXCLUDES);
        if (!this.properties.containsKey(GLOBAL_MODULE_SOURCES))
            this.properties.put(GLOBAL_MODULE_SOURCES, DEFAULT_SOURCES);
        if (!this.properties.containsKey(GLOBAL_MODULE_RESOURCES))
            this.properties.put(GLOBAL_MODULE_RESOURCES, DEFAULT_RESOURCES);

        List<String> sources = PropertiesUtils.getValues(this.getProperties(), MODULE_SOURCES);
        List<String> resources = PropertiesUtils.getValues(this.getProperties(), MODULE_RESOURCES);
        List<String> excludes = PropertiesUtils.getValues(this.getProperties(), MODULE_EXCLUDE);

        // file selectors
        this.sources = new FilePatterns().addAll(sources);
        this.resources = new FilePatterns().addAll(resources);
        this.excludes = new FilePatterns().addAll(excludes);

        this.logger = Logger.getLogger("%s.%s",
            getClass().getSimpleName(),
            getName().getName());
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public String getProjectType() {
        return projectType;
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    @Override
    public File getProjectHome() {
        return projectHome;
    }

    // ----------------------------------------------------------------------
    // Modules
    // ----------------------------------------------------------------------

    @Override
    public List<Module> getModules() {
        if (modules != null)
            return modules;

        modules = new ArrayList<>();

        findModulesByScan();
        findModulesByJavaSourceRoots();
        addRootModule();

        return modules;
    }

    // ----------------------------------------------------------------------

    protected void findModulesByScan() {

        String moduleFile = getProperties().getProperty(PROJECT_MODULE, MODULE_FILE);
        List<File> moduleDirs = new ArrayList<>();
        try {
            Files.walkFileTree(projectHome.toPath(), new SimpleFileVisitor<Path>() {
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
        } catch (IOException e) {
            logger.errorf("findModulesByScan[%s]: %s", projectHome, e);
        }

        moduleDirs.forEach(moduleHome -> {
            Module module = newModule(moduleHome);
            modules.add(module);
        });

    }

    // ----------------------------------------------------------------------

    protected void findModulesByJavaSourceRoots() {
        List<File> sourceRoots = findSourceRoots();
        addSourceRootModules(sourceRoots);
    }

    /**
     * Scan the project directory and all subdirectories to identify the Java source roots.
     * A "Java source root" if the root directory containing the Java source file in the correct
     * subdirectory based on the class package.
     *
     * However, the directory is corrected to support standard Maven/Gradle/Ant filesystem structure:
     *
     *      [sourceRoot]/src/main/java/[package]/[class]
     *      [sourceRoot]/src/[package]/[class]
     *      [sourceRoot]/source/[package]/[class]
     *      [sourceRoot]/[parent]/[package]/[class]
     *
     *
     * @return
     */
    private List<File> findSourceRoots() {
        // skip[0] == skip subtree
        // skip[1] == skip files
        boolean[] skip = new boolean[2];
        HashBag<File> sourceRoots = new HashBag<>();
        try {
            Files.walkFileTree(projectHome.toPath(), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes attrs) {
                    File dir = path.toFile();

                    if (excludes.accept(dir.getName(), FileUtils.getAbsolutePath(dir)))
                        return FileVisitResult.SKIP_SUBTREE;

                    if (skip[0]) {
                        skip[0] = false;
                        return FileVisitResult.SKIP_SUBTREE;
                    }

                    skip[1] = FileUtils.listFiles(dir, JAVA_EXT).isEmpty();
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    if (skip[1]) return FileVisitResult.CONTINUE;

                    File file = path.toFile();

                    if (!JAVA_EXT.equals(FileUtils.getExtension(file)))
                        return FileVisitResult.CONTINUE;

                    // logger.debugf("   visit %s", path.toString());

                    FastJavaParser parser = new FastJavaParser(file);
                    Optional<File> sourceRoot = parser.getSourceRoot();

                    // if the source root is not present, try with another file
                    if (!sourceRoot.isPresent())
                        return FileVisitResult.CONTINUE;

                    addJavaSourceRoot(sourceRoots, sourceRoot.get());

                    skip[0] = true;
                    return FileVisitResult.SKIP_SIBLINGS;
                }
            });
        } catch (IOException e) {
            logger.errorf("findModulesByJavaSourceRoots[%s]: %s", projectHome, e);
        }

        List<File> selectedRoots = new ArrayList<>();
        for(File sourceRoot : sourceRoots) {
            int count = sourceRoots.get(sourceRoot);
            selectedRoots.add(sourceRoot);
        }

        return selectedRoots;
    }

    private static final String MAVEN_SRC_MAIN_JAVA = "java";
    private static final String MAVEN_SRC_MAIN = "main";
    private static final String MAVEN_SRC_TEST = "test";
    private static final String MAVEN_SRC = "src";
    private static final String ANT_SOURCE = "source";

    private void addJavaSourceRoot(Bag<File> sourceRoots, File sourceRoot) {

        // check src/main/java
        if (MAVEN_SRC_MAIN_JAVA.equals(sourceRoot.getName()))
            sourceRoot = sourceRoot.getParentFile();
        // check src/main/[anything]
        if (MAVEN_SRC_MAIN.equals(sourceRoot.getParentFile().getName()))
            sourceRoot = sourceRoot.getParentFile();
        // check src/test/[anything]
        if (MAVEN_SRC_TEST.equals(sourceRoot.getParentFile().getName()))
            sourceRoot = sourceRoot.getParentFile();
        // check src/main
        if (MAVEN_SRC_MAIN.equals(sourceRoot.getName()))
            sourceRoot = sourceRoot.getParentFile();
        // check src/test
        if (MAVEN_SRC_TEST.equals(sourceRoot.getName()))
            sourceRoot = sourceRoot.getParentFile();

        String name = sourceRoot.getName();
        if (MAVEN_SRC.equals(name) || ANT_SOURCE.equals(name))
            sourceRoot = sourceRoot.getParentFile();

        sourceRoots.add(sourceRoot);
    }

    /**
     * Add an extra module for each source root not already registerd as module home
     * @param sourceRoots
     */
    private void addSourceRootModules(List<File> sourceRoots) {
        sourceRoots.stream()
            .filter(sourceRoot -> !hasModuleWithHome(sourceRoot))
            .forEach(this::addModule);
    }

    private void addModule(File sourceRoot) {
        Module module = newModule(sourceRoot);
        modules.add(module);

        logger.warnf("Added module %s based on source root '%s'", module.getName(), module.getModuleHome());
    }

    /**
     * Check if there is a already registered module for the same directory
     */
    private boolean hasModuleWithHome(File sourceRoot) {
        for(Module module : modules) {
            if (module.getModuleHome().equals(sourceRoot))
                return true;
        }
        return false;
    }

    // ----------------------------------------------------------------------

    /**
     * Add the root module (with name "") if not present
     */
    protected void addRootModule() {
        // if the ROOT module is not present it is ADDED
        // as FIRST module!!!!
        if (!hasRootModule()) {
            Module rootModule = newModule(projectHome);
            modules.add(rootModule);
        }

        modules.sort(Comparator.comparing(m -> m.getName().getFullName()));
    }

    private boolean hasRootModule() {
        for(Module module : modules)
            if (module.getName().getFullName().equals(ROOT_MODULE_NAME))
                return true;
        return false;
    }

    // ----------------------------------------------------------------------

    @Override
    public Module getModule(String nameOrId) {
        for (Module module : getModules()) {
            if (module.getId().equals(nameOrId))
                return module;
            if (module.getName().getFullName().equals(nameOrId))
                return module;
            if (module.getPath().equals(nameOrId))
                return module;

            // DANGEROUS
            if (module.getName().getName().equals(nameOrId))
                return module;
        }

        return null;
    }

    protected Module newModule(File moduleHome) {
        throw new UnsupportedOperationException();
    }

    // ----------------------------------------------------------------------
    // Runtime library
    // LibraryFinder
    // ----------------------------------------------------------------------

    /** Set the library resolver */
    @Override
    public Project setLibraryFinder(LibraryFinder lfinder) {
        this.lfinder = lfinder;
        this.lfinder.setProject(this);
        return this;
    }

    @Override
    public LibraryFinder getLibraryFinder() {
        return lfinder;
    }

    @Override
    public MavenDownloader getLibraryDownloader() {
        if (downloader != null)
            return downloader;

        // create a local copy of the downloader
        downloader = lfinder.getDownloader().newDownloader();

        // add the project defined maven repositories
        downloader.addRepositories(getMavenRepositories());

        return downloader;
    }

    // public Library getRuntimeLibrary() {
    //     String runtimeName = this.getProperties().getProperty(RUNTIME_LIBRARY, "jdk8");
    //     Library runtimeLibrary = lfinder.getLibrary(runtimeName);
    //     if (runtimeLibrary == null) {
    //         logger.errorf("JDK Library %s not available. Uses the default jdk8", runtimeName);
    //         runtimeLibrary = lfinder.getLibrary("jdk8");
    //     }
    //
    //     return runtimeLibrary;
    // }

    // ----------------------------------------------------------------------
    // Libraries
    // ----------------------------------------------------------------------

    @Override
    public Set<Library> getLibraries() {
        if (libraries != null)
            return libraries;

        // compose two type of libraries:
        // 1) local libraries
        // 2) maven libraries buf for them it keep ONLY the latest version

        libraries = new LibrarySet();

        getModules().forEach(module -> {
            libraries.addAll(module.getLibraries());
        });

        // add the runtime library
        // libraries.add(getRuntimeLibrary());

        return libraries;
    }

    @Override
    public Library getLibrary(String nameOrId) {
        for (Library library : getLibraries()) {
            if (library.getId().equals(nameOrId))
                return library;
            if (library.getName().getFullName().equals(nameOrId))
                return library;
            if (library.getName().getName().equals(nameOrId))
                return library;
            if (library.getPath().equals(nameOrId))
                return library;
        }
        return null;
    }


    // ----------------------------------------------------------------------
    // Valid sub directories in 'directory'
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
        if (directory.equals(projectHome))
            return false;
        for (Module module : this.getModules())
            if (directory.equals(module.getModuleHome()))
                return true;
        return false;
    }

    // ----------------------------------------------------------------------
    // Sources
    // Resources
    // ----------------------------------------------------------------------

    public List<Source> getSources(File dir, Module module) {
        File moduleHome = module.getModuleHome();

        List<Source> list = FileUtils.asList(dir.listFiles(resource ->
            sources.accept(moduleHome, resource))
        ).stream()
            .map(file -> SourceCode.newSource(file, module))
            .collect(Collectors.toList());

        return list;
    }

    public List<Resource> getResources(File dir, Module module) {
        File moduleHome = module.getModuleHome();
        return FileUtils.asList(dir.listFiles(resource ->
                resources.accept(moduleHome, resource))
        ).stream()
            .map(file -> new ResourceFile(file, module))
            .collect(Collectors.toList());
    }

    // ----------------------------------------------------------------------
    // Library repositories
    // ----------------------------------------------------------------------

    public Set<String> getMavenRepositories() {
        Set<String> mavenRepos = new HashSet<>();

        for(Module module : getModules()) {
            BaseModule bmodule = (BaseModule) module;
            mavenRepos.addAll(bmodule.getMavenRepositories());
        }

        return mavenRepos;
    }

    // ----------------------------------------------------------------------
    // Extras
    // ----------------------------------------------------------------------

    @Override
    public double getComplexity(double threshold) {
        return 0.;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
