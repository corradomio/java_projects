package jext.sourcecode.project.info.library;

import jext.name.PathName;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.info.InfoModule;
import jext.sourcecode.project.info.InfoProject;
import jext.util.MapUtils;

import java.util.Map;

public class InfoInvalidLibrary extends InfoLibrary {

    public InfoInvalidLibrary(InfoModule module, Map<String, Object> info) {
        super(module, LibraryType.INVALID, info);
        this.name = new PathName(MapUtils.get(info,"fullname"));
    }

    public InfoInvalidLibrary(InfoProject project, Map<String, Object> info) {
        super(project, LibraryType.INVALID, info);
        this.name = new PathName(MapUtils.get(info,"fullname"));
    }
}
