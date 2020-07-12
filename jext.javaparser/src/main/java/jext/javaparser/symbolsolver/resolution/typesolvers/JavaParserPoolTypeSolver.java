package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import jext.javaparser.JavaParserPool;

import java.util.Optional;

public class JavaParserPoolTypeSolver extends BaseTypeSolver {

    private JavaParserPool pool;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public JavaParserPoolTypeSolver(JavaParserPool pool) {
        this("Pool", pool);
    }

    public JavaParserPoolTypeSolver(String name, JavaParserPool pool) {
        super(name);
        this.pool = pool;
    }

    // ----------------------------------------------------------------------
    // Resolve
    // ----------------------------------------------------------------------

    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name) {
        Optional<TypeDeclaration<?>> astTypeDeclaration = pool.tryToSolveType(name);
        return astTypeDeclaration
                .map(typeDeclaration -> SymbolReference.solved(JavaParserFacade.get(this).getTypeDeclaration(typeDeclaration)))
                .orElseGet(() -> SymbolReference.unsolved(ResolvedReferenceTypeDeclaration.class));
    }

}
