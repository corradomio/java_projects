package jext.vfs.compress;

import java.util.HashMap;
import org.apache.commons.compress.archivers.ArchiveEntry;

import java.util.Map;

public class CEntry {

    ArchiveEntry entry;
    Map<String, CEntry> children = new HashMap<>();

    CEntry(String path) {
        entry = new InvalidEntry(path);
    }

    CEntry(CEntry parent, String name) {
        String path = String.format("%s/%s", parent.getPath(), name);
        entry = new InvalidEntry(path);
    }

    CEntry(ArchiveEntry ae) {
        entry = ae;
    }

    String getPath() {
        return entry.getName();
    }

    CEntry setEntry(ArchiveEntry ae) {
        entry = ae;
        return this;
    }

    boolean isDirectory() {
        return entry.isDirectory();
    }

    long getSize() {
        return entry.getSize();
    }

    CEntry select(String name) {
        if (name.length() == 0)
            return this;
        if (!children.containsKey(name))
            children.put(name, new CEntry(this, name));
        return children.get(name);
    }

}
