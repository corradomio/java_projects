package jext.sourcecode.project.maven;

import jext.name.Name;
import jext.sourcecode.resources.type.ReferencedType;
import jext.sourcecode.resources.BaseLibrary;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.cache.Cache;
import jext.cache.CacheManager;
import jext.maven.MavenCoords;
import jext.maven.MavenDownloader;
import jext.maven.MavenPom;
import jext.maven.Version;
import jext.util.FileUtils;
import jext.util.JarUtils;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class MavenLibrary extends BaseLibrary {

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
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public boolean isValid() {
        return getFile().exists()
            && getFiles().stream().allMatch(File::exists);
    }

    public MavenDownloader getMavenDownloader() {
        return this.md;
    }

    public String getMavenName() {
        return coords.getName();
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

    @Override
    public LibraryType getLibraryType() {
        return LibraryType.MAVEN;
    }

    @Override
    public String getDigest() {
        File digestFile;
        MavenPom pom = md.getPom(coords);
        if (pom != null && pom.isPomPackaging())
            digestFile = md.getPomFile(coords);
        else
            digestFile = md.getArtifact(coords);
        return FileUtils.digest(digestFile);
    }

    @Override
    public String getPath() {
        return coords.toString();
    }

    // ----------------------------------------------------------------------
    // Dependencies
    // ----------------------------------------------------------------------

    // @Override
    // public List<Library> getDependencies() {
    //     if (dependencies != null)
    //         return dependencies;
    //
    //     dependencies = getDependencies(coords).stream()
    //         .map(dcoords -> new MavenLibrary(dcoords, md, project))
    //         .collect(Collectors.toList());
    //
    //     return dependencies;
    // }

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
                    types.add(new ReferencedType(typeName));
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

    // @Override
    // public String getLatestVersion() {
    //     MavenDownloader md = this.getProject().getLibraryDownloader();
    //     MavenCoords coords = new MavenCoords(this.getName().getFullName());
    //     MavenCoords latest = md.getLatest(coords);
    //     return latest.getVersion().toString();
    // }

    @Override
    public String getLatest() {
        MavenDownloader md = this.getProject().getLibraryDownloader();
        MavenCoords coords = new MavenCoords(this.getName().getFullName());
        MavenCoords latest = md.getLatest(coords);
        return latest.getName();
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
