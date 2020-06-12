package jext.vfs.git;

import jext.vfs.VFile;
import jext.vfs.VFileContent;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class GitFileContent implements VFileContent {

    private GitFile file;

    public GitFileContent(GitFile file) {
        this.file = file;
    }

    @Override
    public VFile getFile() {
        return file;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public long lastModified() {
        return 0;
    }

    @Override
    public long length() {
        return 0;
    }

    @Override
    public InputStream openInputStream() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public OutputStream openOutputStream() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void copyInto(OutputStream out) throws IOException {
        file.copyInto(out);
    }
}
