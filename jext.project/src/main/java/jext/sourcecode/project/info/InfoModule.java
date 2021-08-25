package jext.sourcecode.project.info;

import jext.debug.Debug;
import jext.name.Name;
import jext.name.Named;
import jext.name.PathName;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.Resource;
import jext.sourcecode.project.Source;
import jext.sourcecode.project.Type;
import jext.util.MapUtils;

import java.io.File;
import java.util.ArrayList;
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
    private List<Source> sources;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    InfoModule(InfoProject project, Map<String, Object> info) {
        if (info == null)
            throw new NullPointerException();
        this.project = project;
        this.info = info;
        this.name = new PathName(MapUtils.get(info, "fullname"));
    }

    // ----------------------------------------------------------------------
    //
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
        return Integer.toString(getName().getFullName().hashCode(), 16);
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
    public long getDigest() {
        return MapUtils.getLong(info, "digest");
    }

    @Override
    public File getModuleHome() {
        return new File(project.getProjectHome(), getPath());
    }

    @Override
    public List<File> getSourceRootDirectories() {
        File moduleHome = getModuleHome();
        return getSourceRoots().stream()
            .map(sourceRoot -> new File(moduleHome, sourceRoot))
            .collect(Collectors.toList());
    }

    @Override
    public List<Module> getDependencies() {
        List<String> mdepends = MapUtils.get(info, "dependencies");
        return mdepends.stream()
            .map(mname -> project.getModule(mname))
            .collect(Collectors.toList());
    }

    @Override
    public List<Source> getSources() {
        if (sources != null)
            return sources;

        sources = new ArrayList<>();
        Map<String, Object> sinfos = MapUtils.get(info, "sources");
        for (String fullname : sinfos.keySet()) {
            Map<String, Object> sinfo = MapUtils.get(sinfos, fullname);
            if (project.isAccepted(MapUtils.get(sinfo, "path")))
                sources.add(new InfoSource(this, sinfo));
        }

        return sources;
    }

    @Override
    public Set<String> getSourceRoots() {
        return new HashSet<>(MapUtils.get(info, "sourceRoots"));
    }

    @Override
    public Source getSource(String nameOrId) {
        List<Source> sources = getSources();
        for (Source source : sources) {
            if (source.getName().getFullName().equals(nameOrId))
                return source;
            if (source.getId().equals(nameOrId))
                return source;
        }
        return null;
    }

    @Override
    public Set<String> getMavenRepositories() {
        return Collections.emptySet();
    }

    @Override
    public Library getRuntimeLibrary() {
        String rtlib = MapUtils.get(info, "runtimeLibrary");
        return project.getLibrary(rtlib);
    }

    // @Override
    // public Set<Library> getLibraries() {
    //     List<String> libraryNames = MapUtils.get(info, "libraries");
    //     Set<Library> libraries = new HashSet<>();
    //     libraryNames.forEach(libraryName -> {
    //         Library library = project.getLibrary(libraryName);
    //         if (library != null)
    //             libraries.add(library);
    //     });
    //     return libraries;
    // }

    @Override
    public Set<Library> getDeclaredLibraries() {
        List<String> libraryNames = MapUtils.get(info, "declaredLibraries");
        Set<Library> libraries = new HashSet<>();
        libraryNames.forEach(libraryName -> {
            Library library = project.getLibrary(libraryName);
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
        return null;
    }

    @Override
    public List<Resource> getResources() {
        return Collections.emptyList();
    }

    @Override
    public Resource getResource(String nameOrId) {
        return null;
    }

    // ----------------------------------------------------------------------
    // Resources
    // ----------------------------------------------------------------------

    // @Override
    // public List<Resource> getResources() {
    //     return Collections.emptyList();
    // }

    // @Override
    // public Resource getResource(String nameOrId) {
    //     return null;
    // }

    // ----------------------------------------------------------------------
    // Types
    // ----------------------------------------------------------------------

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Set<Type> getTypes() {
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
