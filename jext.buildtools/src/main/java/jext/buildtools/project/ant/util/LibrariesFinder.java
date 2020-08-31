package jext.buildtools.project.ant.util;

import jext.buildtools.project.ant.AntProject;
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
import java.util.stream.Collectors;

public class LibrariesFinder {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private AntProject project;
    private Set<File> parents = new HashSet<>();

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public LibrariesFinder(AntProject project) {
        this.project = project;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public void findLibraries() {
        try {
            Files.walkFileTree(project.getDirectory().toPath(), new SimpleFileVisitor<Path>(){
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    File libraryFile = path.toFile();
                    if (libraryFile.getName().endsWith(".jar"))
                        parents.add(getParent(libraryFile));
                    return FileVisitResult.CONTINUE;
                }
            });
        }
        catch (IOException e) { }
    }

    private File getParent(File libraryFile) {
        return libraryFile.getParentFile();
    }

    public Set<File> getRoots() {
        return FileUtils.simplify(parents).stream()
                .map(File::getParentFile)
                .collect(Collectors.toSet());
    }
}
