package jext.javaparser.symbolsolver.resolution.typesolvers;

import jext.javaparser.util.ClassPoolRegistry;

import java.io.File;

public class JDKTypeSolver extends JarFilesTypeSolver {

    public JDKTypeSolver(File jdkDirectory) {
        super(jdkDirectory.getName());
        classPoolRegistry = new ClassPoolRegistry();
        classPoolRegistry.add(jdkDirectory);
    }
}
