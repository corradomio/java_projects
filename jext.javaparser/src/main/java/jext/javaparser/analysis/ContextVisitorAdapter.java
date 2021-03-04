package jext.javaparser.analysis;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.TypeSolverExtWrapper;
import jext.javaparser.symbolsolver.resolution.typesolvers.TypeSolverWithNamespace;
import jext.javaparser.symbolsolver.resolution.typesolvers.UnsolvedSymbolsTypeSolver;
import jext.javaparser.util.JPUtils;
import jext.javaparser.util.UnsolvedSymbols;
import jext.lang.JavaUtils;
import jext.util.FileUtils;
import jext.util.HashSet;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class ContextVisitorAdapter extends BaseVoidVisitorAdapter {

    protected String packageName;
    protected Map<String, String> namedImports = new HashMap<>();
    protected Set<String> qualifiedImports = new HashSet<>();
    protected List<String> starImports = new ArrayList<>();
    protected UnsolvedSymbols unsolvedSymbols;
    protected File source;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ContextVisitorAdapter() {

    }

    // ----------------------------------------------------------------------
    // analyze
    // ----------------------------------------------------------------------

    public void analyze(CompilationUnit cu, TypeSolver ts) {
        cu.getStorage().ifPresent(storage ->
            source = storage.getPath().toFile());

        if (ts instanceof TypeSolverWithNamespace)
            this.ts = ts;
        else
            this.ts = new TypeSolverExtWrapper(ts);

        tsx().findTypeSolver(UnsolvedSymbolsTypeSolver.class)
            .ifPresent(usts -> this.unsolvedSymbols = usts.getUnsolvedSymbols());

        super.analyze(cu);
    }

    // ----------------------------------------------------------------------
    // visit
    // ----------------------------------------------------------------------

    // ---
    // BEFORE ImportDeclaration
    // AFTER  PackageDeclaration
    //

    @Override
    public void visit(ImportDeclaration n, Void arg) {
        // called BEFORE PackageDeclaration
        String importName = n.getNameAsString();

        if (n.isStatic()) return;
        if (n.isAsterisk()) {
            starImports.add(importName);
        }
        else {
            namedImports.put(JavaUtils.nameOf(importName), importName);
            qualifiedImports.add(importName);
        }
        logger.printf("imp: %s", n.getNameAsString());

        super.visit(n, arg);
    }

    @Override
    public void visit(PackageDeclaration n, Void arg) {
        // called AFTER the ImportDeclaration
        packageName = n.getNameAsString();

        // classes in the same package
        starImports.add(packageName);
        // classes in "java.lang"
        starImports.add(JavaUtils.JAVA_LANG);
        // to support fully qualified classes
        starImports.add(JavaUtils.ROOT);

        logger.printf("pkg: %s", packageName);

        super.visit(n, arg);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    protected Optional<String> resolveType(String name, Node n) {
        if (!JavaUtils.isClassName(name))
            return Optional.empty();
        if (tsx().isNamespace(name))
            return Optional.empty();

        if (namedImports.containsKey(name))
            return Optional.of(namedImports.get(name));

        for(String namespace : starImports) {
            String symbol = JavaUtils.qualifiedName(namespace, name);
            SymbolReference<ResolvedReferenceTypeDeclaration>
                solved = ts.tryToSolveType(symbol);
            if (solved.isSolved())
                return Optional.of(solved.toString());
        }

        logger.errorf("%s: unresolved %s\n  %s", n.getClass().getName(), name, source);

        return Optional.empty();
    }


    // ----------------------------------------------------------------------
    // end
    // ----------------------------------------------------------------------

}
