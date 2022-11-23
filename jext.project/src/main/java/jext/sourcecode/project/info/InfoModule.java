package jext.sourcecode.project.info;

import jext.name.Name;
import jext.name.Named;
import jext.name.PathName;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryRepository;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.Resources;
import jext.sourcecode.project.Sources;
import jext.sourcecode.project.Type;
import jext.sourcecode.project.info.util.InfoSourcesImpl;
import jext.sourcecode.project.java.maven.MavenRepository;
import jext.sourcecode.project.none.InvalidLibrary;
import jext.sourcecode.project.util.ResourcesImpl;
import jext.sourcecode.project.util.SourcesImpl;
import jext.util.MapUtils;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

public class InfoModule implements Module, Comparable<Named> {

    private final InfoProject project;
    private final Map<String, Object> info;
    private final Name name;
    private SourcesImpl sources;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    InfoModule(InfoProject project, Map<String, Object> info) {
        if (info == null)
            throw new NullPointerException();
        this.project = project;
        this.info = info;
        this.name = PathName.of(MapUtils.get(info, "fullname"));
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public String getId() {
        return MapUtils.get(info, "id");
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public String getRefId() {
        return  MapUtils.get(info, "refId");
    }

    @Override
    public Project getProject() {
        return project;
    }

    @Override
    public Properties getProperties() {
        Properties properties = new Properties();
        properties.putAll(MapUtils.get(info, "properties"));
        return properties;
    }

    @Override
    public String getPath() {
        return MapUtils.get(info, "path");
    }

    @Override
    public String getDigest() {
        return MapUtils.getString(info, "digest");
    }

    @Override
    public File getModuleHome() {
        return new File(project.getProjectHome(), getPath());
    }

    @Override
    public List<Module> getDependencies() {
        List<String> mdepends = MapUtils.get(info, "dependencies");
        return mdepends.stream()
            .map(mname -> project.getModules().getModule(mname))
            .collect(Collectors.toList());
    }

    // ----------------------------------------------------------------------
    // Structure
    // ----------------------------------------------------------------------

    @Override
    public Sources getSources() {
        if (sources != null)
            return sources;

        sources = new InfoSourcesImpl(this);

        Map<String, Object> sinfos = MapUtils.get(info, "sources");
        for (String fullname : sinfos.keySet()) {
            Map<String, Object> sinfo = MapUtils.get(sinfos, fullname);
            if (project.isAccepted(MapUtils.get(sinfo, "path"))) {
                InfoSource source  = new InfoSource(this, sinfo);
                sources.add(source);
            }
        }

        return sources;
    }

    // @Override
    public Set<String> getSourceRoots() {
        return new HashSet<>(MapUtils.get(info, "sourceRoots"));
    }

    // ----------------------------------------------------------------------
    // Repositories
    // ----------------------------------------------------------------------

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
    // Libraries
    // ----------------------------------------------------------------------

    @Override
    public Library getRuntimeLibrary() {
        String rtlib = MapUtils.get(info, "runtimeLibrary");
        return project.getLibraries().getLibrary(rtlib);
    }

    @Override
    public Set<Library> getDeclaredLibraries() {
        // List<String> libraryNames = MapUtils.get(info, "declaredLibraries");
        // Set<Library> libraries = new HashSet<>();
        // libraryNames.forEach(libraryName -> {
        //     Library library = project.getLibraries().getLibrary(libraryName);
        //     if (library != null)
        //         libraries.add(library);
        // });
        // return libraries;
        return getLibraries(MapUtils.get(info, "declaredLibraries"));
    }

    @Override
    public Set<Library> getLocalLibraries() {
        // List<String> libraryNames = MapUtils.get(info, "localLibraries");
        // Set<Library> libraries = new HashSet<>();
        // libraryNames.forEach(libraryName -> {
        //     Library library = project.getLibraries().getLibrary(libraryName);
        //     if (library != null)
        //         libraries.add(library);
        // });
        // return libraries;
        return getLibraries(MapUtils.get(info, "localLibraries"));
    }

    private Set<Library> getLibraries(List<String> libraryNames) {
        // for back compatibility
        if (libraryNames == null || libraryNames.isEmpty())
            return Collections.emptySet();

        Set<Library> libraries = new HashSet<>();
        libraryNames.forEach(libraryName -> {
            Library library = project.getLibraries().getLibrary(libraryName);
            if (library != null)
                libraries.add(library);
        });
        return libraries;
    }

    @Override
    public Library getLibrary(String nameOrId) {
        Set<Library> libraries = getDeclaredLibraries();
        for (Library library : libraries) {
            if (library.getName().getFullName().equals(nameOrId))
                return library;
            if (library.getId().equals(nameOrId))
                return library;
        }

        return new InvalidLibrary(nameOrId, project);
    }

    // ----------------------------------------------------------------------
    // Resources
    // ----------------------------------------------------------------------

    @Override
    public Resources getResources() {
        return new ResourcesImpl(this);
    }

    // ----------------------------------------------------------------------
    // Types
    // ----------------------------------------------------------------------

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Set<RefType> getTypes() {
        return Collections.emptySet();
    }

    @Override
    public Set<RefType> getUsedTypes() {
        return Collections.emptySet();
    }

    // ----------------------------------------------------------------------
    // Overrides
    // ----------------------------------------------------------------------

    @Override
    public boolean equals(Object obj) {
        Module that = (Module) obj;
        return this.getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }

    @Override
    public int compareTo(Named that) {
        return this.getName().compareTo(that.getName());
    }

    @Override
    public String toString() {
        return String.format("InfoModule[%s]", getName().getName());
    }

}
