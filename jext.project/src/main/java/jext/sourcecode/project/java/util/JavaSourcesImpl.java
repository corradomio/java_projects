package jext.sourcecode.project.java.util;

import jext.sourcecode.project.Module;
import jext.sourcecode.project.Source;
import jext.sourcecode.project.util.SourcesImpl;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class JavaSourcesImpl extends SourcesImpl {

    public JavaSourcesImpl(Module module) {
        super(module);
    }

    public JavaSourcesImpl(File projectHome) {
        super(projectHome);
    }

    @Override
    public synchronized boolean add(Source source) {
        sourceRootDirectories = null;
        sourceRoots = null;
        return super.add(source);
    }

    @Override
    protected Set<String> getSourceRootsNoSync() {
        if (sourceRoots != null)
            return sourceRoots;

        sourceRoots = new HashSet<>();
        for(Source source : this) {

            source.getSourceRoot().ifPresent(sourceRoots::add);
        }
        return sourceRoots;
    }

}
