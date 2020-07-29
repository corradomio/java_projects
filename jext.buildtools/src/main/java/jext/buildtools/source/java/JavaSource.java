package jext.buildtools.source.java;

import jext.buildtools.Module;
import jext.buildtools.Name;
import jext.buildtools.source.BaseSource;
import jext.buildtools.util.PathName;
import jext.util.FileUtils;

import java.io.File;
import java.util.Collections;
import java.util.Set;

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

    @Override
    public Set<Name> getTypes() {
        Name type = parser.getType();
        if (type == null)
            return Collections.emptySet();
        else
            return Collections.singleton(type);
    }

    @Override
    public Set<Name> getImportedTypes() {
        return parser.getNamedImports();
    }

    @Override
    public Set<Name> getImportedNamespaces() {
        return parser.getStarImports();
    }
}
