package jext.sourcecode.project.util;

import jext.io.file.FilePatterns;
import jext.logging.Logger;
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
import jext.sourcecode.project.Source;
import jext.sourcecode.project.Sources;
import jext.sourcecode.project.java.maven.MavenRepository;
import jext.sourcecode.resources.ResourceFile;
import jext.sourcecode.resources.SourceCode;
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
    protected jext.sourcecode.project.util.LibrarySet libraries;

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
    public String getProjectLanguage() {
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

    // @Override
    // public String getRuntimeLibrary() {
    //     String rtLibrary = properties.getProperty(RUNTIME_LIBRARY, null);
    //
    //     if (rtLibrary == null)
    //         rtLibrary = JavaGuessRuntimeLibrary.DEFAULT_JAVA_RUNTIME_LIBRARY;
    //
    //     return rtLibrary;
    // }
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

    // @Override
    // public Module getModule(String nameOrId) {
    //     if (nameOrId.isEmpty() || nameOrId.equals("0"))
    //         nameOrId = ROOT_MODULE_NAME;
    //
    //     for (Module module : getModules()) {
    //         if (module.getId().equals(nameOrId))
    //             return module;
    //         if (module.getRefId().equals(nameOrId))
    //             return module;
    //         if (module.getName().getFullName().equals(nameOrId))
    //             return module;
    //         if (module.getPath().equals(nameOrId))
    //             return module;
    //
    //         // DANGEROUS
    //         if (module.getName().getName().equals(nameOrId))
    //             return module;
    //     }
    //
    //     return null;
    // }

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

        getLibraryRepositories().forEach(librepo -> {
            md.addRepository(librepo.getUrl());
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

        jext.sourcecode.project.util.LibrarySet libraries = new jext.sourcecode.project.util.LibrarySet();

        // Note: the runtime libraries are added because otherwise javaassist
        // is not able to resolve the symbols

        getModules().forEach(module -> {
            libraries.addAll(module.getDeclaredLibraries());
            libraries.add(module.getRuntimeLibrary());
        });

        LibraryDownloader md = getLibraryDownloader();

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
