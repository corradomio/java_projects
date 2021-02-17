package jext.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Symbols {

    private static class Entry {
        private final String symbol;
        private final Map<String, Entry> children = new HashMap<>();

        Entry() {
            this.symbol = "";
            this.children.put(this.symbol, this);
        }
        Entry(Entry parent, String child) {
            this.symbol = compose(parent.get(), child);
        }

        Entry get(String child) {
            if (!this.children.containsKey(child))
                this.children.put(child, new Entry(this, child));
            return this.children.get(child);
        }

        String get() {
            return this.symbol;
        }

        private static String compose(String prefix, String suffix) {
            if (prefix.length() == 0) return suffix;
            if (suffix.length() == 0) return prefix;
            String symbol = prefix + "." + suffix;
            return symbol;
        }
    }

    private Entry root = new Entry();
    private List<String> symbols = new ArrayList<>();

    public synchronized String asSymbol(String qualified) {
        String[] parts = qualified.split("\\.");
        Entry entry = root;
        for(String part : parts)
            entry = entry.get(part);
        return entry.get();
    }
}
