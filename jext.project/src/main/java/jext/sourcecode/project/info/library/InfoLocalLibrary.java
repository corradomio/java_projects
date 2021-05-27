package jext.sourcecode.project.info.library;

import jext.name.PathName;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.info.InfoModule;
import jext.sourcecode.project.info.InfoProject;
import jext.util.MapUtils;

import java.util.Map;

public class InfoLocalLibrary extends InfoLibrary {

    public InfoLocalLibrary(InfoModule module, Map<String, Object> info) {
        super(module, LibraryType.LOCAL, info);
        this.name = new PathName(MapUtils.get(info,"fullname"));
    }

    public InfoLocalLibrary(InfoProject project, Map<String, Object> info) {
        super(project, LibraryType.LOCAL, info);
        this.name = new PathName(MapUtils.get(info,"fullname"));
    }

}
