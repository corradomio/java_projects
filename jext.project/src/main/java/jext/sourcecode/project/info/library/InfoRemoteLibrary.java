package jext.sourcecode.project.info.library;

import jext.maven.MavenCoords;
import jext.maven.Version;
import jext.sourcecode.project.LibraryDownloader;
import jext.sourcecode.project.LibraryStatus;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.info.InfoProject;
import jext.sourcecode.project.java.maven.MavenName;
import jext.util.MapUtils;

import java.util.List;
import java.util.Map;

public class InfoRemoteLibrary extends InfoLibrary {

    protected MavenCoords coords;
    protected LibraryDownloader md;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public InfoRemoteLibrary(InfoProject project, Map<String, Object> info) {
        super(project, LibraryType.REMOTE, info);
        String coords = MapUtils.get(info,"fullname");
        this.coords = MavenCoords.of(coords);
        setNameWithId(MavenName.of(coords));
        this.md = project.getLibraryDownloader();
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public LibraryStatus getLibraryStatus() {

        if (!isValid())
            return LibraryStatus.NOTEXISTENT;

        Version currentVersion = Version.of(getVersion());
        Version latestVersion = Version.of(md.getLatestVersion(this.coords));
        // the latest version does't exists
        if (latestVersion.isEmpty() && !currentVersion.isEmpty())
            return LibraryStatus.LATEST_VERSION_NOT_AVAILABLE;
        // current version and latest version are empty
        if (latestVersion.isEmpty() && currentVersion.isEmpty())
            return LibraryStatus.LATEST_VERSION_NOT_AVAILABLE;

        // currentVersion & latestVersion are not empty
        int cmp = currentVersion.compareTo(latestVersion);
        if (cmp > 0)
            return LibraryStatus.INCONSISTENT;
        if (cmp == 0)
            return LibraryStatus.VALID;

        int dif = currentVersion.differOn(latestVersion);
        if (dif > 0)
            // they deffer on minor version number or lower
            return LibraryStatus.UPGRADEABLE;
        else
            // they deffer on major version number
            return LibraryStatus.OBSOLETE;
    }

    public String getPath() {
        String pomFile = MapUtils.get(info, "file");
        List<String> jarFiles = MapUtils.get(info, "files");

        if (jarFiles.size() == 1)
            return jarFiles.get(0);
        else
            return pomFile;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
