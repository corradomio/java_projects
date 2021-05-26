package jext.sourcecode.project.info.library;

import jext.name.Name;
import jext.name.Named;
import jext.name.PathName;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.info.InfoModule;
import jext.sourcecode.project.info.InfoProject;
import jext.util.MapUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
