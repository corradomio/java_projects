package jext.sourcecode.project.java.maven;

import jext.cache.Cache;
import jext.cache.CacheManager;
import jext.maven.MavenCoords;
import jext.maven.MavenDownloader;
import jext.maven.Version;
import jext.name.Name;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryStatus;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.java.libraries.JavaLibrary;
import jext.sourcecode.project.java.types.ReferencedType;
import jext.util.JarUtils;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MavenLibrary extends JavaLibrary {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    protected MavenCoords coords;
    protected MavenDownloader downloader;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public MavenLibrary(MavenCoords coords, MavenDownloader downloader) {
        super(MavenName.of(coords));

        this.coords = coords;
        this.downloader = downloader;
        this.libraryFile = downloader.getPomFile(coords);
        this.libraryType = LibraryType.REMOTE;
        this.version = coords.version;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public LibraryStatus getLibraryStatus() {

        if (!isValid())
            return LibraryStatus.NOTEXISTENT;

        Version currentVersion = Version.of(getVersion());
        Version latestVersion  = Version.of(downloader.getLatestVersion(this.coords));
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

    @Override
    public boolean isValid() {
        return getFile().exists()
            && getFiles().stream().allMatch(File::exists);
    }

    @Override
    public String getPath() {
        return coords.toString();
    }

    // ----------------------------------------------------------------------
    // Dependencies
    // ----------------------------------------------------------------------

    @Override
    public Set<Library> getDependencies() {
        Set<MavenCoords> deplibs = downloader.getDependencies(coords, 0);
        downloader.checkArtifacts(deplibs, true);

        return deplibs.stream()
                .map(dcoords -> new MavenLibrary(dcoords, downloader).project(project))
                .collect(Collectors.toSet());
    }

    // ----------------------------------------------------------------------
    // Types
    // ----------------------------------------------------------------------

    @Override
    public synchronized boolean contains(Name typeName) {
        if (definedTypes.contains(typeName))
            return true;
        if (undefinedTypes.contains(typeName))
            return false;

        populate();

        for (File jarFile : libraryFiles) {
            if (JarUtils.containsClass(jarFile, typeName.toString())) {
                definedTypes.add(typeName);
                return true;
            }
        }
        {
            undefinedTypes.add(typeName);
            return false;
        }
    }

    @Override
    public Set<RefType> getTypes() {
        Cache<String, Set<RefType>> cache = CacheManager.getCache(String.format("dependency.%s.library.types", project.getId()));

        return cache.get(getId(), () -> {
            Set<RefType> types = new HashSet<>();

            getFiles().forEach(libraryFile -> {
                JarUtils.listClassNames(libraryFile).forEach(typeName -> {
                    types.add(new ReferencedType(typeName, MavenLibrary.this));
                });
            });

            return types;
        });

    }

    // ----------------------------------------------------------------------
    // Specific Properties
    // ----------------------------------------------------------------------

    @Override
    public synchronized List<File> getFiles() {
        if (libraryFiles == null) {
            download();
            populate();
        }
        return libraryFiles;
    }

    private void download() {
        if (!libraryFile.exists())
            downloader.checkArtifact(coords);
    }

    private void populate() {
        libraryFiles = downloader.getArtifacts(coords);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
