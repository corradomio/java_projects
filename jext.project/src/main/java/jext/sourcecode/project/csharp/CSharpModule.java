package jext.sourcecode.project.csharp;

import jext.sourcecode.project.Library;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Sources;
import jext.sourcecode.project.util.BaseModule;

import java.io.File;
import java.util.Set;

public class CSharpModule extends BaseModule {

    public CSharpModule(File moduleHome, Project project) {
        super(moduleHome, project);
    }

    @Override
    public Sources getSources() {
        return null;
    }

    @Override
    public Library getRuntimeLibrary() {
        return null;
    }

    @Override
    public Set<Library> getDeclaredLibraries() {
        return null;
    }
}
