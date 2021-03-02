package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.MethodReferenceExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.resolution.declarations.ResolvedConstructorDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;

public interface TypeSolverWithResolve extends TypeSolver {

    boolean isNamespace(String name);

    ResolvedType resolve(Type n);
    ResolvedType resolve(NameExpr n);
    ResolvedMethodDeclaration resolve(MethodReferenceExpr n);
    ResolvedConstructorDeclaration resolve(ObjectCreationExpr n);
    ResolvedMethodDeclaration resolve(MethodCallExpr n);
}
