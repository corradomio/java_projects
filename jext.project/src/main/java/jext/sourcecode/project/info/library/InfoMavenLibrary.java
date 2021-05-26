package jext.sourcecode.project.info;

import jext.name.Name;
import jext.name.Named;
import jext.name.PathName;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.maven.MavenName;
import jext.util.MapUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InfoMavenLibrary implements Library {

    private InfoProject project;
    private InfoModule module;
    private Map<String, Object> info;

    public InfoMavenLibrary(InfoProject project, String libraryName) {
        this.project = project;
        this.info = MapUtils.asMap(
            "fullname", libraryName
        );
    }

    public InfoMavenLibrary(InfoModule module, String libraryName) {
        this.module = module;
        this.project = (InfoProject) module.getProject();
        this.info = MapUtils.asMap(
            "fullname", libraryName
        );
    }

    public InfoMavenLibrary(InfoModule module, Map<String, Object> info) {
        this.module = module;
        this.project = (InfoProject) module.getProject();
        this.info = info;
    }

    public InfoMavenLibrary(InfoProject project, Map<String, Object> info) {
        this.project = project;
        this.info = info;
    }

    @Override
    public String getId() {
        return MapUtils.get(info, "id");
    }

    @Override
    public Name getName() {
        return null;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public Project getProject() {
        return project;
    }

    @Override
    public Module getModule() {
        return null;
    }

    @Override
    public String getModuleId() {
        return null;
    }

    @Override
    public LibraryType getLibraryType() {
        return MapUtils.get(info, "libraryInfo");
    }

    @Override
    public String getPath() {
        return null;
    }

    @Override
    public File getFile() {
        return null;
    }

    @Override
    public String getDigest() {
        return null;
    }

    @Override
    public List<File> getFiles() {
        return null;
    }

    @Override
    public Set<? extends RefType> getTypes() {
        return Collections.emptySet();
    }

    @Override
    public boolean contains(Name typeName) {
        return false;
    }

    @Override
    public String getVersion() {
        return null;
    }

    @Override
    public String getLatest() {
        return null;
    }

    @Override
    public int compareTo(@NotNull Named o) {
        return getName().compareTo(o.getName());
    }
}
