package jext.sourcecode.project.java.libraries;

import jext.cache.Cache;
import jext.cache.CacheManager;
import jext.compress.Archives;
import jext.name.EmptyName;
import jext.name.Name;
import jext.name.VersionName;
import jext.sourcecode.project.LibraryStatus;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.java.JavaConstants;
import jext.sourcecode.project.java.types.ReferencedType;
import jext.sourcecode.project.lfm.LibraryLicense;
import jext.sourcecode.project.lfm.LicenseFinder;
import jext.sourcecode.project.util.LibraryVersion;
import jext.util.FileUtils;
import jext.util.JarUtils;
import org.apache.commons.compress.archivers.ArchiveEntry;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JarLibrary extends JavaLibrary {

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public JarLibrary(File jarFile) {
        super(EmptyName.empty());

        String name = FileUtils.getNameWithoutExt(jarFile);
        this.version = LibraryVersion.versionOf(name);
        if (!this.version.isEmpty()) {
            int sep = name.lastIndexOf(this.version);
            name = name.substring(0, sep-1);
        }

        setNameWithId(VersionName.of(name, this.version));

        this.libraryFile = jarFile;
        this.libraryType = LibraryType.LOCAL;

    }

    public JarLibrary(File jarFile, Module module) {
        this(jarFile);

        this.project = module.getProject();
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public LibraryStatus getLibraryStatus() {
        return LibraryStatus.LATEST_VERSION_NOT_AVAILABLE;
    }

    @Override
    public String getLanguage() {
        return JavaConstants.JAVA;
    }

    @Override
    public boolean isValid() {
        return libraryFile.exists();
    }

    @Override
    public List<File> getFiles() {
        return Collections.singletonList(libraryFile);
    }

    // ----------------------------------------------------------------------
    // License
    // ----------------------------------------------------------------------

    @Override
    public LibraryLicense getLicense() {
        if (!libraryFile.exists())
            return super. getLicense();

        String licensePath = findLicensePath();
        if (licensePath == null)
            return super.getLicense();

        return readLicense(licensePath);
    }

    @Nullable
    private String findLicensePath() {

        try {
            List<ArchiveEntry> entries = Archives.listEntries(libraryFile, "META-INF");
            for(ArchiveEntry entry : entries) {
                String path = entry.getName();
                if (path.contains("META-INF/LICENSE"))
                    return path;
            }
        }
        catch (IOException e) {
            logger.error(e, e);
        }

        return null;
    }

    private LibraryLicense readLicense(String licensePath) {
        try {
            String licenseText = Archives.readText(libraryFile, licensePath);
            String url = String.format("archive://%s", libraryFile.getName());
            return LicenseFinder.findLicense(licenseText, url);
        }
        catch (IOException e) {
            logger.error(e, e);
        }
        return LibraryLicense.NO_LICENSE;
    }

    // ----------------------------------------------------------------------
    // Types
    // ----------------------------------------------------------------------

    @Override
    public Set<RefType> getTypes() {
        Cache<String, Set<RefType>> cache = CacheManager.getCache(String.format("dependency.%s.library.types", project.getId()));

        return cache.get(getId(), () -> {
            Set<RefType> types = new HashSet<>();

            JarUtils.listClassNames(libraryFile).forEach(typeName -> {
                types.add(new ReferencedType(typeName, JarLibrary.this));
            });

            return types;
        });
    }


    @Override
    public synchronized boolean contains(Name typeName) {
        if (definedTypes.contains(typeName))
            return true;
        if (undefinedTypes.contains(typeName))
            return false;

        if (JarUtils.containsClass(libraryFile, typeName.toString())) {
            definedTypes.add(typeName);
            return true;
        }
        else {
            undefinedTypes.add(typeName);
            return false;
        }
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
