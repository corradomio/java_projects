package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import jext.javaparser.resolution.ReferencedTypeDeclaration;
import jext.lang.JavaUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ContextTypeSolver2 extends CompositeTypeSolver {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private CompilationUnit cu;
    private final Map<String, String> namedImports = new HashMap<>();
    private final Set<String> setImports = new HashSet<>();
    private final List<String> starImports = new ArrayList<>();
    private String namespace;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ContextTypeSolver2() {
        super();
    }

    public ContextTypeSolver2(String name) {
        super(name);
    }

    // ----------------------------------------------------------------------
    // Configuration
    // ----------------------------------------------------------------------

    public ContextTypeSolver2 withCu(CompilationUnit cu) {
        this.cu = cu;

        this.namespace = "";
        cu.getPackageDeclaration().ifPresent(this::setPackage);
        cu.getImports().forEach(this::addImport);
        addDefaultImports();
        return this;
    }

    private void setPackage(PackageDeclaration n) {
        this.namespace = n.getNameAsString();
    }

    private void addImport(ImportDeclaration n) {
        String importName = n.getNameAsString();
        if (n.isStatic()) {
            // remove the field/method name
            importName = JavaUtils.namespaceOf(importName);
            // TODO: Is this correct???
            this.namedImports.put(JavaUtils.nameOf(importName), importName);
        }
        else if (n.isAsterisk()) {
            // NOTE: '*' already stripped
            this.starImports.add(importName);
        }
        else {
            this.namedImports.put(JavaUtils.nameOf(importName), importName);
            this.setImports.add(importName);
        }
    }

    private void addDefaultImports() {
        this.starImports.add(JavaUtils.JAVA_LANG);       // import java.lang.*
        this.starImports.add(this.namespace);  // import <currentPackage>.*
        this.starImports.add(JavaUtils.ROOT);    // import <rootPackage>.*
    }

    // ----------------------------------------------------------------------
    // Resolve
    // ----------------------------------------------------------------------

    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name) {
        SymbolReference<ResolvedReferenceTypeDeclaration> solved =
            super.tryToSolveType(name);

        if (name.contains("Distribution"))
            name = name;

        if (solved.isSolved())
            return solved;

        // try to solve using named imports
        if (namedImports.containsKey(name))
            return SymbolReference.solved(new ReferencedTypeDeclaration(namedImports.get(name)));

        // check for the full qualified name
        if (setImports.contains(name))
            return SymbolReference.solved(new ReferencedTypeDeclaration(namedImports.get(name)));

        // try to use using star imports
        for (String starImport : starImports) {
            String qualifiedName = JavaUtils.qualifiedName(starImport, name);
            solved = super.tryToSolveType(qualifiedName);
            if (solved.isSolved())
                return solved;
        }

        //  try to solve using a single import:
        //
        //  1) declared star import
        //  2) current package
        //  3) "java.lang"
        //  4) ""
        //
        if (starImports.size() == 4) {
            String qualifiedName = JavaUtils.qualifiedName(starImports.get(0), name);
            return SymbolReference.solved(new ReferencedTypeDeclaration(qualifiedName));
        }

        // unsolved
        return UNSOLVED;
    }

}
