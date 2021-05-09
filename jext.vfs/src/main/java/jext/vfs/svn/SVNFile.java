package jext.vfs.svn;

import jext.logging.Logger;
import jext.vfs.AbstractFile;
import jext.vfs.VFile;
import jext.vfs.VFileContent;
import jext.vfs.VFileSystem;
import jext.vfs.VFileType;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.io.SVNRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class SVNFile extends AbstractFile {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static Logger logger = Logger.getLogger(SVNFile.class);
    private SVNDirEntry entry;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    protected SVNFile(VFileSystem fs, String path) {
        super(fs, path);
    }

    protected SVNFile(VFileSystem fs, String parent, String name) {
        super(fs, parent, name);
    }

    protected SVNFile(VFileSystem fs, String parent, SVNDirEntry entry) {
        super(fs, parent, entry.getName());
        this.entry = entry;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    public VFile getParentFile() {
        return new SVNFile(getfs(), getFileName().getParentPath());
    }

    @Override
    public List<VFile> listFiles() {
        try {
            String path = getpath(false);
            SVNRepository repo = getrepo();

            Collection entries = repo.getDir( path, -1 , null , (Collection) null );
            return Arrays.asList(entries.toArray())
                .stream()
                .map(entry -> new SVNFile(getfs(), path, (SVNDirEntry) entry))
                .collect(Collectors.toList());
        }
        catch (Exception e) {
            logger.error(e, e);
            return Collections.emptyList();
        }
    }

    @Override
    public VFile newFile(String path) {
        if ("".equals(path) || ".".equals(path))
            return this;
        if ("..".equals(path))
            return getParentFile();
        else
            return new SVNFile(getfs(), getpath(false), path);
    }

    @Override
    public boolean exists() {
        checkf();
        return entry != null;
    }

    @Override
    public VFileType getType() {
        checkf();

        if (entry == null)
            return VFileType.UNKNOWN;

        if (entry.getKind() == SVNNodeKind.DIR)
            return VFileType.FOLDER;
        if (entry.getKind() == SVNNodeKind.FILE)
            return VFileType.FILE;
        else
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
        return new SVNFileContent(this);
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    SVNFileSystem getfs() { return (SVNFileSystem) this.fs; }
    SVNRepository getrepo() { return getfs().getrepo(); }
    String getpath(boolean parent) {
        String path = parent ? getFileName().getParentPath() : getFileName().getPath();
        return path.startsWith("/") ? path.substring(1) : path;
    }

    SVNDirEntry gete() {
        checkf();
        return entry;
    }

    private void checkf() {

        if (entry == null)
        try {
            String name = getName();
            String parentPath = getpath(true);
            SVNRepository repo = getrepo();
            Collection entries = repo.getDir( parentPath, -1 , null , (Collection) null );
            Iterator it = entries.iterator( );
            while (it.hasNext()) {
                SVNDirEntry entry = ( SVNDirEntry ) it.next( );
                if (name.equals(entry.getName())) {
                    this.entry = entry;
                    break;
                }
            }
        }
        catch (Exception e) {
            logger.error(e, e);
        }
    }

}
