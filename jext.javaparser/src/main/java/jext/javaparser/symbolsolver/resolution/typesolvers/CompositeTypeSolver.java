package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CompositeTypeSolver extends BaseTypeSolver {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    protected List<TypeSolver> elements = new ArrayList<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public CompositeTypeSolver(String name) {
        super(name);
    }

    protected CompositeTypeSolver(File file) {
        super(file);
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public CompositeTypeSolver add(TypeSolver typeSolver) {
        this.elements.add(typeSolver);
        typeSolver.setParent(this);
        return this;
    }

    // ----------------------------------------------------------------------
    // Resolve
    // ----------------------------------------------------------------------

    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name) {
        SymbolReference<ResolvedReferenceTypeDeclaration> resolved;

        for (TypeSolver typeSolver : elements) {
            resolved = typeSolver.tryToSolveType(name);
            if (resolved.isSolved())
                return resolved;
        }
        return SymbolReference.unsolved(ResolvedReferenceTypeDeclaration.class);
    }

}
