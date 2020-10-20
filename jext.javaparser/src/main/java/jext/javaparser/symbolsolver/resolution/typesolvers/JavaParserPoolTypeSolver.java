package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import jext.javaparser.JavaParserPool;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class JavaParserPoolTypeSolver extends BaseTypeSolver {

    private JavaParserPool pool;
    private Set<String> unresolved = new HashSet<>();

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

        // speedup
        if (unresolved.contains(name))
            return SymbolReference.unsolved(ResolvedReferenceTypeDeclaration.class);

        try {
            Optional<TypeDeclaration<?>> astTypeDeclaration = pool.tryToSolveType(name);
            if (astTypeDeclaration.isPresent()) {
                return SymbolReference.solved(JavaParserFacade.get(this).getTypeDeclaration(astTypeDeclaration.get()));
            } else {
                unresolved.add(name);   // speedup
                return SymbolReference.unsolved(ResolvedReferenceTypeDeclaration.class);
            }
        }
        catch (com.google.common.util.concurrent.UncheckedExecutionException e) {
            logger.error(e.getMessage());
        }
        catch (Throwable t) {
            logger.error(t, t);
        }
        // }

        return SymbolReference.unsolved(ResolvedReferenceTypeDeclaration.class);
    }

}
