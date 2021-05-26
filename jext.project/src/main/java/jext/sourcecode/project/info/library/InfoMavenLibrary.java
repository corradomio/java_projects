package jext.sourcecode.project.info.library;

import jext.name.Name;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.info.InfoModule;
import jext.sourcecode.project.info.InfoProject;
import jext.sourcecode.project.maven.MavenName;
import jext.util.MapUtils;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class InfoMavenLibrary extends InfoLibrary {

    public InfoMavenLibrary(InfoModule module, Map<String, Object> info) {
        super(module, LibraryType.MAVEN, info);
        this.name = new MavenName(MapUtils.get(info,"fullname"));
    }

    public InfoMavenLibrary(InfoProject project, Map<String, Object> info) {
        super(project, LibraryType.MAVEN, info);
        this.name = new MavenName(MapUtils.get(info,"fullname"));
    }

}
