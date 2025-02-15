package jext.sourcecode.project.util;

import jext.io.file.FilePatterns;
import jext.util.logging.Logger;
import jext.name.Name;
import jext.name.NamedObject;
import jext.name.PathName;
import jext.nio.file.FilteredFileVisitor;
import jext.sourcecode.project.LibraryDownloader;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.LibraryRepository;
import jext.sourcecode.project.LibrarySet;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Modules;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Resource;
import jext.sourcecode.project.Sources;
import jext.sourcecode.project.java.maven.MavenRepository;
import jext.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class BaseProject extends NamedObject implements Project {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    protected static final String GLOBAL_MODULE_SOURCES = "module.sources.$";
    protected static final String GLOBAL_MODULE_RESOURCES = "module.resources.$";
    protected static final String GLOBAL_MODULE_EXCLUDE = "module.exclude.$";

    // ----------------------------------------------------------------------
    // Protected fields
    // ----------------------------------------------------------------------

    protected Logger logger;
    protected File projectHome;
    protected Properties properties;
    protected Predicate<String> selector;

    protected ModulesImpl modules;
    protected SourcesImpl sources;
    protected LibrarySetImpl libraries;

    protected LibraryFinder lfinder;
    protected LibraryDownloader md;

    protected FilePatterns fpSources;
    protected FilePatterns fpResources;
    protected FilePatterns fpExcludes;

    protected transient boolean aborted;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    protected BaseProject(String projectName, File projectHome, Properties properties, String projectType){
        super(PathName.of(projectName));
        this.projectHome = projectHome;
        // this.projectType = projectType;

        this.properties = new Properties();
        this.properties.putAll(properties);
        this.properties.setProperty(Project.PROJECT_TYPE, projectType);
        this.selector = (p) -> true;

        setIdFromName();
        cleanupProperties();

        this.logger = Logger.getLogger("jext.project.%s.%s",
            getClass().getSimpleName(),
            getName().getName());
    }

    protected void cleanupProperties() {
        String rtLibrary = properties.getProperty(RUNTIME_LIBRARY, null);
        if ("auto".equals(rtLibrary) || "".equals(rtLibrary))
            properties.remove(RUNTIME_LIBRARY);
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
        return properties.getProperty(Project.PROJECT_TYPE);
    }

    @Override
    public String getLanguage() {
        return properties.getProperty(Project.PROJECT_LANGUAGE);
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    @Override
    public File getProjectHome() {
        return projectHome;
    }

    @Override
    public abstract String getRuntimeLibrary();

    // ----------------------------------------------------------------------
    // Sources
    // ----------------------------------------------------------------------

    @Override
    public abstract Sources getSources();

    // ----------------------------------------------------------------------
    // Modules
    // ----------------------------------------------------------------------

    @Override
    public abstract Modules getModules();

    // ----------------------------------------------------------------------

    // ----------------------------------------------------------------------
    // Analyze a "Maven layout"
    // ----------------------------------------------------------------------

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
        return this.modules.hasModule(Project.ROOT_MODULE_NAME);
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
    public LibraryDownloader getLibraryDownloader() {
        if (md != null)
            return md;

        // create a local copy of the downloader
        md = lfinder.getDownloader();

        // add local defined library repositories
        getLibraryRepositories().forEach(librepo -> {
            md.addRepository(librepo.getName(), librepo.getUrl());
        });

        return md;
    }

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

        LibraryDownloader downloader = getLibraryDownloader();

        libraries = new LibrarySetImpl(getLanguage());

        // collect remote libraries
        LibrarySet remoteLibraries = new LibrarySetImpl(getLanguage());

        // Note: the runtime libraries are added because otherwise javaassist
        // is not able to resolve the symbols
        getModules().forEach(module -> {
            libraries.addAll(module.getDeclaredLibraries());
            remoteLibraries.addAll(module.getDeclaredLibraries());

            libraries.addAll(module.getLocalLibraries());
            libraries.add(module.getRuntimeLibrary());
        });

        // download the missing libraries, if necessary
        libraries.checkArtifacts(downloader, true);

        // add the missing libraries used
        // resolveRecursiveDependencies(libraries, remoteLibraries, 1);

        logger.debugf("check %d libraries", libraries.size());

        return libraries;
    }

    // private void resolveRecursiveDependencies(Set<Library> allLibraries, Set<Library> remoteLibraries, int depth) {
    //     // for now it retrieve ONLY the first level of recursive dependencies
    //     remoteLibraries.forEach(remoteLibrary -> {
    //         allLibraries.addAll(remoteLibrary.getDependencies());
    //     });
    // }

    @Override
    public Set<Library> getLibraries(Module module) {
        LibrarySetImpl projectLibraries = (LibrarySetImpl) getLibraries();
        return projectLibraries.resolveAll(module.getDeclaredLibraries());
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

                    if (!isValidDir(baseDirectory, dir))
                        return FileVisitResult.SKIP_SUBTREE;

                    moduleDirs.add(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (InvalidPathException e) {
        } catch (IOException e) { }

        return moduleDirs;
    }

    public boolean isValidDir(File baseDirectory, File dir) {

        // the directory is the root (base directory)
        if (dir.equals(baseDirectory))
            return true;

        // the directory is excluded based on the base directory
        if (fpExcludes.accept(baseDirectory, dir))
            return false;

        // the directory is excluded respect 1 or 2 levels up parent directories
        // this try to resolve exclusions as '/test' and '/src/test' respect
        // the module home directory.
        File parent1 = dir.getParentFile();
        File parent2 = parent1.getParentFile();
        if (fpExcludes.accept(parent1, dir) || fpExcludes.accept(parent2, dir))
            return false;

        // it is the project home directory
        if (dir.equals(projectHome))
            return false;

        // the directory is the home directory of some other module
        for (Module module : this.getModules())
            if (dir.equals(module.getModuleHome()))
                return false;

        return true;
    }

    // ----------------------------------------------------------------------
    // Sources
    // Resources
    // ----------------------------------------------------------------------

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
