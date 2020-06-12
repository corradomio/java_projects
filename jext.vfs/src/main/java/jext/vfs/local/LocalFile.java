package jext.vfs.local;

import jext.vfs.AbstractFile;
import jext.vfs.VFile;
import jext.vfs.VFileContent;
import jext.vfs.VFileType;
import jext.vfs.util.FilePath;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LocalFile extends AbstractFile {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private File file;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    LocalFile(LocalFileSystem fs, File local, String rpath) {
        super(fs, rpath);

        this.file = local;

        String path = this.name.getPath();
        assert "".equals(path) || path.startsWith("/");
    }

    LocalFile(LocalFileSystem fs, String rpath) {
        super(fs, rpath);

        this.file = new File(rpath);

        String path = this.name.getPath();
        assert "".equals(path) || path.startsWith("/");
    }

    LocalFile(LocalFile file, String name) {
        super(file.getFileSystem(), file.getFileName().getPath(), name);

        this.file = new File(file.file, name);

        String path = this.name.getPath();
        assert "".equals(path) || path.startsWith("/");
    }

    LocalFile(LocalFileSystem fs, File local) {
        super(fs, FilePath.relative(fs.getLocalRoot().getAbsolutePath(), local.getAbsolutePath()));

        this.file = local;

        String path = this.name.getPath();
        assert "".equals(path) || path.startsWith("/");
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    LocalFileSystem getfs() { return (LocalFileSystem) this.fs;}
    File getf() { return file; }

    @Override
    public VFile getParentFile() {
        return new LocalFile(getfs(), name.getParentPath());
    }

    @Override
    public List<VFile> listFiles() {
        File[] files = file.listFiles();
        if (files == null || files.length == 0)
            return Collections.emptyList();
        return Arrays.asList(files)
            .stream()
            .map(file -> new LocalFile(getfs(), file))
            .collect(Collectors.toList());
    }

    @Override
    public VFile newFile(String path) {
        if ("".equals(path) || ".".equals(path))
            return this;
        if ("..".equals(path))
            return getParentFile();
        else
            return new LocalFile(this, path);
    }

    @Override
    public boolean exists() {
        return file.exists();
    }

    @Override
    public VFileType getType() {
        if (file.isFile())
            return VFileType.FILE;
        if (file.isDirectory())
            return VFileType.FOLDER;
        else
            return VFileType.UNKNOWN;
    }

    @Override
    public boolean delete() {
        return file.delete();
    }

    @Override
    public boolean createFile() {
        if (file.exists())
            return false;
        try {
            new FileOutputStream(file).close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean createFolder() {
        if (file.exists())
            return false;
        return file.mkdirs();
    }

    @Override
    public VFileContent getContent() {
        return new LocalFileContent(this);
    }
}
