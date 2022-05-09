package jext.sourcecode.project.none;

import jext.sourcecode.project.Library;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Sources;
import jext.sourcecode.project.util.BaseModule;
import jext.sourcecode.project.util.SourcesImpl;

import java.io.File;
import java.util.Collections;
import java.util.Set;

public class NoneModule extends BaseModule {
    protected NoneModule(File moduleHome, Project project) {
        super(moduleHome, project);
    }

    @Override
    public Sources getSources() {
        return new SourcesImpl(this);
    }

    @Override
    public Library getRuntimeLibrary() {
        return null;
    }

    @Override
    public Set<Library> getDeclaredLibraries() {
        return Collections.emptySet();
    }

}
