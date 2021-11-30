package jext.sourcecode.project.util;

import jext.io.file.FilePatterns;
import jext.java.FastJavaParser;
import jext.logging.Logger;
import jext.maven.MavenDownloader;
import jext.name.Name;
import jext.name.NamedObject;
import jext.name.PathName;
import jext.nio.file.FilteredFileVisitor;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.LibraryRepository;
import jext.sourcecode.project.LibrarySet;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Resource;
import jext.sourcecode.project.Source;
import jext.sourcecode.project.maven.MavenRepository;
import jext.sourcecode.resources.ResourceFile;
import jext.sourcecode.resources.SourceCode;
import jext.util.Bag;
import jext.util.FileUtils;
import jext.util.HashBag;
import jext.util.PropertiesUtils;
import jext.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
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
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class BaseProject extends NamedObject implements Project {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    public static final String MODULE_FILE = "build.xml";

    protected static final String GLOBAL_MODULE_SOURCES = "module.sources.$";
    protected static final String GLOBAL_MODULE_RESOURCES = "module.resources.$";
    protected static final String GLOBAL_MODULE_EXCLUDE = "module.exclude.$";

    private static final String DEFAULT_SOURCES = ".java";
    private static final String DEFAULT_RESOURCES = ".xml,.properties,.json,.gradle,.project,.classpath";
    private static final String DEFAULT_EXCLUDES = ".*";
    private static final String JAVA_EXT = ".java";

    // ----------------------------------------------------------------------
    // Protected fields
    // ----------------------------------------------------------------------

    protected Logger logger;
    protected File projectHome;
    protected String projectType;
    protected Properties properties;
    protected Predicate<String> selector;

    protected List<Module> modules;
    protected List<Source> sources;
    protected jext.sourcecode.project.util.LibrarySet libraries;

    protected LibraryFinder lfinder;
    protected MavenDownloader md;

    protected FilePatterns fpSources;
    protected FilePatterns fpResources;
    protected FilePatterns fpExcludes;

    protected transient boolean aborted;

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
        this.fpSources = new FilePatterns().addAll(sources);
        this.fpResources = new FilePatterns().addAll(resources);
        this.fpExcludes = new FilePatterns().addAll(excludes);

        this.logger = Logger.getLogger("jext.project.%s.%s",
            getClass().getSimpleName(),
            getName().getName());

        // this.setId(StringUtils.digest(getName().getFullName()));
        setIdFromName();
        this.selector = (p) -> true;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public void setResourceFilter(Predicate<String> selector) {
        this.selector = selector;
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
    public File getProjectHome() {
        return projectHome;
    }

    // ----------------------------------------------------------------------
    // Sources
    // ----------------------------------------------------------------------

    @Override
    public List<Source> getSources() {
        if (sources != null)
            return sources;

        sources = new ArrayList<>();
        for (Module module : getModules())
            sources.addAll(module.getSources());
        return sources;
    }

    @Override
    public Source getSource(String sourceId) {
        for (Source source : getSources()) {
            if (source.getId().equals(sourceId))
                return source;
            if (source.getName().getFullName().equals(sourceId))
                return source;
            if (source.getName().getName().equals(sourceId))
                return source;
        }
        return null;
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
        addParentModules();
        sortModules();

        return modules;
    }

    // ----------------------------------------------------------------------

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
     *
     * @return
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

                    if (!JAVA_EXT.equals(FileUtils.getExtension(file)))
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

    // ----------------------------------------------------------------------

    /**
     * Add the root module (with name "") if not present
     */
    protected void addRootModule() {
        logger.debug("addRootModule");

        if (hasRootModule()) return;

        // if the ROOT module is not present it is ADDED
        Module rootModule = newModule(projectHome);
        rootModule.getProperties().setProperty(MODULE_DEFINITION, MODULE_DEFINITION_BY_HEURISTIC);
        modules.add(rootModule);

        logger.warnf("Added Root module\n  %s", FileUtils.getAbsolutePath(projectHome ));
    }

    private boolean hasRootModule() {
        for(Module module : modules)
            if (module.getName().getFullName().equals(ROOT_MODULE_NAME))
                return true;
        return false;
    }

    // ----------------------------------------------------------------------

    protected void addParentModules() {
        logger.debug("addParentModules");

        Set<Name> mnames = new HashSet<>();

        // populate 'mnames'
        modules.forEach(module -> mnames.add(module.getName()));

        // clone modules -> d)efined modules
        List<Module> dmodules = new ArrayList<>(modules);

        boolean[] updated = new boolean[]{true};

        while (updated[0]) {
            updated[0] = false;

            // check for all parents
            dmodules.forEach(module -> {
                // skip the root module
                Name mname = module.getName();
                if (mname.isRoot()) return;

                // check if the parent module is present
                Name pname = mname.getParent();
                if (mnames.contains(pname)) return;

                // the parent module is missing
                // the moduleHome is the parent directory
                File pmoduleHome = module.getModuleHome().getParentFile();
                Module pmodule = newModule(pmoduleHome);
                pmodule.getProperties().setProperty(MODULE_DEFINITION, MODULE_DEFINITION_BY_HEURISTIC);
                // register the name
                mnames.add(pmodule.getName());
                modules.add(pmodule);

                updated[0] = true;
                logger.warnf("Added missing parent module %s", pmodule.getName().getFullName());
            });
        }
    }

    protected void sortModules() {
        // order the modules in lexicographic/depth order
        modules.sort(Comparator.comparing(m -> m.getName().getFullName()));
    }

    // ----------------------------------------------------------------------

    @Override
    public Module getModule(String nameOrId) {
        if (nameOrId.isEmpty() || nameOrId.equals("0"))
            nameOrId = ROOT_MODULE_NAME;

        for (Module module : getModules()) {
            if (module.getId().equals(nameOrId))
                return module;
            if (module.getRefId().equals(nameOrId))
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
        if (md != null)
            return md;

        // create a local copy of the downloader
        md = lfinder.getLibraryDownloader();

        // add the project defined maven repositories
        // md.addRepositories(getMavenRepositories());
        // return md;

        getLibraryRepositories().forEach(librepo -> {
            md.addRepository(librepo.getUrl());
        });

        return md;
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
    public LibrarySet getLibraries() {
        if (libraries != null)
            return libraries;

        // compose 3 type of libraries:
        // 1) local libraries
        // 2) maven libraries buf for them it keep ONLY the latest version
        // 3) runtime libraries

        jext.sourcecode.project.util.LibrarySet libraries = new jext.sourcecode.project.util.LibrarySet();

        // Note: the runtime libraries are added because otherwise javaassist
        // is not able to resolve the symbols

        getModules().forEach(module -> {
            libraries.addAll(module.getDeclaredLibraries());
            // libraries.add(module.getRuntimeLibrary());
        });

        MavenDownloader md = getLibraryDownloader();

        logger.debugf("check %d libraries", libraries.size());
        libraries.checkArtifacts(md, true);

        this.libraries = libraries;

        return libraries;
    }

    @Override
    public Set<Library> getLibraries(Module module) {
        jext.sourcecode.project.util.LibrarySet projectLibraries = (jext.sourcecode.project.util.LibrarySet) getLibraries();
        return projectLibraries.resolveAll(module.getDeclaredLibraries());
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

    @Override
    public Library getLibrary(Library library) {
        jext.sourcecode.project.util.LibrarySet projectLibraries = (jext.sourcecode.project.util.LibrarySet) getLibraries();
        return projectLibraries.resolve(library);
    }

    @Override
    public Set<LibraryRepository> getLibraryRepositories() {
        return getMavenRepositories()
            .stream()
            .map(MavenRepository::new)
            .collect(Collectors.toSet());
    }

    @Override
    public LibraryRepository getLibraryRepository(String librepoId) {
        for (LibraryRepository librepo : getLibraryRepositories()) {
            if (librepo.getId().equals(librepoId))
                return librepo;
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

                    if (fpExcludes.accept(baseDirectory, dir))
                        return FileVisitResult.SKIP_SUBTREE;

                    if (isModuleDir(dir))
                        return FileVisitResult.SKIP_SUBTREE;

                    moduleDirs.add(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (InvalidPathException e) {
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
            fpSources.accept(moduleHome, resource) && !fpExcludes.accept(moduleHome, resource))
        ).stream()
            .map(file -> SourceCode.newSource(file, module))
            .filter(source -> selector.test(source.getPath()))
            .collect(Collectors.toList());

        return list;
    }

    public List<Resource> getResources(File dir, Module module) {
        File moduleHome = module.getModuleHome();
        return FileUtils.asList(dir.listFiles(resource ->
                fpResources.accept(moduleHome, resource))
        ).stream()
            .map(file -> new ResourceFile(file, module))
            .filter(resource -> selector.test(resource.getPath()))
            .collect(Collectors.toList());
    }

    // ----------------------------------------------------------------------
    // Library repositories
    // ----------------------------------------------------------------------

    private Set<String> getMavenRepositories() {
        Set<String> mavenRepos = new HashSet<>();

        for(Module module : getModules()) {
            BaseModule bmodule = (BaseModule) module;
            Set<String> urlrepos = bmodule.getMavenRepositories();
            mavenRepos.addAll(urlrepos);
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
    // Abort
    // ----------------------------------------------------------------------

    @Override
    public void abort() {
        this.aborted = true;
    }

    @Override
    public boolean isAborted() {
        return this.aborted;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
