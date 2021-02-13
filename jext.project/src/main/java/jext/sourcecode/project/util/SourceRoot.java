package jext.sourcecode.project;

import java.io.File;

public class SourceRoot {

    private File sourceRoot;
    private Module module;

    public SourceRoot(Module module, File sourceRoot) {
        this.module = module;
        this.sourceRoot = sourceRoot;
    }
}
