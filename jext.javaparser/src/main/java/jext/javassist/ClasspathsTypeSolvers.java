package jext.javassist;

import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import jext.util.FileUtils;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClasspathsTypeSolvers {

    private Map<String, Classpaths> classpathsMap = new HashMap<>();

    public TypeSolver getTypeSolver(File fileOrDirectory) {
        return getTypeSolver(FileUtils.getNameWithoutExt(fileOrDirectory), fileOrDirectory, false);
    }

    public TypeSolver getTypeSolver(File fileOrDirectory, boolean recursive) {
        return getTypeSolver(FileUtils.getNameWithoutExt(fileOrDirectory), fileOrDirectory, recursive);
    }

    public TypeSolver getTypeSolver(String name, File fileOrDirectory, boolean recursive) {
        if (fileOrDirectory.isFile())
            return getTypeSolver(name, Collections.singletonList(fileOrDirectory));
        if (!recursive) {
            List<File> libraryFiles = new ArrayList<>();
            libraryFiles.addAll(FileUtils.listFiles(fileOrDirectory, ".jar"));
            libraryFiles.addAll(FileUtils.listFiles(fileOrDirectory, ".jmod"));
            return getTypeSolver(name, libraryFiles);
        }
        else {
            List<File> libraryFiles = new ArrayList<>();
            FileUtils.listFiles(libraryFiles, fileOrDirectory, pathname -> pathname.getName().endsWith(".jar"));
            FileUtils.listFiles(libraryFiles, fileOrDirectory, pathname -> pathname.getName().endsWith(".jmod"));
            return getTypeSolver(name, libraryFiles);
        }
    }

    public TypeSolver getTypeSolver(String name, List<File> libraryFiles) {
        if (!classpathsMap.containsKey(name))
            composeClasspaths(name, libraryFiles);
        Classpaths classpaths = classpathsMap.get(name);
        return new ClasspathsTypeSolver(name, classpaths);
    }

    private void composeClasspaths(String name, List<File> libraryFiles) {
        Classpaths classpaths = new Classpaths();
        classpaths.addAll(libraryFiles);

        classpathsMap.put(name, classpaths);
    }
}
