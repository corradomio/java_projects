package jext.sourcecode.resources.libraries;

import jext.lang.JavaUtils;
import jext.name.Name;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RuntimeLibrary;

import java.io.File;

public class JDKLibrary extends DirectoryLibrary implements RuntimeLibrary {

    public JDKLibrary(String libraryName, File libraryFile, Project project) {
        super(libraryName, libraryFile, project);
    }

    @Override
    public boolean contains(Name typeName) {
        if (JavaUtils.PRIMITIVE_TYPES.contains(typeName.getFullName()))
            return true;
        else
            return super.contains(typeName);
    }

    // @Override
    // public Set<RefType> getTypes() {
    //     String cacheName = String.format("dependency.%s.library.types", project.getId());
    //     Cache<String, Set<RefType>> cache = CacheManager.getCache(cacheName);
    //
    //     return cache.get(getId(), () -> {
    //         Set<RefType> types = new HashSet<>();
    //
    //         getFiles().forEach(libraryFile -> {
    //             JarUtils.listClassNames(libraryFile).forEach(typeName -> {
    //                 types.add(new ReferencedType(typeName));
    //             });
    //         });
    //
    //         types.addAll(JavaUtils.PRIMITIVE_TYPES);
    //
    //         return types;
    //     });
    // }
}
