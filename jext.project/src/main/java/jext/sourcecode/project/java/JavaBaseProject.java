package jext.sourcecode.project.java;

import jext.io.file.FilePatterns;
import jext.sourcecode.project.java.util.FastJavaParser;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Modules;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Source;
import jext.sourcecode.project.Sources;
import jext.sourcecode.project.java.util.JavaSourcesImpl;
import jext.sourcecode.project.util.BaseProject;
import jext.sourcecode.project.util.ModulesImpl;
import jext.util.Bag;
import jext.util.FileUtils;
import jext.util.HashBag;
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
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

public abstract class JavaBaseProject extends BaseProject {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    public static final String MODULE_FILE = "build.xml";

    private static final String DEFAULT_SOURCES = ".java";
    private static final String DEFAULT_RESOURCES = ".xml,.properties,.json,.gradle,.project,.classpath";
    private static final String DEFAULT_EXCLUDES = ".*";

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    protected JavaBaseProject(String projectName, File projectHome, Properties properties, String projectType) {
        super(projectName, projectHome, properties, projectType);

        this.properties.setProperty(Project.PROJECT_LANGUAGE, JavaConstants.JAVA);

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
        this.fpSources = new FilePatterns().addAll(sources);
        this.fpResources = new FilePatterns().addAll(resources);
        this.fpExcludes = new FilePatterns().addAll(excludes);
    }

    // ----------------------------------------------------------------------
    // Sources
    // ----------------------------------------------------------------------

    @Override
    public Sources getSources() {
        if (sources != null)
            return sources;

        sources = new JavaSourcesImpl(getProjectHome());
        for (Module module : getModules())
            sources.addAll(module.getSources());
        return sources;
    }

    // ----------------------------------------------------------------------
    // Runtime library
    // ----------------------------------------------------------------------

    @Override
    public String getRuntimeLibrary() {
        String rtLibrary = properties.getProperty(RUNTIME_LIBRARY, null);

        if (rtLibrary == null)
            rtLibrary = GuessRuntimeLibrary.DEFAULT_JAVA_RUNTIME_LIBRARY;

        return rtLibrary;
    }

    // ----------------------------------------------------------------------
    // Modules
    // ----------------------------------------------------------------------

    @Override
    public Modules getModules() {
        if (modules != null)
            return modules;

        modules = new ModulesImpl();

        findModulesByScan();
        findModulesByJavaSourceRoots();
        addRootModule();
        addParentModules();
        sortModules();

        return modules;
    }

    protected void findModulesByScan() {
        logger.debug("findModulesByScan");

        String moduleFile = getProperties().getProperty(PROJECT_MODULE, MODULE_FILE);
        List<File> moduleDirs = new ArrayList<>();
        try {
            Files.walkFileTree(projectHome.toPath(), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes attrs) {
                    File dir = path.toFile();

                    if (fpExcludes.accept(dir.getName(), FileUtils.getAbsolutePath(dir)))
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
            module.getProperties().setProperty(MODULE_DEFINITION, MODULE_DEFINITION_BY_CONFIGURATION_FILE);
            modules.add(module);
        });

    }

    // ----------------------------------------------------------------------
    // Analyze a "Maven layout"
    // ----------------------------------------------------------------------

    private static final String MAVEN_SRC_MAIN_JAVA = "java";
    private static final String MAVEN_SRC_MAIN = "main";
    private static final String MAVEN_SRC_TEST = "test";
    private static final String MAVEN_SRC = "src";
    private static final String ANT_SOURCE = "source";

    private void addModuleHome(Bag<File> modulesHome, File sourceRoot) {
        File moduleHome = sourceRoot;

        // check src/main/java
        if (MAVEN_SRC_MAIN_JAVA.equals(moduleHome.getName()))
            moduleHome = getParentFile(moduleHome);
        // check src/main/[anything]
        if (MAVEN_SRC_MAIN.equals(moduleHome.getParentFile().getName()))
            moduleHome = getParentFile(moduleHome);
        // check src/test/[anything]
        if (MAVEN_SRC_TEST.equals(moduleHome.getParentFile().getName()))
            moduleHome = getParentFile(moduleHome);
        // check src/main
        if (MAVEN_SRC_MAIN.equals(moduleHome.getName()))
            moduleHome = getParentFile(moduleHome);
        // check src/test
        if (MAVEN_SRC_TEST.equals(moduleHome.getName()))
            moduleHome = getParentFile(moduleHome);

        // check parent/src | parent/source
        String name = moduleHome.getName();
        if (MAVEN_SRC.equals(name) || ANT_SOURCE.equals(name))
            moduleHome = getParentFile(moduleHome);

        // moduleHome == sourceRoot
        if (moduleHome.equals(sourceRoot))
            moduleHome = getParentFile(moduleHome);

        modulesHome.add(moduleHome);
    }

    private File getParentFile(File directory) {
        if (directory == null)
            return projectHome;
        String dpath = FileUtils.getAbsolutePath(directory);
        String hpath = FileUtils.getAbsolutePath(projectHome);
        if (!dpath.startsWith(hpath))
            return projectHome;
        if (dpath.length() > hpath.length())
            return directory.getParentFile();
        else
            return projectHome;
    }

    protected void findModulesByJavaSourceRoots() {
        logger.debug("findModulesByJavaSourceRoots");

        List<File> sourceRoots = findModulesHome();
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
     */
    private List<File> findModulesHome() {
        HashBag<File> modulesHome = new HashBag<>();

        try {
            Files.walkFileTree(projectHome.toPath(), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes attrs) {
                    File dir = path.toFile();

                    if (fpExcludes.accept(dir.getName(), FileUtils.getAbsolutePath(dir)))
                        return FileVisitResult.SKIP_SUBTREE;

                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    File file = path.toFile();

                    if (!JavaConstants.JAVA_EXT.equals(FileUtils.getExtension(file)))
                        return FileVisitResult.CONTINUE;

                    FastJavaParser parser = new FastJavaParser(file);
                    Optional<File> sourceRoot = parser.getSourceRoot();

                    // if the source root is not present, try with another file
                    if (!sourceRoot.isPresent())
                        return FileVisitResult.CONTINUE;

                    addModuleHome(modulesHome, sourceRoot.get());

                    return FileVisitResult.SKIP_SIBLINGS;
                }
            });
        } catch (IOException e) {
            logger.errorf("findModulesByJavaSourceRoots[%s]: %s", projectHome, e);
        }

        List<File> selectedHomes = new ArrayList<>();
        for(File moduleHome : modulesHome) {
            int count = modulesHome.get(moduleHome);
            selectedHomes.add(moduleHome);
        }

        return selectedHomes;
    }

    /**
     * Check if there is a already registered module for the same directory as home directory
     */
    private boolean isModuleHome(File sourceRoot) {
        String relativePath = FileUtils.relativePath(getProjectHome(), sourceRoot);
        for(Module module : modules) {
            if (module.getName().getFullName().equals(relativePath))
                return true;
        }
        return false;
    }

    /**
     * Add an extra module for each source root not already registered as module home
     * @param sourceRoots
     */
    private void addSourceRootModules(List<File> sourceRoots) {
        sourceRoots.stream()
            .filter(sourceRoot -> !isModuleHome(sourceRoot))
            .forEach(sourceRoot -> {
                Module module = newModule(sourceRoot);
                module.getProperties().setProperty(MODULE_DEFINITION, MODULE_DEFINITION_BY_SOURCE_ROOTS);
                modules.add(module);

                logger.warnf("Added module %s based on source root\n  %s", module.getName(), module.getModuleHome());
            });
    }

    // ----------------------------------------------------------------------
    // Sources
    // ----------------------------------------------------------------------

    public List<Source> getSources(File dir, Module module) {
        File moduleHome = module.getModuleHome();

        // scan the directory to retrieve all possible 'source' files
        List<File> sourceFiles = FileUtils.asList(dir.listFiles(resource ->
            fpSources.accept(moduleHome, resource) && !fpExcludes.accept(moduleHome, resource))
        );

        List<Source> sources = sourceFiles
            .stream()
            // file -> <Type>Source
            .map(file -> JavaSourceCode.newSource(file, module))
            // check if it is a ""valid"" source file
            .filter(source -> source.getSourceRoot().isPresent())
            // check if the path is valid
            .filter(source -> selector.test(source.getPath()))
            .collect(Collectors.toList());

        return sources;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------
}
