package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.javassistmodel.JavassistFactory;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;

import java.io.IOException;

public class ClassPoolRegistryTypeSolver extends BaseTypeSolver {

    // ----------------------------------------------------------------------
    // Protected fields
    // ----------------------------------------------------------------------

    protected ClassPoolRegistry classPoolRegistry;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ClassPoolRegistryTypeSolver() {
        this(DEFAULT, new ClassPoolRegistry());
    }

    public ClassPoolRegistryTypeSolver(ClassPoolRegistry classPoolRegistry) {
        this(DEFAULT, classPoolRegistry);
    }

    public ClassPoolRegistryTypeSolver(String name) {
        this(name, new ClassPoolRegistry());
    }

    public ClassPoolRegistryTypeSolver(String name, ClassPoolRegistry classPoolRegistry) {
        super(name);
        this.classPoolRegistry = classPoolRegistry;
    }

    // ----------------------------------------------------------------------
    // Extended operations
    // ----------------------------------------------------------------------

    @Override
    public boolean isNamespace(String name) {
        return classPoolRegistry.isNamespace(name);
    }

    // ----------------------------------------------------------------------
    // Resolve
    // ----------------------------------------------------------------------

    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name) {

        if(classPoolRegistry.isNamespace(name))
            return UNSOLVED;

        try {
            if (this.classPoolRegistry.isType(name)) {
                return SymbolReference.solved(JavassistFactory.toTypeDeclaration(this.classPoolRegistry.get(name).toCtClass(),
                    this.getRoot()));
            }
            else {
                // return SymbolReference.unsolved(ResolvedReferenceTypeDeclaration.class);;
                return UNSOLVED;
            }
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }
}
