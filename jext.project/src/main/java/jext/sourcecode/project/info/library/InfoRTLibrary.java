package jext.sourcecode.project.info.library;

import jext.name.PathName;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.info.InfoModule;
import jext.sourcecode.project.info.InfoProject;
import jext.util.MapUtils;

import java.util.Map;

public class InfoRTLibrary extends InfoLibrary {

    public InfoRTLibrary(InfoModule module, Map<String, Object> info) {
        super(module, LibraryType.RUNTIME, info);
        setName(new PathName(MapUtils.get(info,"fullname")));
    }

    public InfoRTLibrary(InfoProject project, Map<String, Object> info) {
        super(project, LibraryType.RUNTIME, info);
        setName(new PathName(MapUtils.get(info,"fullname")));
    }
}
