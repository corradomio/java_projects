package jext.javaparser.util;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.InitializerDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.MethodReferenceExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.stmt.LocalClassDeclarationStmt;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedConstructorDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import jext.cache.Cache;
import jext.javaparser.exception.ResolveTimeoutException;
import jext.javaparser.symbolsolver.resolution.typesolvers.BaseTypeSolver;
import jext.lang.JavaUtils;
import jext.logging.Logger;
import jext.util.PropertiesUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

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

    public static boolean isTypeParameter(ClassOrInterfaceType n) {
        Optional<Node> optParent = n.getParentNode();
        while(optParent.isPresent()) {
            Node node = optParent.get();

            if (node instanceof ClassOrInterfaceDeclaration) {
                ClassOrInterfaceDeclaration decl = ((ClassOrInterfaceDeclaration) node).asClassOrInterfaceDeclaration();
                for (TypeParameter p : decl.getTypeParameters())
                    if (p.getName().equals(n.getName()))
                        return true;
            }
            if (node instanceof MethodDeclaration) {
                MethodDeclaration decl = ((MethodDeclaration) node).asMethodDeclaration();
                for (TypeParameter p : decl.getTypeParameters())
                    if (p.getName().equals(n.getName()))
                        return true;
            }
            if (node instanceof ConstructorDeclaration) {
                ConstructorDeclaration decl = ((ConstructorDeclaration) node).asConstructorDeclaration();
                for (TypeParameter p : decl.getTypeParameters())
                    if (p.getName().equals(n.getName()))
                        return true;
            }

            optParent = node.getParentNode();
        }
        return false;
    }

    public static boolean isMethodReferenceExpr(ClassOrInterfaceType n) {
        Optional<Node> optParent = n.getParentNode();
        while(optParent.isPresent()) {
            Node node = optParent.get();

            if (node instanceof MethodReferenceExpr)
                return true;

            optParent = node.getParentNode();
        }
        return false;
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
            // if (declClass.equals(ObjectCreationExpr.class))
            //     return Optional.empty();

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
        catch (UnsupportedOperationException | UnsolvedSymbolException | ResolveTimeoutException e) {
            //logger.errorf("Unable to resolve %s: %s (%s)", rmd.toString(), e.getClass().getName(), e.getMessage());
        }
        catch (RuntimeException e) {

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
        catch (UnsupportedOperationException | UnsolvedSymbolException | ResolveTimeoutException e) {
            //logger.errorf("Unable to resolve %s: %s (%s)", rcd.toString(), e.getClass().getName(), e.getMessage());
        }
        catch (RuntimeException e) {

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
        if (ts instanceof BaseTypeSolver)
            ((BaseTypeSolver)ts).getElements().forEach(JPUtils::removeTypeSolver);
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

    private static class JavaParserFacadeCache implements Cache<Object, Object> {
        private Map<TypeSolver, ?> tsmap;

        JavaParserFacadeCache(Map<TypeSolver, ?> tsmap) {
            this.tsmap = tsmap;
        }

        @Override
        public String getId() {
            return Integer.toHexString(JavaParserFacade.class.getName().hashCode());
        }

        @Override
        public String getName() {
            return JavaParserFacade.class.getName();
        }

        @Override
        public Properties getProperties() {
            return PropertiesUtils.empty();
        }

        @Override
        public long size() {
            return tsmap.size();
        }

        @Override
        public boolean containsKey(Object key) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object get(Object key, Callable<Object> callable) {
            throw new UnsupportedOperationException();
        }

        // @Override
        // public Object get(Object key, Function<Object, Object> function) {
        //     throw new UnsupportedOperationException();
        // }

        // @Override
        // public Object getOrDefault(Object key, Object defaultValue) {
        //     throw new UnsupportedOperationException();
        // }

        @Override
        public Object getChecked(Object key, Callable<Object> callable) throws ExecutionException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object get(Object o) {
            throw new UnsupportedOperationException();
        }

        // @Override
        // public Optional<Object> getIfPresent(Object key) {
        //     return Optional.empty();
        // }

        @Override
        public void put(Object key, Object value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove(Object key) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void close() {

        }
    }

    public static Cache<?, ?> getJavaParserFacadeCache() {
        return new JavaParserFacadeCache(getJavaParserFacadeTypeSolversMap());
    }

}
