package jext.sourcecode.project.java.libraries;

import jext.cache.Cache;
import jext.cache.CacheManager;
import jext.sourcecode.project.LibraryStatus;
import jext.sourcecode.project.java.JavaConstants;
import jext.name.EmptyName;
import jext.name.VersionName;
import jext.name.Name;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.util.LibraryVersion;
import jext.sourcecode.project.java.types.ReferencedType;
import jext.sourcecode.project.util.BaseLibrary;
import jext.util.FileUtils;
import jext.util.JarUtils;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JarLibrary extends BaseLibrary {

    private String version;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public JarLibrary(File jarFile, Module module) {
        super(EmptyName.empty(), module.getProject());

        String name = FileUtils.getNameWithoutExt(jarFile);
        this.version = LibraryVersion.versionOf(name);
        if (!this.version.isEmpty()) {
            int sep = name.lastIndexOf(this.version);
            name = name.substring(0, sep-1);
        }

        setNameWithId(new VersionName(name, this.version));

        this.libraryFile = jarFile;
        this.project = module.getProject();
        this.libraryType = LibraryType.LOCAL;
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
    public String getVersion() {
        return version;
    }

    @Override
    public boolean isValid() {
        return libraryFile.exists();
    }

    @Override
    public List<File> getFiles() {
        return Collections.singletonList(libraryFile);
    }

    // @Override
    // public String getModuleId() {
    //     return module.getId();
    // }

    // @Override
    // public Module getModule() {
    //     return module;
    // }

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
