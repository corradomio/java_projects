package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.javassistmodel.JavassistFactory;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import jext.javaparser.util.ClassPoolRegistry;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class ClassPoolRegistryTypeSolver extends BaseTypeSolver {

    // ----------------------------------------------------------------------
    // Protected fields
    // ----------------------------------------------------------------------

    protected ClassPoolRegistry classPoolRegistry;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ClassPoolRegistryTypeSolver() {
        this(DEFAULT, new ClassPoolRegistry());
    }

    public ClassPoolRegistryTypeSolver(ClassPoolRegistry classPoolRegistry) {
        this(DEFAULT, classPoolRegistry);
    }

    public ClassPoolRegistryTypeSolver(String name) {
        this(name, new ClassPoolRegistry());
    }

    public ClassPoolRegistryTypeSolver(String name, ClassPoolRegistry classPoolRegistry) {
        super(name);
        this.classPoolRegistry = classPoolRegistry;
        // if (classPoolRegistry.isEmpty())
        //     throw new IllegalArgumentException("classPoolRegistry can not be empty");
    }

    // ----------------------------------------------------------------------
    // Configuration
    // ----------------------------------------------------------------------

    // public ClassPoolRegistryTypeSolver withClassPoolRegistry(ClassPoolRegistry classPoolRegistry) {
    //     this.classPoolRegistry = classPoolRegistry;
    //     return this;
    // }

    // public ClassPoolRegistryTypeSolver add(File libraryFile, String libraryName) {
    //     this.classPoolRegistry.add(libraryFile, libraryName);
    //     return this;
    // }

    // public ClassPoolRegistryTypeSolver addAll(List<File> libraryFiles, String libraryName) {
    //     this.classPoolRegistry.addAll(libraryFiles, libraryName);
    //     return this;
    // }

    // public ClassPoolRegistryTypeSolver addJdk(File jdk) {
    //     this.classPoolRegistry.addJdk(jdk);
    //     return this;
    // }

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
            if (this.classPoolRegistry.isType(name)) {
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
