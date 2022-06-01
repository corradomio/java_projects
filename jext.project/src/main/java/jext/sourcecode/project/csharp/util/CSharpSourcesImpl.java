package jext.sourcecode.project.csharp.util;

import jext.sourcecode.project.Module;
import jext.sourcecode.project.util.SourcesImpl;
import jext.util.FileUtils;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class CSharpSourcesImpl extends SourcesImpl {

    public CSharpSourcesImpl(Module module) {
        super(module);
    }

    public CSharpSourcesImpl(File projectHome) {
        super(projectHome);
    }

    @Override
    protected Set<String> getSourceRootsNoSync() {
        if (sourceRoots != null)
            return sourceRoots;

        sourceRoots = new HashSet<>();

        CSharpSourceRoots psr = new CSharpSourceRoots();
        psr.scan(moduleHome);
        psr.forEach(sr -> {
            sourceRoots.add(FileUtils.relativePath(moduleHome, sr));
        });

        return sourceRoots;
    }
}
