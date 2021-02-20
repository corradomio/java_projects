package jext.javaparser.util;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.MethodReferenceExpr;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class UniqueIdentifiers extends VoidVisitorAdapter<Void> {

    // private static class Stats {
    //     long uniqueSymbols;
    //     long totalSymbols;
    //     long uniqueAllocated;
    //     long totalAllocated;
    //
    //     public void dump() {
    //         System.out.printf(" Total symbols  : %d\n", totalSymbols);
    //         System.out.printf("Unique symbols  : %d\n", uniqueSymbols);
    //         System.out.printf(" Total allocated: %d\n", totalAllocated);
    //         System.out.printf("Unique allocated: %d\n", uniqueAllocated);
    //         System.out.printf(" Ratio symbols  : %f\n", (0.+uniqueSymbols)/(totalSymbols+0.));
    //         System.out.printf(" Ratio allocated: %f\n", (0.+uniqueAllocated)/(totalAllocated+0.));
    //     }
    // }

    // private Stats stats = new Stats();

    private class Entry {
        String part;
        String symbol;
        Map<String, Entry> children = new HashMap<>();

        Entry() {
            this.part = "";
            this.symbol = this.part;
            this.children.put(this.symbol, this);
        }

        Entry(Entry parent, String part) {
            this.part = part;
            this.symbol = this.compose(parent.symbol, part);

            // stats.uniqueSymbols += 2;
            // stats.uniqueAllocated += this.symbol.length() + this.part.length();
        }

        Entry get(String part) {
            if (!this.children.containsKey(part)) {
                this.children.put(part, new Entry(this, part));
            }

            return this.children.get(part);
        }

        String compose(String prefix, String suffix) {
            if (prefix.length() == 0) return suffix;
            if (suffix.length() == 0) return prefix;
            return prefix + "." + suffix;
        }
    }

    private class Symbols {

        private final Entry root = new Entry();

        public synchronized String get(String symbol) {
            // stats.totalSymbols += 1;
            // stats.totalAllocated += symbol.length();

            String[] parts = symbol.split("\\.");
            Entry entry = root;
            for(String part : parts)
                entry = entry.get(part);
            return entry.symbol;
        }

    }

    private Symbols symbols = new Symbols();

    public void analyze(CompilationUnit cu) {
        visit(cu, null);
    }

    @Override
    public void visit(Name n, Void arg) {
        makeUnique(n);
        super.visit(n, arg);
    }
    @Override
    public void visit(SimpleName n, Void arg) {
        makeUnique(n);
        super.visit(n, arg);
    }
    @Override
    public void visit(MethodReferenceExpr n, Void arg) {
        makeUnique(n);
        super.visit(n, arg);
    }

    private void makeUnique(Node n) {
        try {
            Field field = n.getClass().getDeclaredField("identifier");
            field.setAccessible(true);
            String symbol = (String) field.get(n);
            field.set(n, symbols.get(symbol));
        } catch (IllegalAccessException | NoSuchFieldException e) {

        }
    }

    public void stats() {
        // stats.dump();
    }
}
