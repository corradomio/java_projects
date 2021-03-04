package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import jext.javaparser.JavaParserPool;

import java.util.Optional;

public class JavaParserPoolTypeSolver extends BaseTypeSolver {

    // ----------------------------------------------------------------------
    // Private properties
    // ----------------------------------------------------------------------

    private JavaParserPool pool;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public JavaParserPoolTypeSolver() {
        this(DEFAULT);
    }

    public JavaParserPoolTypeSolver(String name) {
        super(name);
    }

    public JavaParserPoolTypeSolver(JavaParserPool pool) {
        super(pool.getName());
        this.pool = pool;
    }

    public JavaParserPoolTypeSolver(String name, JavaParserPool pool) {
        super(name);
        this.pool = pool;
    }

    // ----------------------------------------------------------------------
    // Extended operations
    // ----------------------------------------------------------------------

    public JavaParserPoolTypeSolver withPool(JavaParserPool pool) {
        this.pool = pool;
        return this;
    }

    // ----------------------------------------------------------------------
    // Extended operations
    // ----------------------------------------------------------------------

    @Override
    public boolean isNamespace(String name) {
        return pool.isNamespace(name);
    }

    // ----------------------------------------------------------------------
    // tryToSolveType
    // ----------------------------------------------------------------------

    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name) {
        try {
            Optional<TypeDeclaration<?>> astTypeDeclaration = pool.tryToSolveType(name);
            if (astTypeDeclaration.isPresent()) {
                return SymbolReference.solved(JavaParserFacade.get(this).getTypeDeclaration(astTypeDeclaration.get()));
            } else {
                return UNSOLVED;
            }
        }
        catch (com.google.common.util.concurrent.UncheckedExecutionException e) {
            logger.error(e.getMessage());
        }
        return UNSOLVED;
    }

    // ----------------------------------------------------------------------
    // Overrides
    // ----------------------------------------------------------------------

    @Override
    public String toString() {
        return String.format("JavaParserPoolTypeSolver[%s]", pool.getName());
    }

}
