package jext.javassist;

import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.javassistmodel.JavassistFactory;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ClasspathsTypeSolver implements TypeSolver {

    private Classpaths classpaths;
    private Map<String, SymbolReference<ResolvedReferenceTypeDeclaration>>
            alreadySolved = new HashMap<>();

    public ClasspathsTypeSolver() {
        this.classpaths = new Classpaths();
    }

    public ClasspathsTypeSolver(Classpaths classpaths) {
        this.classpaths = classpaths;
    }

    @Override
    public TypeSolver getParent() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setParent(TypeSolver parent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name) {
        if (alreadySolved.containsKey(name))
            return alreadySolved.get(name);

        for(ClasspathElements cpe : classpaths.classpathElements())
            if (cpe.containsKey(name)) {
                SymbolReference<ResolvedReferenceTypeDeclaration> solved =
                SymbolReference.solved(JavassistFactory.toTypeDeclaration(cpe.get(name), getRoot()));
                alreadySolved.put(name, solved);
                return solved;
            }
        return SymbolReference.unsolved(ResolvedReferenceTypeDeclaration.class);
    }

    public void addLibrary(File libFile) {
        classpaths.getClasspathElements(libFile);
    }
}
