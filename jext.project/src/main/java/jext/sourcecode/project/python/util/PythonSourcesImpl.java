package jext.sourcecode.project.python.util;

import jext.sourcecode.project.Module;
import jext.sourcecode.project.util.SourcesImpl;
import jext.util.FileUtils;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class PythonSourcesImpl extends SourcesImpl {

    public PythonSourcesImpl(Module module) {
        super(module);
    }

    public PythonSourcesImpl(File projectHome) {
        super(projectHome);
    }

    @Override
    protected Set<String> getSourceRootsNoSync() {
        if (sourceRoots != null)
            return sourceRoots;

        sourceRoots = new HashSet<>();

        PythonSourceRoots psr = new PythonSourceRoots();
        psr.scan(moduleHome);
        psr.forEach(sr -> {
            sourceRoots.add(FileUtils.relativePath(moduleHome, sr));
        });

        return sourceRoots;
    }
}
