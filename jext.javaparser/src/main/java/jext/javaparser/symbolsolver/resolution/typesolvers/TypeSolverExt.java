package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;

public interface TypeSolverExt extends TypeSolver {

    boolean isNamespace(String name);

    void canSolve();
}
