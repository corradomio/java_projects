package jext.sourcecode.resources.libraries;

import jext.cache.Cache;
import jext.cache.CacheManager;
import jext.name.Name;
import jext.name.PathName;
import jext.nio.file.FilteredFileVisitor;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.util.ReferencedType;
import jext.sourcecode.resources.BaseLibrary;
import jext.util.FileUtils;
import jext.util.JarUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DirectoryLibrary extends BaseLibrary {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private List<File> libraryFiles;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public DirectoryLibrary(String libraryName, File libraryFile, Project project) {
        super(new PathName(libraryName), project);
        this.libraryFile = libraryFile;
        this.libraryType = LibraryType.RUNTIME; //LibraryType.LOCAL_COLLECTION;
    }

    // ----------------------------------------------------------------------
    // Specific Properties
    // ----------------------------------------------------------------------

    @Override
    public boolean isValid() {
        return libraryFile.exists() && libraryFile.isDirectory();
    }

    @Override
    public synchronized List<File> getFiles() {
        checkFilesNoSync();
        return libraryFiles;
    }

    @Override
    public synchronized boolean contains(Name typeName) {
        if (definedTypes.contains(typeName))
            return true;
        if (undefinedTypes.contains(typeName))
            return false;

        checkFilesNoSync();
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

        // Set<RefType> types = new HashSet<>();
        //
        // getFiles().forEach(libraryFile -> {
        //     JarUtils.listClassNames(libraryFile).forEach(typeName -> {
        //         types.add(new ReferencedType(typeName));
        //     });
        // });
        //
        // return types;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    private void checkFilesNoSync() {
        if (!libraryFile.isDirectory()) {
            logger.errorf("Invalid library directory '%s'", FileUtils.getAbsolutePath(libraryFile));
        }

        if (libraryFiles != null)
            return;

        libraryFiles = new ArrayList<>();

        // scan the directory to collect all '.jar' and '.jmod' files
        try {
            Files.walkFileTree(libraryFile.toPath(), new FilteredFileVisitor<Path>(){

                @Override
                public boolean filterFile(Path file) {
                    String name = file.getFileName().toString();
                    return name.endsWith(".jar") || name.endsWith(".jmod");
                }

                @Override
                public void onVisitFile(Path file, BasicFileAttributes attrs) {
                    libraryFiles.add(file.toFile());
                }
            });
        }
        catch (IOException e) {
            logger.error(e, e);
        }
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
