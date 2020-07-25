package jext.buildtools.util;

import jext.buildtools.Module;
import jext.buildtools.Name;
import jext.buildtools.Named;
import jext.util.FileUtils;

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

}
