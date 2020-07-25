package jext.buildtools.java;

import jext.buildtools.Name;
import jext.buildtools.Source;
import jext.buildtools.util.BaseModule;
import jext.buildtools.util.BaseResource;
import jext.buildtools.util.PathName;
import jext.util.FileUtils;

import java.io.File;

public class JavaSource extends BaseResource implements Source {

    private FastJavaParser parser;

    public JavaSource(File sourceFile, BaseModule module) {
        super(sourceFile, module);
        this.module = module;
        String rpath = FileUtils.relativePath(module.getDirectory(), sourceFile);
        this.name = new PathName(rpath);
        this.parser = new FastJavaParser(sourceFile).parse();
    }

    @Override
    public Name getRoot() {
        String rpath = FileUtils.relativePath(module.getDirectory(), parser.getRoot());
        return new PathName(rpath);
    }

}
