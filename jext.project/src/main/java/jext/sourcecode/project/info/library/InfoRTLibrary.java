package jext.sourcecode.project.info.library;

import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.info.InfoModule;
import jext.sourcecode.project.info.InfoProject;

import java.util.Map;

public class InfoRTLibrary extends InfoLibrary {

    public InfoRTLibrary(InfoModule module, Map<String, Object> info) {
        super(module, LibraryType.RUNTIME, info);
        // setNameWithId(PathName.of(MapUtils.get(info,"fullname")));
    }

    public InfoRTLibrary(InfoProject project, Map<String, Object> info) {
        super(project, LibraryType.RUNTIME, info);
        // setNameWithId(PathName.of(MapUtils.get(info,"fullname")));
    }
}
