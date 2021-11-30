package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import jext.logging.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class BaseTypeSolver implements TypeSolverExt {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    protected static final String DEFAULT = "default";
    protected static final SymbolReference<ResolvedReferenceTypeDeclaration>
        UNSOLVED = SymbolReference.unsolved(ResolvedReferenceTypeDeclaration.class);

    protected Logger logger;
    protected TypeSolver parent;
    protected String name;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    protected BaseTypeSolver(String name) {
        this.name = name;
        this.logger = Logger.getLogger(getClass(), name);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public String getName() {
        return name;
    }

    // ----------------------------------------------------------------------
    // Parent
    // ----------------------------------------------------------------------

    @Override
    public TypeSolver getParent() {
        return parent;
    }

    @Override
    public void setParent(TypeSolver parent) {
        Objects.requireNonNull(parent);
        if (this.parent != null) {
            throw new IllegalStateException("This TypeSolver already has a parent.");
        }
        if (parent == this) {
            throw new IllegalStateException("The parent of this TypeSolver cannot be itself.");
        }

        this.parent = parent;
    }

    // ----------------------------------------------------------------------
    // Extended operations
    // ----------------------------------------------------------------------

    public List<TypeSolver> getElements() {
        return Collections.emptyList();
    }

    // ----------------------------------------------------------------------
    // Extended operations
    // ----------------------------------------------------------------------

    @Override
    public void setCu(CompilationUnit cu) {

    }

    @Override
    public boolean isNamespace(String name) {
        return false;
    }

    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(Type n) {
        return UNSOLVED;
    }

    // @Override
    // public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name, int nTypeParams) {
    //     return UNSOLVED;
    // }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
