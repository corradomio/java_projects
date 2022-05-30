package jext.sourcecode.project.util;

import jext.cache.Cache;
import jext.cache.CacheManager;
import jext.logging.Logger;
import jext.name.PathName;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryRepository;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.Resources;
import jext.sourcecode.project.Sources;
import jext.sourcecode.project.Type;
import jext.sourcecode.project.java.maven.MavenRepository;
import jext.util.FileUtils;
import jext.util.LongHash;
import jext.util.SetUtils;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

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
    protected SourcesImpl sources;
    protected ResourcesImpl resources;

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
        setNameWithId(PathName.of(this.path));
        setRefIdFromName();

        this.logger = Logger.getLogger("jext.project.module.%s.%s.%s",
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

    @Override
    public String getDigest() {
        long[] digest = new long[1];
        getSources().forEach(source -> {
            digest[0] = LongHash.concat(digest[0], LongHash.fromString(source.getDigest()));
        });
        return LongHash.toString(digest[0]);
    }

    // ----------------------------------------------------------------------
    // Module dependencies
    // ----------------------------------------------------------------------

    @Override
    public List<Module> getDependencies() {
        return Collections.emptyList();
    }

    // ----------------------------------------------------------------------
    // Module content
    // ----------------------------------------------------------------------

    @Override
    public abstract Sources getSources();

    // ----------------------------------------------------------------------
    // Libraries
    // ----------------------------------------------------------------------

    @Override
    public abstract Library getRuntimeLibrary();

    @Override
    public abstract Set<Library> getDeclaredLibraries();

    @Override
    public Library getLibrary(String nameOrId) {
        Library selected = project.getLibraries().getLibrary(nameOrId);
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

    @Override
    public Set<String> getMavenRepositories() {
        return Collections.emptySet();
    }

    @Override
    public Set<LibraryRepository> getLibraryRepositories() {
        Set<LibraryRepository> librepos = new HashSet<>();

        for (String mavenUrl : getMavenRepositories())
            librepos.add(new MavenRepository(mavenUrl));
        return librepos;
    }

    // ----------------------------------------------------------------------
    // Resources
    // ----------------------------------------------------------------------

    @Override
    public Resources getResources() {
        if (resources != null)
            return resources;

        resources = new ResourcesImpl(this);

        return resources;
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

            return SetUtils.difference(usedTypes, definedTypes, true);
        });
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
