package jext.javaparser.analysis;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.MarkerAnnotationExpr;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.expr.ThisExpr;
import com.github.javaparser.ast.stmt.ReturnStmt;
import jext.logging.Logger;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LogVisitorAdapter<A> extends VisitorWithDefaults<A> {

    private static Class<?>[] SKIPPED_CLASSES = new Class[]{
        Name.class,
        SimpleName.class,
        Modifier.class,
        NameExpr.class,
        MarkerAnnotationExpr.class,
        ThisExpr.class,
        ReturnStmt.class
    };

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    protected Logger logger = Logger.getLogger(getClass());

    protected CompilationUnit cu;
    protected String fileName;
    protected List<Class<?>> skippedClasses = new ArrayList<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public LogVisitorAdapter() {

    }

    public LogVisitorAdapter<A> skipDefaultClasses() {
        skippedClasses.addAll(Arrays.asList(SKIPPED_CLASSES));
        return this;
    }

    public LogVisitorAdapter<A> skipClass(Class<?>... clazzes) {
        skippedClasses.addAll(Arrays.asList(clazzes));
        return this;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public LogVisitorAdapter analyze(CompilationUnit cu) {
        this.cu = cu;
        this.fileName = "";

        try {
            visit(cu, null);
        } catch (Throwable e) {
            logger.error(e);
        }
        return this;
    }

    // ----------------------------------------------------------------------
    // Overrides
    // ----------------------------------------------------------------------

    // ----------------------------------------------------------------------

    public void defaultAction(Node n, A arg) {
        for (Class<?> clazz : skippedClasses)
            if (clazz.isInstance(n)) return;
        // if (n instanceof Name) return;
        // if (n instanceof SimpleName) return;
        // if (n instanceof Modifier) return;

        System.out.printf("[%s] %s\n", getClassName(n), toString(n));
    }

    public void defaultAction(NodeList n, A arg) {
        n.forEach(e ->
            defaultAction((Node)e, arg));
    }

    // ----------------------------------------------------------------------

    private static String getClassName(Node n) {
        String className = n.getClass().getName();
        if (className.startsWith("com.github.javaparser.ast."))
            return className.substring("com.github.javaparser.ast.".length());
        else
            return className;
    }

    private static String toString(Node n) {
        String s = n.toString().trim();
        s = s.replace('\t', ' ')
            .replace('\n', ' ')
            .replace('\r', ' ');
        while (s.contains("  "))
            s = s.replace("  ", " ");
        if (s.length() > 100)
            s = s.substring(0, 96) + " ...";
        return s;
    }
}
