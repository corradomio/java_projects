package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import jext.javaparser.util.JPUtils;
import jext.lang.JavaUtils;

public class UnsolvedSymbolsRegistryTypeSolver extends BaseTypeSolver {

    private final UnsolvedSymbolsRegistry usr;

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    public UnsolvedSymbolsRegistryTypeSolver() {
        this(new UnsolvedSymbolsRegistry());
    }

    public UnsolvedSymbolsRegistryTypeSolver(UnsolvedSymbolsRegistry usr) {
        super(UnsolvedSymbolsRegistryTypeSolver.class.getName());
        this.usr = usr;
    }

    // ----------------------------------------------------------------------
    // Predicates
    // ----------------------------------------------------------------------

    @Override
    public boolean isNamespace(String name) {
        return usr.isNamespace(name);
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name) {
        ResolvedReferenceTypeDeclaration rrtd = usr.get(name);
        if (rrtd != null)
            return SymbolReference.solved(rrtd);
        else
            return UNSOLVED;
    }

    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(Type n) {
        if (!n.isClassOrInterfaceType())
            return UNSOLVED;

        ClassOrInterfaceType cit = n.asClassOrInterfaceType();
        String name = getNameWithScope(cit);
        int nTypeParams = getTypeParamsCount(cit);

        ResolvedReferenceTypeDeclaration rrtd = usr.get(name);
        if (rrtd != null && (nTypeParams == 0 || nTypeParams == rrtd.getTypeParameters().size()))
            return SymbolReference.solved(rrtd);

        usr.register(name, nTypeParams);
        rrtd = usr.get(name);
        return SymbolReference.solved(rrtd);
    }

    private String getNameWithScope(ClassOrInterfaceType n) {
        String name = n.getNameWithScope();
        if (name.contains(".")) return name;

        CompilationUnit cu = JPUtils.getCompilationUnit(n);
        for (ImportDeclaration idecl : cu.getImports()) {
            String imported = idecl.getNameAsString();
            if (idecl.isAsterisk())
                continue;
            if (idecl.isStatic())
                imported = JavaUtils.namespaceOf(imported);

            if (name.equals(JavaUtils.nameOf(imported)))
                return imported;
        }
        return name;
    }

    private int getTypeParamsCount(ClassOrInterfaceType n) {
        if (n.getTypeArguments().isPresent())
            return n.getTypeArguments().get().size();
        else
            return 0;
    }

}
