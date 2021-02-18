package jext.javaparser.ast;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Symbols {

    private static class Stats {
        long uniqueSymbols;
        long totalSymbols;
        long uniqueAllocated;
        long totalAllocated;
    }

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

            stats.uniqueSymbols += 2;
            stats.uniqueAllocated += this.symbol.length() + this.part.length();
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

    private Stats stats = new Stats();
    private Entry root = new Entry();

    public synchronized String get(String symbol) {
        // if (!valid(symbol))
        //     return symbol;

        stats.totalSymbols += 1;
        stats.totalAllocated += symbol.length();

        String[] parts = symbol.split("\\.");
        Entry entry = root;
        for(String part : parts)
            entry = entry.get(part);
        return entry.symbol;
    }

    public void dump() {
        System.out.printf(" Total symbols  : %d\n", stats.totalSymbols);
        System.out.printf("Unique symbols  : %d\n", stats.uniqueSymbols);
        System.out.printf(" Total allocated: %d\n", stats.totalAllocated);
        System.out.printf("Unique allocated: %d\n", stats.uniqueAllocated);
        System.out.printf(" Ratio symbols  : %f\n", (0.+stats.uniqueSymbols)/(stats.totalSymbols+0.));
        System.out.printf(" Ratio allocated: %f\n", (0.+stats.uniqueAllocated)/(stats.totalAllocated+0.));
    }
}
