package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;

import java.util.ArrayList;
import java.util.List;


public class CompositeTypeSolver extends BaseTypeSolver {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    protected List<TypeSolver> elements = new ArrayList<>();
    private List<TypeSolverExt> elementsExt = new ArrayList<>();


    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public CompositeTypeSolver() {
        this(DEFAULT);
    }


    public CompositeTypeSolver(String name) {
        super(name);
    }

    // ----------------------------------------------------------------------
    // Extended operations
    // ----------------------------------------------------------------------

    public CompositeTypeSolver add(TypeSolver ts) {
        this.elements.add(ts);
        if (ts instanceof TypeSolverExt)
            this.elementsExt.add((TypeSolverExt) ts);
        ts.setParent(this);
        return this;
    }

    @Override
    public List<TypeSolver> getElements() {
        return this.elements;
    }

    // ----------------------------------------------------------------------
    // Extended operations
    // ----------------------------------------------------------------------

    @Override
    public boolean isNamespace(String name) {
        for (TypeSolverExt tsx : elementsExt) {
            if (tsx.isNamespace(name))
                return true;
        }
        return false;
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
        return UNSOLVED;
    }

}
