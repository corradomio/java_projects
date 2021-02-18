package jext.javaparser.util;

import java.util.HashMap;
import java.util.Map;

public class SymbolCollection {
    private class Entry {
        String symbol;
        Map<String, Entry> children = new HashMap<>();

        Entry() {
            this.symbol = "";
            this.children.put(this.symbol, this);
        }

        Entry(Entry parent, String part) {
            this.symbol = this.compose(parent.symbol, part);
        }

        Entry get(String part) {
            if (!this.children.containsKey(part))
                this.children.put(part, new Entry(this, part));
            return this.children.get(part);
        }

        String compose(String prefix, String suffix) {
            if (prefix.length() == 0) return suffix;
            if (suffix.length() == 0) return prefix;
            return prefix + "." + suffix;
        }
    }

    private Entry root = new Entry();

    public synchronized String get(String symbol) {
        String[] parts = symbol.split("\\.");
        Entry entry = root;
        for(String part : parts)
            entry = entry.get(part);
        return entry.symbol;
    }
}
