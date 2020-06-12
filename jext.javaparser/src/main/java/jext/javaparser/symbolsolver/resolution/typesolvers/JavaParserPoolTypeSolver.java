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

        // synchronized (this/*JavaParserPool.getPool()*/) {
            Optional<TypeDeclaration<?>> astTypeDeclaration = pool.tryToSolveType(name);
            if (astTypeDeclaration.isPresent()) {
                return SymbolReference.solved(JavaParserFacade.get(this).getTypeDeclaration(astTypeDeclaration.get()));
            }
            else {
                return SymbolReference.unsolved(ResolvedReferenceTypeDeclaration.class);
            }
        // }
    }

}
