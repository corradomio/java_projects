package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.javassistmodel.JavassistFactory;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import jext.javaparser.util.ClassPoolRegistry;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;


public class JarFilesTypeSolver extends BaseTypeSolver {

    protected ClassPoolRegistry classPoolRegistry;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public JarFilesTypeSolver() {
        this("libraries");
    }

    public JarFilesTypeSolver(String name) {
        this(name, new ClassPoolRegistry());
    }

    public JarFilesTypeSolver(String name, ClassPoolRegistry classPoolRegistry) {
        super(name);
        this.classPoolRegistry = classPoolRegistry;
    }

    public JarFilesTypeSolver addAll(List<File> libraryFiles) {
        libraryFiles.forEach(this::add);
        return this;
    }

    public JarFilesTypeSolver addJdk(File jdk) {
        add(new File(jdk, "lib"));      // jdk 1 -> 8
        add(new File(jdk, "jre/lib"));  // jre 1 -> 8
        add(new File(jdk, "jmods"));    // jdk 9 -> ...
        return this;
    }

    public JarFilesTypeSolver add(File libraryFile) {
        this.classPoolRegistry.add(libraryFile);
        return this;
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
                return unsolved;
            }
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }
}
