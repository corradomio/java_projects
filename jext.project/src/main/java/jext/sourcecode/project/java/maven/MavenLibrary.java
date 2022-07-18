package jext.sourcecode.project.java.maven;

import jext.cache.Cache;
import jext.cache.CacheManager;
import jext.sourcecode.project.java.JavaConstants;
import jext.maven.MavenCoords;
import jext.maven.MavenDownloader;
import jext.maven.MavenPom;
import jext.maven.Version;
import jext.name.Name;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryStatus;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.java.libraries.JavaLibrary;
import jext.sourcecode.project.java.types.ReferencedType;
import jext.sourcecode.project.util.BaseLibrary;
import jext.util.FileUtils;
import jext.util.JarUtils;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MavenLibrary extends JavaLibrary {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    protected MavenCoords coords;
    protected MavenDownloader md;

    protected List<Library> dependencies;
    private List<File> jarFiles;

    private static final int MAX_DEPTH = 3;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public MavenLibrary(MavenCoords coords, MavenDownloader md, Project project) {
        super(new MavenName(coords), project);

        this.coords = coords;
        this.md = md;
        this.libraryFile = md.getPomFile(coords);
        this.libraryType = LibraryType.MAVEN;
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

    @Override
    public boolean isValid() {
        return getFile().exists()
            && getFiles().stream().allMatch(File::exists);
    }

    public MavenDownloader getMavenDownloader() {
        return this.md;
    }

    public Version getMavenVersion() {
        return coords.getVersion();
    }

    public MavenCoords getCoords() {
        return this.coords;
    }

    @Override
    public synchronized boolean contains(Name typeName) {
        if (definedTypes.contains(typeName))
            return true;
        if (undefinedTypes.contains(typeName))
            return false;

        checkFilesNoSync();
        for (File jarFile : jarFiles) {
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

    // @Override
    // public String getDigest() {
    //     File digestFile;
    //     MavenPom pom = md.getPom(coords);
    //     if (pom != null && pom.isPomPackaging())
    //         digestFile = md.getPomFile(coords);
    //     else
    //         digestFile = md.getArtifact(coords);
    //     return FileUtils.digest(digestFile);
    // }

    @Override
    public String getPath() {
        return coords.toString();
    }

    // ----------------------------------------------------------------------
    // Dependencies
    // ----------------------------------------------------------------------

    private List<MavenCoords> getDependencies(MavenCoords coords) {
        return md.getDependencies(coords, MAX_DEPTH);
    }

    // ----------------------------------------------------------------------
    // Types
    // ----------------------------------------------------------------------

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
        checkFilesNoSync();
        return jarFiles;
    }

    // ----------------------------------------------------------------------
    // Version
    // ----------------------------------------------------------------------

    @Override
    public String getVersion() {
        return coords.version;
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private void checkFilesNoSync() {
        if (jarFiles != null)
            return;

        jarFiles = md.getArtifacts(coords);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
