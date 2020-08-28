package jext.buildtools.resource;

import jext.buildtools.Module;
import jext.buildtools.Resource;
import jext.buildtools.util.NamedObject;
import jext.buildtools.util.PathName;
import jext.util.FileUtils;
import jext.util.MimeTypes;

import java.io.File;

public class BaseResource extends NamedObject implements Resource {

    protected File file;
    protected Module module;

    protected BaseResource(File file, Module module) {
        super(null);
        this.file = file;
        this.module = module;
        String rpath = FileUtils.relativePath(module.getDirectory(), file);
        setName(new PathName(rpath));
    }

    public Module getModule() {
        return module;
    }

    public File getFile() {
        return file;
    }

    public String getDigest() {
        return FileUtils.digest(file);
    }

    public String getMimeType() {
        return MimeTypes.guessMimeType(file);
    }

}
