package jext.sourcecode.project.info.library;

import jext.name.EmptyName;
import jext.name.Name;
import jext.name.Named;
import jext.name.NamedObject;
import jext.name.PathName;
import jext.name.VersionName;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryStatus;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.info.InfoModule;
import jext.sourcecode.project.info.InfoProject;
import jext.sourcecode.project.java.maven.MavenName;
import jext.util.FileUtils;
import jext.util.MapUtils;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class InfoLibrary extends NamedObject implements Library {

    protected InfoProject project;
    protected Map<String, Object> info;
    protected LibraryType libraryType;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public InfoLibrary(InfoModule module, LibraryType libraryType, Map<String, Object> info) {
        this((InfoProject)(module.getProject()), libraryType, info);
        // this.module = module;
    }

    public InfoLibrary(InfoProject project, LibraryType libraryType, Map<String, Object> info) {
        super(EmptyName.empty());

        this.project = project;
        this.info = info;
        this.libraryType = libraryType;
        this.id =  MapUtils.get(info, "id");

        String fullname = MapUtils.get(info, "fullname");
        String version  = MapUtils.get(info, "version");

        switch(libraryType) {
            case MAVEN:
                this.name = MavenName.of(fullname);
                this.libraryType = LibraryType.REMOTE;
                break;
            case REMOTE:
                this.name = MavenName.of(fullname);
                break;
            case LOCAL:
                this.name = VersionName.of(fullname, version);
                break;
            case RUNTIME:
            case INVALID:
            default:
                this.name = PathName.of(fullname);
                break;
        }

        if (this.id == null)
            setNameWithId(this.name);
    }

    @Override
    public String getLanguage() {
        return MapUtils.get(info, "language");
    }

    @Override
    public String getVersion() {
        return MapUtils.get(info, "version");
    }

    @Override
    public Project getProject() {
        return project;
    }

    // @Override
    // public Module getModule() {
    //     return module;
    // }

    @Override
    public LibraryType getLibraryType() {
        return libraryType;
    }

    @Override
    public LibraryStatus getLibraryStatus() {
        return LibraryStatus.VALID;
    }

    // @Override
    // public String getDigest() {
    //     return MapUtils.getString(info, "digest");
    // }

    @Override
    public String getPath() {
        switch (libraryType) {
            case RUNTIME:
                return FileUtils.getAbsolutePath(getFile());
            case LOCAL:
                return FileUtils.relativePath(project.getProjectHome(), getFile());
            case REMOTE:
                List<File> files = getFiles();
                if (files.size() == 1)
                    return FileUtils.getAbsolutePath(files.get(0));
                else
                    return FileUtils.getAbsolutePath(getFile());
            default:
                return "";
        }
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
        File file = getFile();
        return file != null && file.exists()
            && getFiles().stream().allMatch(File::exists);
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
