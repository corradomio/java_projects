package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;

public interface TypeSolverExt extends TypeSolver {

    boolean isNamespace(String name);

    // ResolvedType resolve(Type n);
    // ResolvedType resolve(NameExpr n);
    // ResolvedMethodDeclaration resolve(MethodReferenceExpr n);
    // ResolvedConstructorDeclaration resolve(ObjectCreationExpr n);
    // ResolvedMethodDeclaration resolve(MethodCallExpr n);
}
