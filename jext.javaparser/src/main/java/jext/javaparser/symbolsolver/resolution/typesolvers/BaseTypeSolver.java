package jext.javaparser.symbolsolver.resolution.typesolvers;

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
    protected TypeSolverExt parent;
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
        if (parent instanceof TypeSolverExt)
            this.parent = (TypeSolverExt) parent;
        else
            this.parent = new TypeSolverExtWrapper(parent);
    }

    // ----------------------------------------------------------------------
    // Resolve
    // ----------------------------------------------------------------------

    // @Override
    // public ResolvedType resolve(Type type) {
    //     return type.resolve();
    // }
    //
    // @Override
    // public ResolvedType resolve(NameExpr n) {
    //     ResolvedValueDeclaration rvd = n.resolve();
    //     return rvd.getType();
    // }
    //
    // @Override
    // public ResolvedMethodDeclaration resolve(MethodReferenceExpr n) {
    //     return n.resolve();
    // }
    //
    // @Override
    // public ResolvedConstructorDeclaration resolve(ObjectCreationExpr n) {
    //     return n.resolve();
    // }
    //
    // @Override
    // public ResolvedMethodDeclaration resolve(MethodCallExpr n) {
    //     return n.resolve();
    // }

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
    public boolean isNamespace(String name) {
        return false;
    }

}
