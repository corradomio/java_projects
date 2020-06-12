package jext.javaparser.analysis;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.SimpleName;
import jext.logging.Logger;

public class LogVisitorAdapter<A> extends VisitorWithDefaults<A> {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    protected Logger logger = Logger.getLogger(getClass());

    protected CompilationUnit cu;
    protected String fileName;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public LogVisitorAdapter() {

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
        if (n instanceof Name) return;
        if (n instanceof SimpleName) return;

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
            .replace('\n', '^')
            .replace('\r', ' ');
        if (s.length() > 100)
            s = s.substring(0, 100) + "...";
        return s;
    }
}
