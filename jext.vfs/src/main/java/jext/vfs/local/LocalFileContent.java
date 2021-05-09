package jext.vfs.local;

import jext.vfs.VFile;
import jext.vfs.VFileContent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LocalFileContent implements VFileContent {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private LocalFile file;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    LocalFileContent(LocalFile file) {
        this.file = file;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public VFile getFile() {
        return file;
    }

    public boolean isEmpty() {
        return length() == 0;
    }

    public long lastModified() {
        return file.getf().lastModified();
    }

    public long length() {
        if (file.getf().isDirectory())
            return file.getf().list().length;
        if (file.getf().isFile())
            return file.getf().length();
        else
            return 0;
    }

    public InputStream openInputStream() throws FileNotFoundException {
        return new FileInputStream(file.getf());
    }

    public OutputStream openOutputStream() throws FileNotFoundException {
        return new FileOutputStream(file.getf());
    }

    @Override
    public void copyInto(OutputStream out) throws IOException {
        file.copyInto(out);
    }
}
