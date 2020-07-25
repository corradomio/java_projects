package jext.buildtools.java;

import jext.buildtools.Module;
import jext.buildtools.Name;
import jext.buildtools.util.BaseSource;
import jext.buildtools.util.PathName;
import jext.util.FileUtils;

import java.io.File;

public class JavaSource extends BaseSource {

    private FastJavaParser parser;

    public JavaSource(File sourceFile, Module module) {
        super(sourceFile, module);
        this.parser = new FastJavaParser(sourceFile).parse();
    }

    @Override
    public Name getRoot() {
        String rpath = FileUtils.relativePath(module.getDirectory(), parser.getRoot());
        return new PathName(rpath);
    }

}
