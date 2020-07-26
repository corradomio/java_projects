package jext.buildtools.util;

import jext.buildtools.Library;
import jext.buildtools.Module;
import jext.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JarLibraries extends BaseLibraries {

    public JarLibraries(Module module) {
        super(module);
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

}
