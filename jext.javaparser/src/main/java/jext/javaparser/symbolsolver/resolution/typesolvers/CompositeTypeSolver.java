package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.type.Type;
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
    protected List<TypeSolverExt> elementsExt = new ArrayList<>();

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

    @Override
    public void setCu(CompilationUnit cu) {
        for (TypeSolverExt tsx : elementsExt)
            tsx.setCu(cu);
    }

    // ----------------------------------------------------------------------
    // Resolve
    // ----------------------------------------------------------------------

    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name) {
        SymbolReference<ResolvedReferenceTypeDeclaration> solved;

        for (TypeSolver typeSolver : elements) {
            solved = typeSolver.tryToSolveType(name);
            if (solved.isSolved())
                return solved;
        }
        return UNSOLVED;
    }

    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(Type n) {
        for (TypeSolverExt tsx : elementsExt) {
            SymbolReference<ResolvedReferenceTypeDeclaration>
                solved = tsx.tryToSolveType(n);
            if (solved.isSolved())
                return solved;
        }
        return UNSOLVED;
    }

}
