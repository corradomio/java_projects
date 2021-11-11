package jext.javaparser.symbolsolver.resolution.typesolvers;

/**
 * Used to assign a reasonable class name to the TypeSolver
 */
public class LibrariesTypeSolver extends ClassPoolRegistryTypeSolver {

    public LibrariesTypeSolver() {
        super();
    }

    public LibrariesTypeSolver(ClassPoolRegistry classPoolRegistry) {
        super(classPoolRegistry);
    }

    public LibrariesTypeSolver(String name) {
        super(name);
    }

    public LibrariesTypeSolver(String name, ClassPoolRegistry classPoolRegistry) {
        super(name, classPoolRegistry);
    }
}
