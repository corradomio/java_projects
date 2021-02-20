package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.javassistmodel.JavassistFactory;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import jext.javaparser.util.ClassPoolRegistry;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class JarFilesTypeSolver extends BaseTypeSolver {

    // ----------------------------------------------------------------------
    // Protected fields
    // ----------------------------------------------------------------------

    protected ClassPoolRegistry classPoolRegistry;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public JarFilesTypeSolver() {
        this(DEFAULT, new ClassPoolRegistry());
    }

    public JarFilesTypeSolver(ClassPoolRegistry classPoolRegistry) {
        this(DEFAULT, classPoolRegistry);
    }

    public JarFilesTypeSolver(String name) {
        this(name, new ClassPoolRegistry());
    }

    public JarFilesTypeSolver(String name, ClassPoolRegistry classPoolRegistry) {
        super(name);
        this.classPoolRegistry = classPoolRegistry;
    }

    // ----------------------------------------------------------------------
    // Configuration
    // ----------------------------------------------------------------------

    public JarFilesTypeSolver withClassPoolRegistry(ClassPoolRegistry classPoolRegistry) {
        this.classPoolRegistry = classPoolRegistry;
        return this;
    }

    public JarFilesTypeSolver add(File libraryFile) {
        this.classPoolRegistry.add(libraryFile);
        return this;
    }

    public JarFilesTypeSolver addAll(List<File> libraryFiles) {
        this.classPoolRegistry.addAll(libraryFiles);
        return this;
    }

    public JarFilesTypeSolver addJdk(File jdk) {
        this.classPoolRegistry.addJdk(jdk);
        return this;
    }

    // ----------------------------------------------------------------------
    // Extended operations
    // ----------------------------------------------------------------------

    @Override
    public boolean isNamespace(String name) {
        return classPoolRegistry.isNamespace(name);
    }

    // ----------------------------------------------------------------------
    // Resolve
    // ----------------------------------------------------------------------

    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name) {

        if(classPoolRegistry.isNamespace(name))
            return UNSOLVED;

        try {
            if (this.classPoolRegistry.containsKey(name)) {
                return SymbolReference.solved(JavassistFactory.toTypeDeclaration(this.classPoolRegistry.get(name).toCtClass(),
                    this.getRoot()));
            }
            else {
                // return SymbolReference.unsolved(ResolvedReferenceTypeDeclaration.class);;
                return UNSOLVED;
            }
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }
}
