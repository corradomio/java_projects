package jext.sourcecode.project.info.util;

import jext.sourcecode.project.Module;
import jext.sourcecode.project.info.InfoModule;
import jext.sourcecode.project.util.SourcesImpl;

import java.io.File;
import java.util.Set;

public class InfoSourcesImpl extends SourcesImpl {

    private InfoModule module;

    public InfoSourcesImpl(Module module) {
        super(module);
        this.module = (InfoModule) module;
    }

    public InfoSourcesImpl(File projectHome) {
        super(projectHome);
    }

    @Override
    protected Set<String> getSourceRootsNoSync() {
        return module.getSourceRoots();
    }
}
