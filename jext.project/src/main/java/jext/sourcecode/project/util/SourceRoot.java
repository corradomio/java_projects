package jext.sourcecode.project.util;

import jext.sourcecode.project.Module;
import jext.sourcecode.project.Source;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SourceRoot {

    private File sourceRoot;
    private Module module;
    private List<Source> sources = new ArrayList<>();

    public SourceRoot(Module module, File sourceRoot) {
        this.module = module;
        this.sourceRoot = sourceRoot;
    }

    public Module getModule()  {
        return module;
    }

    public File getSourceRoot() {
        return this.sourceRoot;
    }

    public List<Source> getSources() {
        return this.sources;
    }

    public void add(Source source) {
        this.sources.add(source);
    }

    public boolean isEmpty() {
        return sources.isEmpty();
    }

    public int size() {
        return sources.size();
    }
}
