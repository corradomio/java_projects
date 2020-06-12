package jext.vfs;

import jext.vfs.util.FilePath;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractFile implements VFile {

    // ----------------------------------------------------------------------
    // Protected fields
    // ----------------------------------------------------------------------

    protected VFileSystem fs;
    protected FilePath name;

    // ----------------------------------------------------------------------
    // Constructors
    // ----------------------------------------------------------------------

    protected AbstractFile(VFileSystem fs) {
        this.fs = fs;
        this.name = new FilePath("");
    }

    protected AbstractFile(VFileSystem fs, String path) {
        this.fs = fs;
        this.name = new FilePath(path);
    }

    protected AbstractFile(VFileSystem fs, String parent, String name) {
        this.fs = fs;
        this.name = new FilePath(parent, name);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public VFileName getFileName() { return name; }

    @Override
    public VFileSystem getFileSystem() {
        return fs;
    }

    @Override
    public boolean create(VFileType type) {
        switch(type) {
            case FILE:
                return createFile();
            case FOLDER:
                return createFolder();
            default:
                throw new VFileSystemException(String.format("Unable to create a %s's file", type));
        }
    }

    @Override
    public List<VFile> listFiles(final VFileSelector selector) {
        return listFiles()
            .stream()
            .filter(file -> selector.accept(file))
            .collect(Collectors.toList());
    }

    @Override
    public boolean isFile() {
        return getType() == VFileType.FILE;
    }

    @Override
    public boolean isFolder() {
        return getType() == VFileType.FOLDER;
    }

    @Override
    public boolean delete(boolean recursively) {
        if (recursively && isFolder()) {
           List<VFile> files = this.listFiles();
            files.forEach(file -> file.delete(recursively));
        }
        return delete();
    }

    // ----------------------------------------------------------------------
    // Compatibility
    // ----------------------------------------------------------------------

    @Override
    public String getName() { return getFileName().getName(); }

    @Override
    public long length() { return getContent().length(); }

    // ----------------------------------------------------------------------
    // Support
    // ----------------------------------------------------------------------

    public void copyInto(OutputStream out) throws IOException {
        int count;
        byte[] buffer = new byte[4096];

        try(InputStream in = getContent().openInputStream()) {
            while((count = in.read(buffer)) > 0)
                out.write(buffer, 0, count);
        }
    }
}
