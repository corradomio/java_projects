package jext.javaparser.symbolsolver.resolution.typesolvers;

import java.io.File;
import java.util.Collection;

public class JarsTypeSolver extends ClassPoolRegistryTypeSolver {

    public JarsTypeSolver() {
        super();
    }

    public JarsTypeSolver(ClassPoolRegistry classPoolRegistry) {
        super(classPoolRegistry);
    }

    public JarsTypeSolver(String name) {
        super(name);
    }

    public JarsTypeSolver(String name, ClassPoolRegistry classPoolRegistry) {
        super(name, classPoolRegistry);
    }

    public JarsTypeSolver addJdk(File jdk) {
        classPoolRegistry.addJdk(jdk);
        return this;
    }

    public JarsTypeSolver addAll(Collection<File> libraryFiles) {
        classPoolRegistry.addAll(libraryFiles);
        return this;
    }
}
