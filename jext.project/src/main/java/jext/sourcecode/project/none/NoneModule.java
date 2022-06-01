package jext.sourcecode.project.none;

import jext.sourcecode.project.Library;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.Sources;
import jext.sourcecode.project.Type;
import jext.sourcecode.project.util.BaseModule;
import jext.sourcecode.project.util.SourcesImpl;

import java.io.File;
import java.util.Collections;
import java.util.Set;

public class NoneModule extends BaseModule {

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    protected NoneModule(File moduleHome, Project project) {
        super(moduleHome, project);
    }

    // ----------------------------------------------------------------------
    // Sources
    // ----------------------------------------------------------------------

    @Override
    public Sources getSources() {
        return new SourcesImpl(this) {
            @Override
            protected Set<String> getSourceRootsNoSync() {
                return Collections.emptySet();
            }
        };
    }

    // ----------------------------------------------------------------------
    // Libraries
    // ----------------------------------------------------------------------

    @Override
    public Library getRuntimeLibrary() {
        return null;
    }

    @Override
    public Set<Library> getDeclaredLibraries() {
        return Collections.emptySet();
    }

    // ----------------------------------------------------------------------
    // Types
    // ----------------------------------------------------------------------

    @Override
    public Set<Type> getTypes() {
        return Collections.emptySet();
    }

    @Override
    public Set<RefType> getUsedTypes() {
        return Collections.emptySet();
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
