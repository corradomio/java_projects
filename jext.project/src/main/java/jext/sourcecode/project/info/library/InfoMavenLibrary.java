package jext.sourcecode.project.info.library;

import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.info.InfoModule;
import jext.sourcecode.project.info.InfoProject;
import jext.sourcecode.project.maven.MavenName;
import jext.util.MapUtils;

import java.util.Map;

public class InfoMavenLibrary extends InfoLibrary {

    public InfoMavenLibrary(InfoModule module, Map<String, Object> info) {
        super(module, LibraryType.MAVEN, info);
        this.name = new MavenName((String)MapUtils.get(info,"fullname"));
    }

    public InfoMavenLibrary(InfoProject project, Map<String, Object> info) {
        super(project, LibraryType.MAVEN, info);
        this.name = new MavenName((String)MapUtils.get(info,"fullname"));
    }

}
