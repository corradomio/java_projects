package jext.vfs.compress;

import org.apache.commons.compress.archivers.ArchiveEntry;

import java.util.HashMap;
import java.util.Map;

public class CEntries {

    private CEntry root;
    private Map<String, CEntry> entries = new HashMap<>();

    public CEntries(){
        root = new CEntry(new RootEntry());
        entries.put("", root);
    }

    CEntries add(ArchiveEntry ae) {
        String path = ae.getName();
        CEntry entry = select(path);
        entry.setEntry(ae);
        entries.put(path, entry);
        return this;
    }

    CEntry select(String path) {
        String[] parts = normalize(path).split("/");
        int len = parts.length;

        CEntry selected = root;
        for(int i=0; i<len; ++i)
            selected = selected.select(parts[i]);
        return selected;
    }

    private static String normalize(String path) {
        if (path.contains("\\"))
            path = path.replace("\\", "/'");
        while(path.startsWith("/"))
            path = path.substring(1);
        while(path.endsWith("/"))
            path = path.substring(0, path.length()-1);
        return path;
    }

}
