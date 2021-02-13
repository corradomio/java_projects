package jext.javaparser.symbolsolver.resolution.typesolvers;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;


public class JDKTypeSolver extends JarFilesTypeSolver {

    public JDKTypeSolver(File jdk) {
        super(jdk.getName());

        addLibraries(new File(jdk, "lib"), ".jar");
        addLibraries(new File(jdk, "jre/lib"), ".jar");
        addLibraries(new File(jdk, "jmod"), ".jmod");
    }

    private void addLibraries(File directory, String ext) {
        File[] libraryFiles = directory.listFiles((f, n) -> n.endsWith(ext));
        if (libraryFiles != null)
        for(File libraryFile : libraryFiles)
            add(libraryFile);
    }

}
