package jext.buildtools.library;

import jext.buildtools.Library;
import jext.buildtools.Module;
import jext.buildtools.Name;
import jext.buildtools.util.NamedObject;
import jext.buildtools.util.PathName;
import jext.util.FileUtils;
import jext.util.StringUtils;

import java.io.File;

public class JarLibrary extends NamedObject implements Library {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private File jarFile;
    private Module module;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public JarLibrary(File jarFile, Module module) {
        super(null);
        this.jarFile = jarFile;
        this.module = module;
        String rpath = FileUtils.relativePath(module.getDirectory(), jarFile);
        setName(new PathName(rpath));
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    public Module getModule() {
        return module;
    }
}
