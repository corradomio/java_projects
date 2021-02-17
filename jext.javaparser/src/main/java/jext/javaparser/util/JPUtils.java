package jext.javaparser.util;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.InitializerDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.stmt.LocalClassDeclarationStmt;
import com.github.javaparser.resolution.declarations.ResolvedConstructorDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.BaseTypeSolver;
import jext.lang.JavaUtils;
import jext.logging.Logger;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JPUtils {

    private static Logger logger = Logger.getLogger(JPUtils.class);

    private static final Class<?> INITIALIZER_DECLARATION = InitializerDeclaration.class;
    private static final Class<?> METHOD_DECLARATION = MethodDeclaration.class;
    private static final Class<?> CONSTRUCTOR_DECLARATION = ConstructorDeclaration.class;
    private static final Class<?> OBJECT_CREATION_EXPR = ObjectCreationExpr.class;
    private static final Class<?> CLASS_OR_INTERFACE = ObjectCreationExpr.class;

    // ----------------------------------------------------------------------

    public static void setSymbolSolver(CompilationUnit cu, TypeSolver ts) {
        JavaSymbolSolver symbolResolver = new JavaSymbolSolver(ts);
        cu.setData(Node.SYMBOL_RESOLVER_KEY, symbolResolver);
    }

    public static void removeSymbolSolver(CompilationUnit cu) {
        cu.removeData(Node.SYMBOL_RESOLVER_KEY);
    }

    // ----------------------------------------------------------------------

    // public static MethodCallScope findMethodCallScope(Node n) {
    //     Optional<Node> current = n.getParentNode();
    //     while(current.isPresent()) {
    //         Class<?> clazz = current.get().getClass();
    //
    //         if (INITIALIZER_DECLARATION.equals(clazz))
    //             return new MethodCallScope((InitializerDeclaration)n);
    //
    //         if (METHOD_DECLARATION.equals(clazz))
    //             return new MethodCallScope((MethodDeclaration)n);
    //
    //         if (CONSTRUCTOR_DECLARATION.equals(clazz))
    //             return new MethodCallScope((ConstructorDeclaration)n);
    //     }
    //     return MethodCallScope.unavailable();
    // }

    // ----------------------------------------------------------------------

    // public static Optional<InitializerDeclaration> findInitializerDeclaration(Node n) {
    //     Optional<Node> current = n.getParentNode();
    //     while(current.isPresent()) {
    //         Class<?> clazz = current.get().getClass();
    //
    //         // found a methods declaration
    //         if (clazz.equals(MethodDeclaration.class))
    //             return Optional.empty();
    //
    //         // found an initializer
    //         if (clazz.equals(InitializerDeclaration.class))
    //             return Optional.of((InitializerDeclaration)current.get());
    //
    //         current = current.get().getParentNode();
    //     }
    //     return Optional.empty();
    // }

    // public static Optional<MethodDeclaration> findMethodDeclaration(Node n) {
    //     Optional<Node> current = n.getParentNode();
    //     while(current.isPresent()) {
    //         Class<?> clazz = current.get().getClass();
    //
    //         // found a methods declaration
    //         if (clazz.equals(MethodDeclaration.class))
    //             return Optional.of((MethodDeclaration)current.get());
    //
    //         // found an initializer
    //         if (clazz.equals(InitializerDeclaration.class))
    //             return Optional.empty();
    //
    //         current = current.get().getParentNode();
    //     }
    //     return Optional.empty();
    // }

    // ----------------------------------------------------------------------

    public static Optional<Node> findParentDeclaration(Node n) {
        Optional<Node> optDecl = n.getParentNode();
        while(optDecl.isPresent()) {
            Node declaration = optDecl.get();
            Class<?> declClass = declaration.getClass();
            // Class | Interface
            if (declClass.equals(ClassOrInterfaceDeclaration.class))
                return Optional.of(declaration);
            // Local Class
            if (declClass.equals(LocalClassDeclarationStmt.class))
                return Optional.of(declaration);

            // new Type(){ ... }
            if (declClass.equals(ObjectCreationExpr.class))
                return Optional.of(declaration);
            // Enum
            if (declClass.equals(EnumDeclaration.class))
                return Optional.of(declaration);

            optDecl = declaration.getParentNode();
        }
        return Optional.empty();
    }

    /**
     * Find the class containing this method declaration
     * @param n node
     * @return ClassOrInterfaceDeclaration
     */
    public static Optional<ClassOrInterfaceDeclaration> findClassOrInterfaceDeclaration(Node n) {
        Optional<Node> optDecl = n.getParentNode();
        while(optDecl.isPresent()) {
            Node declaration = optDecl.get();
            Class<?> declClass = declaration.getClass();
            if (declClass.equals(ClassOrInterfaceDeclaration.class))
                return Optional.of((ClassOrInterfaceDeclaration) declaration);
            if (declClass.equals(LocalClassDeclarationStmt.class))
                return Optional.of(((LocalClassDeclarationStmt) declaration).getClassDeclaration());

            if (declClass.equals(ObjectCreationExpr.class))
                return Optional.empty();
            if (declClass.equals(EnumDeclaration.class))
                return Optional.empty();

            optDecl = declaration.getParentNode();
        }
        return Optional.empty();
    }

    // public static Optional<EnumDeclaration> findEnumDeclaration(Node n) {
    //     Optional<Node> optDecl = n.getParentNode();
    //     while(optDecl.isPresent()) {
    //         Node declaration = optDecl.get();
    //         Class<?> declClass = declaration.getClass();
    //         if (declClass.equals(ClassOrInterfaceDeclaration.class))
    //             return Optional.empty();
    //         if (declClass.equals(LocalClassDeclarationStmt.class))
    //             return Optional.empty();
    //
    //         if (declClass.equals(ObjectCreationExpr.class))
    //             return Optional.empty();
    //         if (declClass.equals(EnumDeclaration.class))
    //             return Optional.of((EnumDeclaration)declaration);
    //
    //         optDecl = declaration.getParentNode();
    //     }
    //     return Optional.empty();
    // }

    // public static Optional<ObjectCreationExpr> findObjectCreationExpr(Node n) {
    //     Optional<Node> optDecl = n.getParentNode();
    //     while(optDecl.isPresent()) {
    //         Node declaration = optDecl.get();
    //         Class<?> declClass = declaration.getClass();
    //         if (declClass.equals(ClassOrInterfaceDeclaration.class))
    //             return Optional.empty();
    //         if (declClass.equals(LocalClassDeclarationStmt.class))
    //             return Optional.empty();
    //
    //         if (declClass.equals(ObjectCreationExpr.class))
    //             return Optional.of((ObjectCreationExpr)declaration);
    //         if (declClass.equals(EnumDeclaration.class))
    //             return Optional.empty();
    //
    //         optDecl = declaration.getParentNode();
    //     }
    //     return Optional.empty();
    // }

    private  static Optional<ClassOrInterfaceDeclaration> findParentClassOrInterfaceDeclaration(Node n) {
        Optional<Node> optDecl = n.getParentNode();
        while(optDecl.isPresent()) {
            Node declaration = optDecl.get();
            Class<?> declClass = declaration.getClass();
            if (declClass.equals(ClassOrInterfaceDeclaration.class))
                return Optional.of((ClassOrInterfaceDeclaration) declaration);
            if (declClass.equals(LocalClassDeclarationStmt.class))
                ; //return Optional.of(((LocalClassDeclarationStmt) declaration).getClassDeclaration());
            if (declClass.equals(ObjectCreationExpr.class))
                return Optional.empty();

            optDecl = declaration.getParentNode();
        }
        return Optional.empty();
    }

    public static Optional<String> getFullyQualifiedName(TypeDeclaration cid) {
        Optional<String> oqname = cid.getFullyQualifiedName();
        if (oqname.isPresent()) return oqname;

        String name = cid.getNameAsString();
        Optional<ClassOrInterfaceDeclaration> ocid = findParentClassOrInterfaceDeclaration(cid);
        if (!ocid.isPresent())
            return Optional.empty();
        oqname = getFullyQualifiedName(ocid.get());
        return Optional.of(JavaUtils.fullName(oqname.get(), name));
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public static String getSignature(ConstructorDeclaration cd) {
        String signature = cd.getSignature().asString();
        return signature;
    }

    public static String getSignature(MethodDeclaration md) {
        String signature = md.getSignature().asString();
        return signature;
    }

    public static String getSignature(ResolvedMethodDeclaration rmd) {
        try {
            String signature = rmd.getSignature();
            return signature;
        }
        catch (UnsupportedOperationException e) {

        }
        catch (Throwable t) {
            logger.error(t, t);
        }
        return rmd.getQualifiedName();
    }

    public static String getSignature(ResolvedConstructorDeclaration rcd) {
        try {
            // A signature with a ".../n" is the constructor for a anonymous class
            String signature = rcd.getSignature();
            if (signature.contains("/")) {
                int pos = signature.lastIndexOf('/');
                int bgn = signature.lastIndexOf('.');
                signature = signature.substring(0, pos) + signature.substring(bgn, pos);
            }
            return signature;
        }
        catch (UnsupportedOperationException e) {

        }
        catch (Throwable t) {
            logger.error(t, t);
        }
        return rcd.getQualifiedName();
    }

    // ----------------------------------------------------------------------

    private static Map<TypeSolver, ?> getJavaParserFacadeTypeSolversMap() {
        try {
            Field instancesField = JavaParserFacade.class.getDeclaredField("instances");
            instancesField.setAccessible(true);
            return (Map<TypeSolver, ?>)instancesField.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeTypeSolver(TypeSolver ts) {
        synchronized (JavaParserFacade.class) {
            Map<TypeSolver, ?> tsmap = getJavaParserFacadeTypeSolversMap();
            tsmap.remove(ts);
        }
    }

    public static void removeTypeSolvers(String prefix) {
        List<TypeSolver> toRemove = new ArrayList<>();
        synchronized (JavaParserFacade.class) {
            Map<TypeSolver, ?> tsmap = getJavaParserFacadeTypeSolversMap();
            for(TypeSolver ts : tsmap.keySet())
                if (ts instanceof BaseTypeSolver)
                    if (((BaseTypeSolver)ts).getName().startsWith(prefix))
                        toRemove.add(ts);
            for (TypeSolver ts : toRemove)
                tsmap.remove(ts);
        }
    }

}
