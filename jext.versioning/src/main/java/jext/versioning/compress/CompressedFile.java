package jext.versioning.compress;

import jext.compress.Archives;
import jext.util.FileUtils;
import jext.util.PathUtils;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CompressedFile {

    private File compressedFile;
    private String base;
    private String path;
    private long lastModified;
    private long length;
    private Map<String, CompressedFile> children;

    // ----------------------------------------------------------------------

    CompressedFile(File compressedFile, String base) {
        this.compressedFile = compressedFile;
        this.base = base;
        this.path = "";
        this.children = new HashMap<>();
    }

    CompressedFile(CompressedFile parent, String name) {
        this.compressedFile = parent.compressedFile;
        this.base = parent.base;
        this.path = PathUtils.concat(parent.path, name);
        this.children = new HashMap<>();
    }

    // ----------------------------------------------------------------------

    void add(ArchiveEntry entry) {
        String path = PathUtils.relativePath(base, entry.getName());
        CompressedFile selected = select(path);
        selected.set(entry);
    }

    private void set(ArchiveEntry entry) {
        this.lastModified = entry.getLastModifiedDate().getTime();
        this.length = entry.getSize();
    }

    private CompressedFile get(String name) {
        if (!children.containsKey(name))
            children.put(name, new CompressedFile(this, name));
        return children.get(name);
    }

    private CompressedFile select(String path) {
        String[] parts = path.split("/");
        CompressedFile selected = this;
        for (String part : parts)
            selected = selected.get(part);
        return selected;
    }

    // ----------------------------------------------------------------------

    String getName() {
        return PathUtils.getName(path);
    }

    boolean isDirectory() {
        return lastModified == 0;
    }

    List<CompressedFile> listFiles() /*throws IOException*/ {
        return new ArrayList<>(children.values());
    }

    // ----------------------------------------------------------------------

    void copyInto(File file) throws IOException {
        copy(file);
    }

    private void copy(File file) throws IOException {
        if (isDirectory())
            copyDir(file);
        else
            copyFile(file);
    }

    private void copyFile(File file) throws IOException {
        if (file.exists() && file.lastModified() == lastModified && file.length() == length)
            return;

        try (ArchiveInputStream aistream = Archives.openArchive(compressedFile);
            FileOutputStream ostream = new FileOutputStream(file)) {
            ArchiveEntry entry = Archives.select(aistream, PathUtils.concat(base, path));
            if (entry != null)
                FileUtils.copy(aistream, ostream);
        }

        file.setLastModified(lastModified);
    }

    private void copyDir(File dir) throws IOException {
        FileUtils.mkdirs(dir);

        for (CompressedFile rfile : listFiles()) {
            String name = rfile.getName();
            File file = new File(dir, name);
            rfile.copy(file);
        };
    }

    // ----------------------------------------------------------------------

    void alignWith(File file) throws IOException {
        // 1) copy
        copyInto(file);
        mergeWith(file);
    }

    private void mergeWith(File dir) throws IOException {
        if (!isDirectory())
            return;

        Set<String> names = names();
        FileUtils.asList(dir.listFiles())
            .forEach(file -> {
                if (!names.contains(file.getName()))
                    FileUtils.delete(file);
            });

        for(CompressedFile rfile : listFiles()) {
            if (!rfile.isDirectory())
                continue;

            File sdir = new File(dir, rfile.getName());
            rfile.mergeWith(sdir);
        }
    }

    private Set<String> names() {
        return children.keySet();
    }

}
