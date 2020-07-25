package jext.buildtools.util;

import jext.buildtools.Resource;
import jext.buildtools.Module;
import jext.util.FileUtils;
import jext.util.MimeTypes;

import java.io.File;

public class ResourceFile extends NamedObject implements Resource {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private final Module module;
    private final File file;
    private final String path;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ResourceFile(File file, Module module) {
        super(PathName.empty());

        this.module = module;
        this.file = file;
        this.path = PathUtils.normalize(file.getAbsolutePath());

        String rpath = PathUtils.relativePath(module.getPath(), getPath());
        setName(new PathName(rpath));
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    // @Override
    public String getId() {
        return file.getAbsolutePath();
    }

    @Override
    public String getPath() {
        return path;
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
    public Module getModule() {
        return module;
    }

}
