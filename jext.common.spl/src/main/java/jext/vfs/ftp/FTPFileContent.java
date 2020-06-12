package jext.vfs.ftp;

import jext.vfs.VFile;
import jext.vfs.VFileContent;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FTPFileContent implements VFileContent {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private FTPVFile file;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    FTPFileContent(FTPVFile file) {
        this.file = file;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    public VFile getFile() {
        return file;
    }

    @Override
    public boolean isEmpty() {
        return length() == 0;
    }

    @Override
    public long lastModified() {
        FTPFile ftpf = this.file.getf();
        if (ftpf == null)
            return 0;
        else
            return ftpf.getTimestamp().getTime().getTime();
    }

    @Override
    public long length() {
        FTPFile ftpfile = file.getf();
        if (ftpfile == null)
            return 0;

        if (ftpfile.isFile())
            return ftpfile.getSize();

        if (ftpfile.isDirectory()) {
            return file.listFiles().size();
        }

        return 0;
    }

    @Override
    public InputStream openInputStream() throws IOException {
        String path = file.getpath(false);
        return file.getcli().retrieveFileStream(path);
    }

    @Override
    public OutputStream openOutputStream() throws IOException {
        String path = file.getpath(false);
        return file.getcli().storeFileStream(path);
    }

    @Override
    public void copyInto(OutputStream out) throws IOException {
        String path = file.getpath(false);
        file.getcli().retrieveFile(path, out);
    }
}

