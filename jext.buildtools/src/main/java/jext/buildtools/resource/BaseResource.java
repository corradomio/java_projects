package jext.buildtools.resource;

import jext.buildtools.Module;
import jext.buildtools.Name;
import jext.buildtools.Named;
import jext.buildtools.util.PathName;
import jext.util.FileUtils;
import jext.util.MimeTypes;

import java.io.File;

public class BaseResource implements Named {

    protected File file;
    protected Module module;
    protected Name name;

    protected BaseResource(File file, Module module) {
        this.file = file;
        this.module = module;
        String rpath = FileUtils.relativePath(module.getDirectory(), file);
        this.name = new PathName(rpath);
    }

    public Module getModule() {
        return module;
    }

    @Override
    public Name getName() {
        return name;
    }

    public String getId() {
        return String.valueOf(name.toString().hashCode());
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
