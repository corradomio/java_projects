package jext.sourcecode.project.util;

import jext.cache.Cache;
import jext.cache.CacheManager;
import jext.io.filters.FileFilters;
import jext.logging.Logger;
import jext.maven.MavenCoords;
import jext.maven.MavenDownloader;
import jext.maven.MavenPom;
import jext.name.PathName;
import jext.sourcecode.project.GuessRuntimeLibrary;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.Resource;
import jext.sourcecode.project.RuntimeLibrary;
import jext.sourcecode.project.Source;
import jext.sourcecode.project.Type;
import jext.sourcecode.project.ant.util.IvyFile;
import jext.sourcecode.project.eclipse.util.ClasspathFile;
import jext.sourcecode.project.maven.MavenLibrary;
import jext.sourcecode.resources.libraries.ArchiveUtils;
import jext.sourcecode.resources.libraries.InvalidLibrary;
import jext.util.FileUtils;
import jext.util.LongHash;
import jext.util.SetUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public abstract class BaseModule extends ReferencedObject implements Module {

    // ----------------------------------------------------------------------
    // Protected fields
    // ----------------------------------------------------------------------

    protected Project project;
    protected String path;
    protected Properties properties;
    protected File moduleHome;

    protected List<File> directories;
    protected Set<Library> libraries;
    protected List<Module> dependencies;
    protected List<Source> sources;
    protected Set<String> sourceRoots;

    protected Logger logger;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    protected BaseModule(File moduleHome, Project project) {
        super(null);
        this.moduleHome = moduleHome;
        this.project = project;
        this.properties = new Properties();

        this.path = FileUtils.relativePath(project.getProjectHome(), moduleHome);
        setNameWithId(new PathName(this.path));
        setRefIdFromName();

        this.logger = Logger.getLogger("%s.%s.%s",
            getClass().getSimpleName(),
            project.getName().getName(),
            getName().getName());
    }

    // @Override
    // public void setName(String name) {
    //     if (name.isEmpty())
    //         name = ROOT_MODULE_NAME;
    //     super.setName(name);
    // }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public Project getProject(){
        return project;
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public File getModuleHome() {
        return this.moduleHome;
    }

    @Override
    public List<File> getSourceRootDirectories() {
        return getSourceRoots().stream()
            .map(sourceRoot -> new File(moduleHome, sourceRoot))
            .collect(Collectors.toList());
    }

    @Override
    public String getDigest() {
        long[] digest = new long[1];
        getSources().forEach(source -> {
            digest[0] = LongHash.concat(digest[0], source.getDigest());
        });
        return LongHash.toString(digest[0]);
    }

    // ----------------------------------------------------------------------
    // Module dependencies
    // ----------------------------------------------------------------------

    @Override
    public List<Module> getDependencies() {
        if (dependencies != null)
            return dependencies;

        Set<Module> orderedDeps = getUsedTypesDependencies();

        this.dependencies = orderedDeps.isEmpty()
            ? Collections.emptyList()
            : new ArrayList<>(orderedDeps);

        return this.dependencies;
    }

    protected Set<Module> getUsedTypesDependencies() {

        //
        // Identify a module dependency based on the following rule:
        //
        // 1) each module defines a set of types ('types')
        // 2) the implementation of these types use other types ('usedTypes')
        //    defined in
        //    - the same module
        //    - in other modules
        //    - in external libraries
        //
        // The FIRST step is to exclude from current 'usedTypes' the 'types' defined
        // inside the current module, to obtain the '(filtered) usedTypes'.
        //
        // There is a 'module dependency' if the intersection between the current
        // module's '(filtered) usedTypes' with another module's 'types' is NOT EMPTY.
        //
        // However it is possible to have 'false positive', that is the intersection
        // is not empty but this is only a case.
        //
        // This can happen when two or more modules define the same type present in the
        // current '(filtered) usedTypes'.
        // In theory, the correct definition is selected based on the 'module order'
        // defined in the 'building system configuration file'. But it is possible that
        // this file is not available or it is not possible to analyze (for example
        // Gradle 'build.gradle') for some reason.
        //
        // To resolve this problem we use the following trick:
        //
        // - we order the module dependencies based on the intersection size, in
        //   decreasing order
        //
        // In this way we hope to test the dependencies with the most probable
        // module.
        //
        // The correct order is implemented inside the derived classes on this class
        // because each derived class is specialized on a specific 'building system'
        //
        // Update: it is NOT necessary to consider the included libraries because
        // we are interested ONLY on the module dependencies
        //

        Set<Module> orderedDeps = new TreeSet<>(COMPARATOR);

        // Union of LOCAL DEFINED types PLUS types DEFINED inside the LOCAL libraries
        // WHY to include the external libraries??
        // It is enough to use ONLY the types defined in the current module and other modules!
        Set<Type> definedTypes = getTypes();

        // EXTERNAL USED types: LOCAL USED types MINUS LOCAL DEFINED types
        Set<RefType> usedTypes = getUsedTypes();

        project.getModules().forEach(dmodule -> {
            if (dmodule.getName().equals(this.getName()))
                return;

            // EXTERNAL DEFINED types: all EXTERNAL DEFINED types MINUS LOCAL DEFINED types
            // to be sure to consider ONLY effective types NOT DEFINED locally
            Set<Type> ddefinedTypes = new HashSet<>(dmodule.getTypes());
            Set<RefType> dtypes = new HashSet<>(SetUtils.differenceOrdered(ddefinedTypes, definedTypes));

            // LOCAL USED types available in the EXTERNAL DEFINED types
            Set<RefType> itypes = SetUtils.intersectionOrdered(usedTypes, dtypes);
            int isize = itypes.size();
            if (isize == 0)
                return;

            // there is a dependency between the current module AND dmodule
            orderedDeps.add(dmodule);
        });

        return orderedDeps;
    }

    // ----------------------------------------------------------------------
    // Module content
    // ----------------------------------------------------------------------

    @Override
    public List<Source> getSources() {
        if (sources != null)
            return sources;

        directories = getBaseProject().getDirectories(moduleHome);
        sources = new ArrayList<>();
        getDirectories().forEach(dir -> {
            List<Source> srclist = getBaseProject().getSources(dir, this);
            sources.addAll(srclist);
        });

        return sources;
    }

    @Override
    public Source getSource(String nameOrId) {
        // for (SourceRoot sourceRoot : getSourceRoots())
        // for (Source source : sourceRoot.getSources()) {
        for (Source source : getSources()) {
            if (source.getId().equals(nameOrId))
                return source;
            if (source.getName().getFullName().equals(nameOrId))
                return source;
            if (source.getName().getName().equals(nameOrId))
                return source;
            if (source.getPath().equals(nameOrId))
                return source;
        }
        return null;
    }

    @Override
    public Set<String> getSourceRoots() {
        if (sourceRoots != null)
            return sourceRoots;

        sourceRoots = new HashSet<>();
        for (Source source : getSources()) {
            source.getSourceRoot().ifPresent(sourceRoot ->
                sourceRoots.add(sourceRoot));
        }

        return sourceRoots;
    }

    // ----------------------------------------------------------------------
    // Libraries
    // ----------------------------------------------------------------------

    @Override
    public RuntimeLibrary getRuntimeLibrary() {
        String runtimeName = GuessRuntimeLibrary.guessRuntimeLibrary(this);

        LibraryFinder lfinder = getProject().getLibraryFinder();

        RuntimeLibrary runtimeLibrary = lfinder.getRuntimeLibrary(runtimeName);
        if (runtimeLibrary == null) {
            logger.errorf("JDK Library %s not available. Uses the default jdk8", runtimeName);
            runtimeLibrary = lfinder.getRuntimeLibrary(GuessRuntimeLibrary.DEFAULT_JAVA_RUNTIME_LIBRARY);
        }
        if (runtimeLibrary == null)
            runtimeLibrary = new InvalidLibrary(GuessRuntimeLibrary.DEFAULT_JAVA_RUNTIME_LIBRARY, getProject());

        return runtimeLibrary;
    }

    @Override
    public Set<Library> getDeclaredLibraries() {
        if (libraries != null)
            return libraries;

        libraries = new HashSet<>();

        collectLibraries(libraries);

        return libraries;
    }

    protected void collectLibraries(Set<Library> collectedLibraries) {
        collectLocalLibraries(collectedLibraries);
        collectEclipseLibraries(collectedLibraries);
        collectIvyLibraries(collectedLibraries);
        collectMavenLibraries(collectedLibraries);
        collectGradleLibraries(collectedLibraries);
    }

    protected void collectLocalLibraries(Set<Library> collectedLibraries) {
        List<File> jarFiles = new ArrayList<>();
        getDirectories().forEach(dir ->{
            FileUtils.listFiles(jarFiles, dir, FileFilters.IS_JAR);
        });

        jarFiles.stream()
            .map(jarFile -> ArchiveUtils.newLibrary(jarFile, this))
            .forEach(library -> collectedLibraries.add(library));
    }

    protected void collectEclipseLibraries(Set<Library> collectedLibraries) {
        ClasspathFile classpathFile = new ClasspathFile(moduleHome);
        if (!classpathFile.exists())
            return;

        List<MavenCoords> coordList = classpathFile.getMavenLibraries()
            .stream()
            .map(MavenCoords::of)
            .collect(Collectors.toList());

        MavenDownloader md = project.getLibraryDownloader();

        coordList
            .stream()
            .map(coords -> new MavenLibrary(coords, md, project))
            .forEach(collectedLibraries::add);
    }

    protected void collectIvyLibraries(Set<Library> collectedLibraries) {
        List<IvyFile> ivyFiles = FileUtils.asList(getModuleHome().listFiles((dir, name) -> name.startsWith("ivy")))
            .stream()
            .map(IvyFile::new)
            .collect(Collectors.toList());

        if (ivyFiles.isEmpty())
            return;

        List<MavenCoords> coordList = new ArrayList<>();
        ivyFiles.forEach(ivyFile -> {
            coordList.addAll(ivyFile.getDependencyCoords());
        });

        MavenDownloader md = project.getLibraryDownloader();

        coordList.stream()
            .map(lcoords -> new MavenLibrary(lcoords, md, project))
            .forEach(collectedLibraries::add);
    }

    protected void collectGradleLibraries(Set<Library> collectedLibraries) {

    }

    protected void collectMavenLibraries(Set<Library> collectedLibraries) {
        MavenDownloader md = project.getLibraryDownloader();
        MavenPom mavenPom = new MavenPom(moduleHome, md);
        if (!mavenPom.exists())
            return;

        mavenPom.getDependencyCoords().stream()
            .map(coords -> new MavenLibrary(coords, md, project))
            .forEach(collectedLibraries::add);
    }

    @Override
    public Library getLibrary(String nameOrId) {
        Library selected = project.getLibrary(nameOrId);
        if (selected != null)
            return selected;

        for (Library library : getDeclaredLibraries()) {
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


    /**
     * List of local libraries (*.jar files)
     */
    // protected List<Library> getLocalLibraries() {
    //     List<File> jarFiles = new ArrayList<>();
    //
    //     // check the sub directories (are EXCLUDED the subdirectories that
    //     // contain modules)
    //     getDirectories().forEach(dir ->{
    //         FileUtils.listFiles(jarFiles, dir, FileFilters.IS_JAR);
    //     });
    //
    //     return new HashSet<>(jarFiles).stream()
    //         .map(jarFile -> ArchiveUtils.newLibrary(jarFile, this))
    //         .collect(Collectors.toList());
    // }

    /**
     * List of Maven libraries, specified by a Maven Coordinate
     */
    // protected List<Library> getMavenLibraries() {
    //     return Collections.emptyList();
    // }

    @Override
    public Set<String> getMavenRepositories() {
        return Collections.emptySet();
    }

    // ----------------------------------------------------------------------
    // Resources
    // ----------------------------------------------------------------------

    @Override
    public List<Resource> getResources() {

        List<Resource> resources = new ArrayList<>();

        // local resources
        resources.addAll(getBaseProject().getResources(moduleHome, this));

        // sub resources
        getDirectories().forEach(dir -> {
            List<Resource> reslist = getBaseProject().getResources(dir, this);
            resources.addAll(reslist);
        });

        return resources;
    }

    @Override
    public Resource getResource(String nameOrId) {
        for (Resource resource : getResources()) {
            if (resource.getId().equals(nameOrId))
                return resource;
            if (resource.getName().getFullName().equals(nameOrId))
                return resource;
            if (resource.getName().getName().equals(nameOrId))
                return resource;
            if (resource.getPath().equals(nameOrId))
                return resource;
        }
        return null;
    }

    // ----------------------------------------------------------------------
    // Types
    // ----------------------------------------------------------------------

    @Override
    public boolean isEmpty() {
        return getSources().isEmpty();
    }

    @Override
    public Set<Type> getTypes() {

        // cache names:
        //
        //      dependency.{project}.module.types
        //

        String cacheName = String.format("dependency.%s.module.types", project.getId());
        Cache<String, Set<Type>> cache = CacheManager.getCache(cacheName);

        return cache.get(getId(), () -> {
            Set<Type> types = new TreeSet<>();

            getSources().forEach(source ->
                        types.addAll(source.getTypes()));

            return types;
        });
    }

    @Override
    public Set<RefType> getUsedTypes() {
        // EXTERNAL USED types: LOCAL USED types MINUS LOCAL DEFINED types
        Set<RefType> definedTypes = new HashSet<>(getTypes());

        // cache names:
        //
        //      dependency.{project}.module.usedTypes
        //

        String cacheName = String.format("dependency.%s.module.usedTypes", project.getId());
        Cache<String, Set<RefType>> cache = CacheManager.getCache(cacheName);

        return cache.get(getId(), () -> {
            Set<RefType> usedTypes = new TreeSet<>();

            getSources().forEach(source ->
                    usedTypes.addAll(source.getUsedTypes()));

            return SetUtils.differenceOrdered(usedTypes, definedTypes);
        });
    }

    // ----------------------------------------------------------------------
    // Implementations
    // ----------------------------------------------------------------------

    protected List<File> getDirectories() {
        if (directories == null)
            directories = getBaseProject().getDirectories(moduleHome);
        return directories;
    }

    private BaseProject getBaseProject() {
        return (BaseProject) project;
    }


    // ----------------------------------------------------------------------
    // modules comparator
    // ----------------------------------------------------------------------

    private static class ModuleComparator implements Comparator<Module> {

        @Override
        public int compare(Module o1, Module o2) {
            // int cmp = o2.getSourceRoots().size() - o1.getSourceRoots().size();
            int cmp = o2.getSources().size() - o1.getSources().size();
            if (cmp == 0)
                cmp = o1.getName().getFullName().compareTo(o2.getName().getFullName());
            return cmp;
        }
    }

    public static final ModuleComparator COMPARATOR = new ModuleComparator();

    // ----------------------------------------------------------------------
    // Overrides
    // ----------------------------------------------------------------------

    @Override
    public String toString() {
        return String.format("%s[%s, %d]",
            getClass().getSimpleName(),
            this.getName().getFullName(),
            // this.getSourceRoots().size()
            this.getSources().size()
        );
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
