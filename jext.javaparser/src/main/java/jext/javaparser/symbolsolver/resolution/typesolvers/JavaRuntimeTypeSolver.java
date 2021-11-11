package jext.javaparser.symbolsolver.resolution.typesolvers;

/**
 * Used to assign a reasonable class name to the TypeSolver
 */
public class JavaRuntimeTypeSolver extends ClassPoolRegistryTypeSolver {

    public JavaRuntimeTypeSolver() {
        super();
    }

    public JavaRuntimeTypeSolver(ClassPoolRegistry classPoolRegistry) {
        super(classPoolRegistry);
    }

    public JavaRuntimeTypeSolver(String name) {
        super(name);
    }

    public JavaRuntimeTypeSolver(String name, ClassPoolRegistry classPoolRegistry) {
        super(name, classPoolRegistry);
    }
}
