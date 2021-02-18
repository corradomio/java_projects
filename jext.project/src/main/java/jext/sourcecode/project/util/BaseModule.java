package jext.sourcecode.project.util;

import jext.name.PathName;
import jext.sourcecode.project.GuessRuntimeLibrary;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.Resource;
import jext.sourcecode.project.Source;
import jext.cache.Cache;
import jext.cache.CacheManager;
import jext.io.util.FileFilters;
import jext.logging.Logger;
import jext.sourcecode.project.maven.LibrarySet;
import jext.sourcecode.resources.libraries.ArchiveUtils;
import jext.util.FileUtils;
import jext.util.SetUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public abstract class BaseModule extends NamedObject implements /*Directory*/Module {

    // ----------------------------------------------------------------------
    // Protected fields
    // ----------------------------------------------------------------------

    protected Project project;
    protected String path;
    protected Properties properties;
    protected File moduleHome;

    protected List<File> directories;
    protected List<Library> libraries;
    protected List<Module> dependencies;
    protected List<Source> sources;
    // protected SourceRoots sourceRoots;

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
        setName(new PathName(this.path));

        this.logger = Logger.getLogger("%s.%s.%s",
            getClass().getSimpleName(),
            project.getName().getName(),
            getName().getName());
    }

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

    // ----------------------------------------------------------------------
    // Module dependencies
    // ----------------------------------------------------------------------

    // @Override
    // public List<Module> getDependencies(boolean recursive) {
    //     if (dependencies == null)
    //         dependencies = getDependencies();
    //
    //     if (!recursive)
    //         return dependencies;
    //     else
    //         return getRecursiveDependencies();
    // }

    @Override
    public List<Module> getDependencies() {
        //
        // WARN: DON'T CALL IT directly!
        //
        // It is better to call 'getDependencies(false)'  because
        // that method checks if 'dependencies' is already evaluated!
        //

        if (dependencies != null)
            return dependencies;

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
        Set<RefType> definedTypes = getTypes();

        // EXTERNAL USED types: LOCAL USED types MINUS LOCAL DEFINED types
        Set<RefType> usedTypes = SetUtils.differenceOrdered(getUsedTypes(), definedTypes);

        project.getModules().forEach(dmodule -> {
            if (dmodule.getName().equals(this.getName()))
                return;

            // EXTERNAL DEFINED types: all EXTERNAL DEFINED types MINUS LOCAL DEFINED types
            // to be sure to consider ONLY effective types NOT DEFINED locally
            Set<RefType> dtypes = SetUtils.differenceOrdered(dmodule.getTypes(), definedTypes);

            // LOCAL USED types available in the EXTERNAL DEFINED types
            Set<RefType> itypes = SetUtils.intersectionOrdered(usedTypes, dtypes);
            int isize = itypes.size();
            if (isize == 0)
                return;

            // there is a dependency between the current module AND dmodule
            orderedDeps.add(dmodule);
        });

        this.dependencies = orderedDeps.isEmpty()
            ? Collections.emptyList()
            : new ArrayList<>(orderedDeps);

        return this.dependencies;
    }

    // evaluate the recursive dependencies (breath first)
    // private List<Module> getRecursiveDependencies() {
    //     Queue<Module> toVisit = new LinkedList<>(getDependencies());
    //     Set<Module> visited = new HashSet<>();
    //
    //     while (!toVisit.isEmpty()) {
    //         Module dmodule = toVisit.remove();
    //         if (visited.contains(dmodule))
    //             continue;
    //
    //         visited.add(dmodule);
    //         toVisit.addAll(dmodule.getDependencies());
    //     }
    //
    //     return new ArrayList<>(visited);
    // }

    // ----------------------------------------------------------------------
    // Module content
    // ----------------------------------------------------------------------

    // @Override
    // public List<SourceRoot> getSourceRoots() {
    //     if (this.sourceRoots != null)
    //         return this.sourceRoots;
    //
    //     this.sourceRoots = new SourceRoots(this);
    //
    //     getDirectories().forEach(dir -> {
    //         getBaseProject().getSources(dir, this)
    //             .forEach(source -> {
    //                 sourceRoots.add(source);
    //             });
    //     });
    //
    //     return this.sourceRoots;
    // }

    @Override
    public List<Source> getSources() {
        if (sources != null)
            return sources;

        sources = new ArrayList<>();

        getDirectories().forEach(dir -> {
            List<Source> srclist = getBaseProject().getSources(dir, this);
            sources.addAll(srclist);
        });

        return sources;
    }

    @Override
    public Source getSource(String name) {
        // for (SourceRoot sourceRoot : getSourceRoots())
        // for (Source source : sourceRoot.getSources()) {
        for (Source source : getSources()) {
            if (source.getId().equals(name))
                return source;
            if (source.getName().getFullName().equals(name))
                return source;
            if (source.getName().getName().equals(name))
                return source;
            if (source.getPath().equals(name))
                return source;
        }
        return null;
    }

    @Override
    public Set<File> getSourceRoots() {
        Set<File> sourceRoots = new HashSet<>();
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
    public Library getRuntimeLibrary() {
        String runtimeName = GuessRuntimeLibrary.guessRuntimeLibrary(this);

        LibraryFinder lfinder = getProject().getLibraryFinder();

        Library runtimeLibrary = lfinder.getLibrary(runtimeName);
        if (runtimeLibrary == null) {
            logger.errorf("JDK Library %s not available. Uses the default jdk8", runtimeName);
            runtimeLibrary = lfinder.getLibrary(GuessRuntimeLibrary.DEFAULT_JAVA_RUNTIME_LIBRARY);
        }

        return runtimeLibrary;
    }

    @Override
    public List<Library> getLibraries() {
        LibrarySet projectLibraries = (LibrarySet) project.getLibraries();
        return projectLibraries.resolveAll(getDefinedLibraries());
    }

    @Override
    public List<Library> getDefinedLibraries() {
        if (libraries != null)
            return libraries;

        libraries = new ArrayList<>();

        libraries.addAll(getLocalLibraries());

        libraries.addAll(getMavenLibraries());

        return libraries;
    }

    @Override
    public Library getLibrary(String nameOrId) {
        Library selected = project.getLibrary(nameOrId);
        if (selected != null)
            return selected;

        for (Library library : getDefinedLibraries()) {
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
    protected List<Library> getLocalLibraries() {
        List<File> jarFiles = new ArrayList<>();

        // check the module directory
        // {
        //     FileUtils.listFiles(jarFiles, moduleRoot, FileFilters.IS_JAR);
        // }

        // check the sub directories (are EXCLUDED the subdirectories that
        // contain modules)
        getDirectories().forEach(dir ->{
            FileUtils.listFiles(jarFiles, dir, FileFilters.IS_JAR);
        });

        return new HashSet<>(jarFiles).stream()
            .map(jarFile -> ArchiveUtils.newLibrary(jarFile, this))
            .collect(Collectors.toList());
    }

    /**
     * List of Maven libraries, specified by a Maven Coordinate
     */
    protected List<Library> getMavenLibraries() {
        return Collections.emptyList();
    }

    protected List<String> getMavenRepositories() {
        return Collections.emptyList();
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
    public Set<RefType> getTypes() {

        // cache names:
        //
        //      dependency.{project}.module.types
        //      dependency.{project}.module.allTypes
        //

        Cache<String, Set<RefType>> cache = CacheManager.getCache(String.format("dependency.%s.module.types", project.getId()));

        Set<RefType> definedTypes = cache.get(getId(), () -> {
            Set<RefType> types = new HashSet<>();

            getSources().forEach(source ->
                        types.addAll(source.getTypes()));

            // getSourceRoots().forEach(sourceRoot -> {
            //     sourceRoot.getSources().forEach(source ->
            //         types.addAll(source.getTypes()));
            // });

            return types;
        });

        return definedTypes;
    }

    @Override
    public Set<RefType> getUsedTypes() {
        Cache<String, Set<RefType>> cache = CacheManager.getCache(String.format("dependency.%s.module.usedTypes", project.getId()));

        return cache.get(getId(), () -> {
                Set<RefType> usedTypes = new HashSet<>();

                getSources().forEach(source ->
                        usedTypes.addAll(source.getUsedTypes()));

                // getSourceRoots().forEach(sourceRoot -> {
                //     sourceRoot.getSources().forEach(source ->
                //         usedTypes.addAll(source.getUsedTypes()));
                // });

                return usedTypes;
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

}
