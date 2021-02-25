package jext.sourcecode.resources.libraries;

import jext.sourcecode.resources.BaseLibrary;
import jext.name.Name;
import jext.sourcecode.resources.type.ReferencedType;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.RefType;
import jext.name.ObjectName;
import jext.name.PathName;
import jext.cache.Cache;
import jext.cache.CacheManager;
import jext.util.FileUtils;
import jext.util.JarUtils;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class JarLibrary extends BaseLibrary {

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public JarLibrary(File jarFile, Module module) {
        super(ObjectName.empty(), module.getProject());

        String rpath = FileUtils.relativePathNoExt(module.getModuleHome(), jarFile);
        String name = FileUtils.getNameWithoutExt(jarFile);
        setName(new PathName(name));

        this.libraryFile = jarFile;
        this.module = module;
        this.libraryType = LibraryType.LOCAL; //LibraryType.LOCAL_JAR;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public List<File> getFiles() {
        return Collections.singletonList(libraryFile);
    }

    @Override
    public String getModuleId() {
        return module.getId();
    }

    @Override
    public Module getModule() {
        return module;
    }


    @Override
    public Set<RefType> getTypes() {
        Cache<String, Set<RefType>> cache = CacheManager.getCache(String.format("dependency.%s.library.types", project.getId()));

        return cache.get(getId(), () -> {
            Set<RefType> types = new HashSet<>();

            JarUtils.listClassNames(libraryFile).forEach(typeName -> {
                types.add(new ReferencedType(typeName));
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
