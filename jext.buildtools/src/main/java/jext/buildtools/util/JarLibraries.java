package jext.buildtools.util;

import jext.buildtools.Libraries;
import jext.buildtools.Library;
import jext.buildtools.Module;
import jext.buildtools.Name;
import jext.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JarLibraries implements Libraries {

    private BaseModule module;
    private List<Library> libraries;

    public JarLibraries(Module module) {
        this.module = (BaseModule) module;
    }

    @Override
    public boolean isEmpty() {
        return getLibraries().isEmpty();
    }

    @Override
    public int size() {
        return getLibraries().size();
    }

    @Override
    public Set<Name> getRoots() {
        Set<Name> roots = new HashSet<>();
        getLibraries().forEach(library -> {
            Name root = library.getName().getParent();
            roots.add(root);
        });
        return roots;
    }

    @Override
    public List<Library> getLibraries() {
        if (libraries != null)
            return libraries;

        List<File> libraryFiles = new ArrayList<>();

        module.listDirectories().forEach(dir ->{
            FileUtils.listFiles(libraryFiles, dir, FileFilters.IS_JAR);
        });

        libraries = libraryFiles.stream()
                .map(sourceFile -> new JarLibrary(sourceFile, module))
                .collect(Collectors.toList());

        return libraries;
    }

    @Override
    public List<Library> getLibraries(Name root) {
         return getLibraries().stream()
            .filter(library -> library.getName().getFullname().startsWith(root.getFullname()))
            .collect(Collectors.toList());
    }
}
