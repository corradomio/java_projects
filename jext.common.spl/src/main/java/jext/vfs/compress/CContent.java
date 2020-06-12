package jext.vfs.compress;


import jext.vfs.VFile;
import jext.vfs.VFileContent;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CContent implements VFileContent {

    private CompressedFile file;

    CContent(CompressedFile f) {
        file = f;
    }

    @Override
    public VFile getFile() {
        return file;
    }

    @Override
    public boolean isEmpty() {
        return false;
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
        throw new UnsupportedOperationException();
    }
}
