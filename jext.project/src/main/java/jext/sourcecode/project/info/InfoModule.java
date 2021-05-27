package jext.sourcecode.project.info;

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
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

public class InfoModule implements Module {

    private InfoProject project;
    private Map<String, Object> info;
    private List<Source> sources;
    private Name name;

    InfoModule(InfoProject project, Map<String, Object> info) {
        this.project = project;
        this.info = info;
        this.name = new PathName(MapUtils.get(info, "fullname"));
    }

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
    public File getModuleHome() {
        return new File(project.getProjectHome(), getPath());
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
            Map<String, Object> sinfo = MapUtils.get(info, fullname);
            sources.add(new InfoSource(this, sinfo));
        }

        return sources;
    }

    @Override
    public Set<File> getSourceRoots() {
        List<String> sroots = MapUtils.get(info, "sourceRoots");
        return sroots.stream()
            .map(sroot -> new File(getModuleHome(), sroot))
            .collect(Collectors.toSet());
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

    @Override
    public List<Library> getLibraries() {
        List<String> libraries = MapUtils.get(info, "libraries");
        return libraries.stream()
            .map(name -> project.getLibrary(name))
            .collect(Collectors.toList());
    }

    @Override
    public List<Library> getDeclaredLibraries() {
        List<String> libraries = MapUtils.get(info, "declaredLibraries");
        return libraries.stream()
            .map(name -> project.getLibrary(name))
            .collect(Collectors.toList());
    }

    @Override
    public Library getLibrary(String nameOrId) {
        List<Library> libraries = getDeclaredLibraries();
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

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Set<? extends Type> getTypes() {
        return Collections.emptySet();
    }

    @Override
    public Set<RefType> getUsedTypes() {
        return Collections.emptySet();
    }

    @Override
    public int compareTo(Named o) {
        return getName().compareTo(o.getName());
    }
}
