package jext.versioning.compress;

import org.apache.commons.compress.archivers.ArchiveEntry;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CompressedEntry {

    private String name;
    private boolean isFile;
    private Map<String, CompressedEntry> children;

    CompressedEntry() {
        this.name = "";
        this.children = new HashMap<>();
    }

    CompressedEntry(CompressedEntry parent, String name) {
        this.name = name;
        this.children = new HashMap<>();
    }

    boolean isDirectory() {
        return !isFile;
    }

    String getName() {
        return name;
    }

    void add(ArchiveEntry entry) {
        String[] parts = entry.getName().split("/");

        CompressedEntry current = this;
        for (String part : parts)
            current = current.get(part);

        current.isFile = true;
    }

    Collection<CompressedEntry> list() {
        return children.values();
    }

    Set<String> names() {
        return children.keySet();
    }

    private CompressedEntry get(String name) {
        if (!children.containsKey(name))
            children.put(name, new CompressedEntry(this, name));
        return children.get(name);
    }
}
