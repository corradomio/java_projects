package jext.sourcecode.project.info.library;

import jext.sourcecode.project.LibraryStatus;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.info.InfoModule;
import jext.sourcecode.project.info.InfoProject;

import java.util.Map;

public class InfoRuntimeLibrary extends InfoLibrary {

    public InfoRuntimeLibrary(InfoModule module, Map<String, Object> info) {
        super(module, LibraryType.RUNTIME, info);
    }

    public InfoRuntimeLibrary(InfoProject project, Map<String, Object> info) {
        super(project, LibraryType.RUNTIME, info);
    }

    @Override
    public LibraryStatus getLibraryStatus() {
        return LibraryStatus.VALID;
    }

}
