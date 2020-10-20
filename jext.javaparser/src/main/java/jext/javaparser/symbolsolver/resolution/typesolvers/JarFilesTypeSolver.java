package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.javassistmodel.JavassistFactory;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import jext.javaparser.util.ClassPoolRegistry;
import jext.logging.Logger;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class JarFilesTypeSolver extends BaseTypeSolver {

    protected ClassPoolRegistry classPoolRegistry;
    private Set<String> unresolved = new HashSet<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public JarFilesTypeSolver(String name) {
        super(name);
    }

    public JarFilesTypeSolver(File libraryDirectory) {
        super(libraryDirectory.getName());
        classPoolRegistry = new ClassPoolRegistry();
        classPoolRegistry.add(libraryDirectory);
    }

    public JarFilesTypeSolver(String name, ClassPoolRegistry classPoolRegistry) {
        super(name);
        this.classPoolRegistry = classPoolRegistry;
    }

    // ----------------------------------------------------------------------
    // Resolve
    // ----------------------------------------------------------------------

    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name) {

        // speedup
        if (unresolved.contains(name))
            return SymbolReference.unsolved(ResolvedReferenceTypeDeclaration.class);

        try {
            if (this.classPoolRegistry.containsKey(name)) {
                return SymbolReference.solved(JavassistFactory.toTypeDeclaration(this.classPoolRegistry.get(name).toCtClass(),
                    this.getRoot()));
            }
            else {
                unresolved.add(name);
                return SymbolReference.unsolved(ResolvedReferenceTypeDeclaration.class);
            }
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }
}
