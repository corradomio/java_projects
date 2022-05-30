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
import jext.sourcecode.project.java.maven.MavenRepository;
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

    @Override
    public Sources getSources() {
        if (sources != null)
            return sources;

        sources = new SourcesImpl(this);
        Map<String, Object> sinfos = MapUtils.get(info, "sources");
        for (String fullname : sinfos.keySet()) {
            Map<String, Object> sinfo = MapUtils.get(sinfos, fullname);
            if (project.isAccepted(MapUtils.get(sinfo, "path"))) {
                InfoSource source  = new InfoSource(this, sinfo);
                sources.add(source);
                // idMap.put(source.getId(), source);
                // nameMap.put(source.getName().getFullName(), source);
                // pathMap.put(source.getPath(), source);
            }
        }

        // sources.sort(Comparable::compareTo);

        return sources;
    }

    // @Override
    // public List<File> getSourceFiles() {
    //     return getSources().stream()
    //         .map(Resource::getFile)
    //         .collect(Collectors.toList());
    // }

    // @Override
    // public Set<String> getSourceRoots() {
    //     return new HashSet<>(MapUtils.get(info, "sourceRoots"));
    // }

    // @Override
    // public List<File> getSourceRootDirectories() {
    //     File moduleHome = getModuleHome();
    //     return getSourceRoots().stream()
    //         .map(sourceRoot -> new File(moduleHome, sourceRoot))
    //         .collect(Collectors.toList());
    // }

    // @Override
    // public Source getSource(String nameOrPathOrId) {
    //     if (pathMap.containsKey(nameOrPathOrId))
    //         return pathMap.get(nameOrPathOrId);
    //     if (nameMap.containsKey(nameOrPathOrId))
    //         return nameMap.get(nameOrPathOrId);
    //     if (idMap.containsKey(nameOrPathOrId))
    //         return nameMap.get(nameOrPathOrId);
    //
    //     List<Source> sources = getSources();
    //     for (Source source : sources) {
    //         if (source.getName().getFullName().equals(nameOrPathOrId))
    //             return source;
    //         if (source.getId().equals(nameOrPathOrId))
    //             return source;
    //     }
    //     return null;
    // }

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

    @Override
    public Library getRuntimeLibrary() {
        String rtlib = MapUtils.get(info, "runtimeLibrary");
        return project.getLibraries().getLibrary(rtlib);
    }

    @Override
    public Set<Library> getDeclaredLibraries() {
        List<String> libraryNames = MapUtils.get(info, "declaredLibraries");
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
        return null;
    }

    // ----------------------------------------------------------------------
    // Resources
    // ----------------------------------------------------------------------

    @Override
    public Resources getResources() {
        return new ResourcesImpl(this);
    }

    // @Override
    // public Resource getResource(String nameOrId) {
    //     File directory = FileUtils.addParentPath(getModuleHome(), nameOrId);
    //
    //     List<File> resources = FileUtils.listFiles(directory);
    //     for (File resourceFile : resources) {
    //         Resource resource = new ResourceFile(resourceFile, this);
    //         if (resource.getName().getFullName().equals(nameOrId))
    //             return resource;
    //         if (resource.getId().equals(nameOrId))
    //             return resource;
    //         if (resource.getName().getName().equals(nameOrId))
    //             return resource;
    //     }
    //
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
