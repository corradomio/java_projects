package jext.sourcecode.resources;

import jext.name.ObjectName;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Resource;
import jext.sourcecode.project.util.NamedObject;
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
        super(ObjectName.empty());

        this.module = module;
        this.file = file;
        this.path =  FileUtils.relativePath(getProject().getProjectHome(), file);

        String rpath = FileUtils.relativePathNoExt(module.getModuleHome(), getFile());
        setName(rpath);
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
    public long getDigest() {
        return FileUtils.digestAsLong(file);
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

    @Override
    public String getModuleId() {
        return module.getId();
    }

}
