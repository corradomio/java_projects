package jext.sourcecode.project.info.library;

import jext.name.VersionName;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.info.InfoModule;
import jext.sourcecode.project.info.InfoProject;
import jext.util.MapUtils;

import java.util.Map;

public class InfoLocalLibrary extends InfoLibrary {

    public InfoLocalLibrary(InfoModule module, Map<String, Object> info) {
        super(module, LibraryType.LOCAL, info);
        String fullname = MapUtils.get(info,"fullname");
        String version = MapUtils.get(info,"version");
        // this.name = new PathName(MapUtils.get(info,"fullname"));
        setName(new VersionName(fullname, version));
    }

    public InfoLocalLibrary(InfoProject project, Map<String, Object> info) {
        super(project, LibraryType.LOCAL, info);
        String fullname = MapUtils.get(info,"fullname");
        String version = MapUtils.get(info,"version");
        // this.name = new PathName(MapUtils.get(info,"fullname"));
        setName(new VersionName(fullname, version));
    }

}
