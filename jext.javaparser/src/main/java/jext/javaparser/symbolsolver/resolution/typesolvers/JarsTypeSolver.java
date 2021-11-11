package jext.javaparser.symbolsolver.resolution.typesolvers;

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
}
