package jext.sourcecode.project.java;

import jext.cache.Cache;
import jext.cache.CacheManager;
import jext.io.filters.FileFilters;
import jext.maven.MavenCoords;
import jext.maven.MavenDownloader;
import jext.maven.MavenPom;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.Resource;
import jext.sourcecode.project.Resources;
import jext.sourcecode.project.Source;
import jext.sourcecode.project.Sources;
import jext.sourcecode.project.java.ant.util.IvyFile;
import jext.sourcecode.project.java.eclipse.util.ClasspathFile;
import jext.sourcecode.project.java.libraries.ArchiveUtils;
import jext.sourcecode.project.java.maven.MavenLibrary;
import jext.sourcecode.project.java.util.JavaSourcesImpl;
import jext.sourcecode.project.none.InvalidLibrary;
import jext.sourcecode.project.util.BaseModule;
import jext.sourcecode.project.util.BaseProject;
import jext.sourcecode.project.util.ResourcesImpl;
import jext.util.FileUtils;
import jext.util.SetUtils;
import jext.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class JavaBaseModule extends BaseModule {

    // ----------------------------------------------------------------------
    // Protected fields
    // ----------------------------------------------------------------------

    protected List<Module> dependencies;

    // ----------------------------------------------------------------------
    // Module dependencies
    // ----------------------------------------------------------------------

    protected JavaBaseModule(File moduleHome, Project project) {
        super(moduleHome, project);
    }

    // ----------------------------------------------------------------------
    // Module content
    // ----------------------------------------------------------------------

    @Override
    public Sources getSources() {
        if (sources != null)
            return sources;

        sources = new JavaSourcesImpl(this);
        directories = getBaseProject().getDirectories(moduleHome);
        getDirectories().forEach(dir -> {
            List<Source> srclist = ((JavaBaseProject)project).getSources(dir, this);
            sources.addAll(srclist);
        });

        return sources;
    }

    // ----------------------------------------------------------------------
    // Resources
    // ----------------------------------------------------------------------

    @Override
    public Resources getResources() {
        if (resources != null)
            return resources;

        resources = new ResourcesImpl(this);

        // local resources
        resources.addAll(getBaseProject().getResources(moduleHome, this));

        // sub resources
        getDirectories().forEach(dir -> {
            List<Resource> reslist = getBaseProject().getResources(dir, this);
            resources.addAll(reslist);
        });

        return resources;
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
        Set<RefType> definedTypes = getTypes();

        // EXTERNAL USED types: LOCAL USED types MINUS LOCAL DEFINED types
        Set<RefType> usedTypes = getUsedTypes();

        project.getModules().forEach(dmodule -> {
            if (dmodule.getName().equals(this.getName()))
                return;

            // EXTERNAL DEFINED types: all EXTERNAL DEFINED types MINUS LOCAL DEFINED types
            // to be sure to consider ONLY effective types NOT DEFINED locally
            Set<RefType> ddefinedTypes = dmodule.getTypes();
            Set<RefType> dtypes = SetUtils.difference(ddefinedTypes, definedTypes, true);

            // LOCAL USED types available in the EXTERNAL DEFINED types
            Set<RefType> itypes = SetUtils.intersection(usedTypes, dtypes, true);
            int isize = itypes.size();
            if (isize == 0)
                return;

            // there is a dependency between the current module AND dmodule
            orderedDeps.add(dmodule);
        });

        return orderedDeps;
    }

    // ----------------------------------------------------------------------
    // Libraries
    // ----------------------------------------------------------------------

    @Override
    public Library getRuntimeLibrary() {
        // check if it is possible to retrieve the runtime library locally
        String runtimeName = GuessRuntimeLibrary.guessRuntimeLibrary(this);

        // check if the project has a specified jdk
        if (StringUtils.isEmpty(runtimeName))
            runtimeName = project.getRuntimeLibrary();

        return getRuntimeLibrary(runtimeName);
    }

    protected Library getRuntimeLibrary(String runtimeName) {
        String rtLibrary = project.getRuntimeLibrary();;
        LibraryFinder lfinder = getProject().getLibraryFinder();

        Library runtimeLibrary = lfinder.getRuntimeLibrary(runtimeName);
        if (runtimeLibrary == null) {
            logger.errorf("JDK Library %s not available. Used the default %s", runtimeName, rtLibrary);
            runtimeLibrary = lfinder.getRuntimeLibrary(rtLibrary);
        }

        if (runtimeLibrary == null) {
            logger.errorf("JDK Library %s not available. Used an 'empty library'", runtimeName);
            runtimeLibrary = new InvalidLibrary(runtimeName, getProject());
        }

        return runtimeLibrary;
    }

    @Override
    public Set<Library> getDeclaredLibraries() {
        if (libraries != null)
            return libraries;

        libraries = new HashSet<>();

        collectLocalLibraries(libraries);
        collectEclipseLibraries(libraries);
        collectIvyLibraries(libraries);
        collectMavenLibraries(libraries);
        collectGradleLibraries(libraries);

        return libraries;
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

        MavenDownloader md = (MavenDownloader) project.getLibraryDownloader();

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

        MavenDownloader md = (MavenDownloader) project.getLibraryDownloader();

        coordList.stream()
            .map(lcoords -> new MavenLibrary(lcoords, md, project))
            .forEach(collectedLibraries::add);
    }

    protected void collectGradleLibraries(Set<Library> collectedLibraries) {

    }

    protected void collectMavenLibraries(Set<Library> collectedLibraries) {
        MavenDownloader md = (MavenDownloader) project.getLibraryDownloader();
        MavenPom mavenPom = new MavenPom(moduleHome, md);
        if (!mavenPom.exists())
            return;

        mavenPom.getDependencyCoords().stream()
            .map(coords -> new MavenLibrary(coords, md, project))
            .forEach(collectedLibraries::add);
    }

    // ----------------------------------------------------------------------
    // Types
    // ----------------------------------------------------------------------

    @Override
    public Set<RefType> getTypes() {

        // cache names:
        //
        //      dependency.{project}.module.types
        //

        String cacheName = String.format("dependency.%s.module.types", project.getId());
        Cache<String, Set<RefType>> cache = CacheManager.getCache(cacheName);

        return cache.get(getId(), () -> {
            Set<RefType> types = new TreeSet<>();

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

            return SetUtils.difference(usedTypes, definedTypes, true);
        });
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
