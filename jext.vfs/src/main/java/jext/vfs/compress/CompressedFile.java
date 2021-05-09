package jext.vfs.compress;

import jext.vfs.AbstractFile;
import jext.vfs.VFile;
import jext.vfs.VFileContent;
import jext.vfs.VFileSystem;
import jext.vfs.VFileType;

import java.util.List;
import java.util.stream.Collectors;

public class CompressedFile extends AbstractFile {

    CEntry entry;

    CompressedFile(VFileSystem fs, CEntry e) {
        super(fs, e.getPath());
        entry = e;
    }

    CompressedFile(VFileSystem fs, String path) {
        super(fs, path);
    }

    CompressedFile(VFileSystem fs, String parent, String name) {
        super(fs, parent, name);
    }

    @Override
    public VFile getParentFile() {
        return new CompressedFile(fs, getFileName().getParentPath());
    }

    @Override
    public List<VFile> listFiles() {
        check();
        return entry.children.values()
            .stream()
            .map(child -> new CompressedFile(fs, child))
            .collect(Collectors.toList());
    }

    @Override
    public VFile newFile(String path) {
        return new CompressedFile(fs, getpath(), path);
    }

    @Override
    public boolean exists() {
        return check().getSize() != -1;
    }

    @Override
    public VFileType getType() {
        if (!exists())
            return VFileType.UNKNOWN;
        if (entry.isDirectory())
            return VFileType.FOLDER;
        else
            return VFileType.FILE;
    }

    @Override
    public boolean isFile() {
        return exists() && !entry.isDirectory();
    }

    @Override
    public boolean isFolder() {
        return exists() && entry.isDirectory();
    }

    @Override
    public boolean create(VFileType type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(boolean recursively) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean createFile() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean createFolder() {
        throw new UnsupportedOperationException();
    }

    @Override
    public VFileContent getContent() {
        return new CContent(this);
    }

    @Override
    public long length() {
        if (!exists())
            return 0;
        else
            return entry.getSize();
    }


    private CEntry check() {
        if (entry == null)
            entry = getfs().select(getFileName().getPath());
        return entry;
    }


    private CompressFileSystem getfs() {
        return (CompressFileSystem) fs;
    }

    private String getpath() {
        return getFileName().getPath();
    }
}
