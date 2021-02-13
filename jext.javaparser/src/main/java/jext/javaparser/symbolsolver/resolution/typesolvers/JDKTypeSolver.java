package jext.javaparser.symbolsolver.resolution.typesolvers;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;


public class JDKTypeSolver extends JarFilesTypeSolver {

    public JDKTypeSolver(File jdk) {
        super(jdk.getName());

        try {
            Files.walkFileTree(jdk.toPath(), new SimpleFileVisitor<Path>(){

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.endsWith(".jar") || file.endsWith(".jmod"))
                        JDKTypeSolver.this.add(file.toFile());
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) { }
    }

}
