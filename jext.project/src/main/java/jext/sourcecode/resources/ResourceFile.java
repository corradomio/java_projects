package jext.sourcecode.resources;

import jext.name.EmptyName;
import jext.name.NamedObject;
import jext.name.ObjectName;
import jext.name.PathName;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Resource;
import jext.util.FileUtils;
import jext.util.MimeTypes;
import jext.util.PathUtils;

import java.io.File;

public class ResourceFile extends NamedObject implements Resource {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    protected final Module module;
    protected final File file;
    protected final String path;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ResourceFile(File file, Module module) {
        super(EmptyName.empty());

        this.module = module;
        this.file = file;
        this.path =  FileUtils.relativePath(getProject().getProjectHome(), file);

        // name: relative path respect the module home directory
        String rpath = FileUtils.relativePath/* NoExt */(module.getModuleHome(), getFile());
        this.name = PathName.of(rpath);

        // id: based on relative path respect the project home directory
        rpath = FileUtils.relativePath/* NoExt */(getProject().getProjectHome(), getFile());
        this.id = String.valueOf(rpath.hashCode());
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public String getDigest() {
        return FileUtils.digest(file);
    }

    @Override
    public String getMimeType() {
        return MimeTypes.guessMimeType(PathUtils.getName(getPath()));
    }

    // ----------------------------------------------------------------------
    // Navigate
    // ----------------------------------------------------------------------

    @Override
    public Project getProject() {
        return module.getProject();
    }

    @Override
    public Module getModule() {
        return module;
    }

}
