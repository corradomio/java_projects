package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import jext.javaparser.resolution.PrimitiveTypeDeclaration;
import jext.javaparser.resolution.ReferencedTypeDeclaration;
import jext.java.JavaUtils;

import static jext.java.JavaConstants.OBJECT;

/**
 * Used to assign a reasonable class name to the TypeSolver
 */
public class JavaRuntimeTypeSolver extends ClassPoolRegistryTypeSolver {

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

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

    // ----------------------------------------------------------------------
    // tryToSolveType
    // ----------------------------------------------------------------------
    // Specialized to resolve the primitive types

    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name, int nTypeParams) {
        if (JavaUtils.isPrimitive(name))
            return SymbolReference.solved(PrimitiveTypeDeclaration.of(name));
        if (OBJECT.equals(name) || JAVA_LANG_OBJECT.equals(name))
            return SymbolReference.solved(ReferencedTypeDeclaration.OBJECT);
        else
            return super.tryToSolveType(name, nTypeParams);
    }

    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name) {
        if (JavaUtils.isPrimitive(name))
            return SymbolReference.solved(PrimitiveTypeDeclaration.of(name));
        else
            return super.tryToSolveType(name);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
