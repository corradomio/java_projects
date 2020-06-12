package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import jext.javaparser.symbolsolver.javassistmodel.JavassistFactoryObject;
import jext.javassist.ClasspathElements;
import jext.javassist.JVMByteCodeAnalyzer;

import java.io.File;
import java.io.IOException;


public class JarTypeSolver extends BaseTypeSolver {

    private ClasspathElements classpathElements;

    public JarTypeSolver(File pathToJar) throws IOException {
        super(pathToJar);

        addPathToJar(pathToJar);
    }

    private void addPathToJar(File pathToJar) throws IOException {
        classpathElements = JVMByteCodeAnalyzer.getInstance().getClasspathElements(pathToJar);
    }

    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name) {
        // try {
            if (classpathElements.containsKey(name)) {
                ResolvedReferenceTypeDeclaration solved;

                solved =
                    JavassistFactoryObject.toTypeDeclaration(classpathElements.get(name), getRoot());

                return SymbolReference.solved(solved);
            } else {
                return SymbolReference.unsolved(ResolvedReferenceTypeDeclaration.class);
            }
        // } catch (IOException e) {
        //     throw new RuntimeException(e);
        // }
    }

    @Override
    public ResolvedReferenceTypeDeclaration solveType(String name) throws UnsolvedSymbolException {
        SymbolReference<ResolvedReferenceTypeDeclaration> ref = tryToSolveType(name);
        if (ref.isSolved()) {
            return ref.getCorrespondingDeclaration();
        } else {
            throw new UnsolvedSymbolException(name);
        }
    }

}
