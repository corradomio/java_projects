package jext.buildtools.project.ant.util;

import jext.buildtools.project.ant.AntProject;
import jext.buildtools.source.java.FastJavaParser;
import jext.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

public class SourcesFinder {

    private AntProject project;
    private Set<File> roots = new HashSet<>();

    public SourcesFinder(AntProject project) {
        this.project = project;
    }

    public void findSources() {
        try {
            Files.walkFileTree(project.getDirectory().toPath(), new SimpleFileVisitor<Path>(){
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    File javaFile = path.toFile();
                    if (javaFile.getName().endsWith(".java"))
                        roots.add(getRoot(javaFile));
                    return FileVisitResult.CONTINUE;
                }
            });
        }
        catch (IOException e) { }
    }

    private File getRoot(File javaFile) {
        FastJavaParser jp = new FastJavaParser(javaFile).parse();
        return jp.getRoot().getParentFile();
    }

    public Set<File> getRoots() {
        return this.roots;
    }
}
