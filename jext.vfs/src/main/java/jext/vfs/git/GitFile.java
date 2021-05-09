package jext.vfs.git;

import jext.vfs.AbstractFile;
import jext.vfs.VFile;
import jext.vfs.VFileContent;
import jext.vfs.VFileSystem;
import jext.vfs.VFileType;
import jext.vfs.util.FilePath;

import java.util.Collections;
import java.util.List;

public class GitFile extends AbstractFile {

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    protected GitFile(VFileSystem fs) {
        super(fs);
    }

    protected GitFile(VFileSystem fs, String path) {
        super(fs, path);
    }

    protected GitFile(VFileSystem fs, String parent, String name) {
        super(fs, parent, name);
    }

    // ----------------------------------------------------------------------
    // Overrides
    // ----------------------------------------------------------------------

    @Override
    public VFile getParentFile() {
        return new GitFile(getfs(), name.getParentPath());
    }

    @Override
    public List<VFile> listFiles() {
        return Collections.emptyList();
    }

    @Override
    public VFile newFile(String path) {
        if ("".equals(path) || ".".equals(path))
            return this;
        if ("..".equals(path))
            return getParentFile();
        else
            return new GitFile(getfs(), FilePath.concat(name.getPath(), path));
    }

    @Override
    public boolean exists() {
        return false;
    }

    @Override
    public VFileType getType() {
        return VFileType.UNKNOWN;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public boolean createFile() {
        return false;
    }

    @Override
    public boolean createFolder() {
        return false;
    }

    @Override
    public VFileContent getContent() {
        return new GitFileContent(this);
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    GitFileSystem getfs() { return (GitFileSystem) this.fs;}

}
