package jext.sourcecode.project.info.library;

import jext.name.Name;
import jext.name.Named;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.info.InfoModule;
import jext.sourcecode.project.info.InfoProject;
import jext.util.FileUtils;
import jext.util.MapUtils;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class InfoLibrary implements Library {

    protected InfoProject project;
    protected InfoModule module;
    protected Name name;
    protected Map<String, Object> info;
    protected LibraryType libraryType;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public InfoLibrary(InfoModule module, LibraryType libraryType, Map<String, Object> info) {
        this((InfoProject)(module.getProject()), libraryType, info);
        this.module = module;
    }

    public InfoLibrary(InfoProject project, LibraryType libraryType, Map<String, Object> info) {
        this.project = project;
        this.info = info;
        this.libraryType = libraryType;
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
    public String getVersion() {
        return MapUtils.get(info, "version");
    }

    @Override
    public String getLatest() {
        return null;
    }

    @Override
    public Project getProject() {
        return project;
    }

    @Override
    public Module getModule() {
        return module;
    }

    @Override
    public String getModuleId() {
        return module != null ? module.getId() : null;
    }

    @Override
    public LibraryType getLibraryType() {
        return libraryType;
    }

    @Override
    public long getDigest() {
        return MapUtils.getLong(info, "digest");
    }

    @Override
    public String getPath() {
        return MapUtils.get(info, "path");
    }

    @Override
    public File getFile() {
        return FileUtils.toFile(MapUtils.get(info, "file"));
    }

    @Override
    public List<File> getFiles() {
        return FileUtils.toFiles(MapUtils.get(info, "files"));
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public Set<RefType> getTypes() {
        return Collections.emptySet();
    }

    @Override
    public boolean contains(Name typeName) {
        return false;
    }

    @Override
    public int compareTo(Named o) {
        return getName().compareTo(o.getName());
    }
}
