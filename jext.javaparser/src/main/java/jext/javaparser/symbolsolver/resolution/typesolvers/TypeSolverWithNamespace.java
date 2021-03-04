package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;

import java.util.Optional;

public interface TypeSolverWithNamespace extends TypeSolver {

    boolean isNamespace(String name);

    <T extends TypeSolver> Optional<T> findTypeSolver(Class<T> tsClass);

}
