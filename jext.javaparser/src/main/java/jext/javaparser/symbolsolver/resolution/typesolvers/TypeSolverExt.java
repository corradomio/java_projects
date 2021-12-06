package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;

public interface TypeSolverExt extends TypeSolver {
    void setCu(CompilationUnit cu);

    boolean isNamespace(String name);

    SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name, int nTypeParams);
    SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(Type n);
}
