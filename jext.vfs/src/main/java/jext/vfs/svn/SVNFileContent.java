package jext.vfs.svn;

import jext.vfs.VFile;
import jext.vfs.VFileContent;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNProperties;
import org.tmatesoft.svn.core.io.SVNRepository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SVNFileContent implements VFileContent {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private SVNFile file;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    SVNFileContent(SVNFile file) {
        this.file = file;
    }

    // ----------------------------------------------------------------------
    // Properties
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
        return 0;
    }

    @Override
    public long length() {
        SVNDirEntry entry = file.gete();
        if(entry == null)
            return 0;
        else
            return entry.getSize();
    }

    @Override
    public InputStream openInputStream() throws IOException {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            SVNRepository repo = file.getrepo();
            String path = file.getpath(false);
            SVNProperties fprops = new SVNProperties( );
            repo.getFile(path, -1, fprops, baos);
            return new ByteArrayInputStream(baos.toByteArray());
        }
        catch (SVNException e) {
            throw new IOException(e);
        }
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
