package jext.sourcecode.project.info.library;

import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.info.InfoModule;
import jext.sourcecode.project.info.InfoProject;
import jext.sourcecode.project.maven.MavenName;
import jext.util.MapUtils;

import java.util.List;
import java.util.Map;

public class InfoMavenLibrary extends InfoLibrary {

    public InfoMavenLibrary(InfoModule module, Map<String, Object> info) {
        super(module, LibraryType.MAVEN, info);
        String coords = MapUtils.get(info,"fullname");
        setName(new MavenName(coords));
    }

    public InfoMavenLibrary(InfoProject project, Map<String, Object> info) {
        super(project, LibraryType.MAVEN, info);
        String coords = MapUtils.get(info,"fullname");
        setName(new MavenName(coords));
    }

    public String getPath() {
        String pomFile = MapUtils.get(info, "file");
        List<String> jarFiles = MapUtils.get(info, "files");

        if (jarFiles.size() == 1)
            return jarFiles.get(0);
        else
            return pomFile;
    }

}
