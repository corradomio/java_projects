package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.javassistmodel.JavassistFactory;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import jext.javaparser.util.ClassPoolRegistry;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class JarFilesTypeSolver extends BaseTypeSolver {

    protected ClassPoolRegistry classPoolRegistry;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public JarFilesTypeSolver(String name) {
        this(name, new ClassPoolRegistry());
    }

    public JarFilesTypeSolver(String name, ClassPoolRegistry classPoolRegistry) {
        super(name);
        this.classPoolRegistry = classPoolRegistry;
    }

    public void addAll(List<File> libraryFiles) {
        this.classPoolRegistry.addAll(libraryFiles);
    }

    // ----------------------------------------------------------------------
    // Resolve
    // ----------------------------------------------------------------------

    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name) {

        try {
            if (this.classPoolRegistry.containsKey(name)) {
                return SymbolReference.solved(JavassistFactory.toTypeDeclaration(this.classPoolRegistry.get(name).toCtClass(),
                    this.getRoot()));
            }
            else {
                SymbolReference<ResolvedReferenceTypeDeclaration>
                    unsolved = SymbolReference.unsolved(ResolvedReferenceTypeDeclaration.class);
                // unresolved.put(name, unsolved);
                return unsolved;
            }
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }
}
