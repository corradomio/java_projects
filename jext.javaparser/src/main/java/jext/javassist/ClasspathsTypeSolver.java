package jext.javassist;

import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.javassistmodel.JavassistFactory;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.BaseTypeSolver;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClasspathsTypeSolver extends BaseTypeSolver {

    private Classpaths classpaths;

    private Map<String, SymbolReference<ResolvedReferenceTypeDeclaration>>
        alreadySolved = new HashMap<>();

    public ClasspathsTypeSolver(String name, Classpaths classpaths) {
        super(name);
        this.classpaths = classpaths;
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

    public void addAll(List<File> libFiles) {
        libFiles.forEach(this::add);
    }

    public void add(File libFile) {
        classpaths.getClasspathElements(libFile);
    }
}
