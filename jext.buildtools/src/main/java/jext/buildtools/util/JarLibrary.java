package jext.buildtools.util;

import jext.buildtools.Library;
import jext.buildtools.Module;
import jext.buildtools.Name;
import jext.util.FileUtils;
import jext.util.StringUtils;

import java.io.File;

public class JarLibrary implements Library {

    private File jarFile;
    private Module module;
    private Name name;

    public JarLibrary(File jarFile, Module module) {
        this.jarFile = jarFile;
        this.module = module;
        String rpath = FileUtils.relativePath(module.getDirectory(), jarFile);
        this.name = new PathName(rpath);
    }

    @Override
    public String getId() {
        return StringUtils.digest(name.toString());
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public Module getModule() {
        return module;
    }
}
