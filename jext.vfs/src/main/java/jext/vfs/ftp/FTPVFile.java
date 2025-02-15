package jext.vfs.ftp;

import jext.util.logging.Logger;
import jext.vfs.AbstractFile;
import jext.vfs.VFile;
import jext.vfs.VFileContent;
import jext.vfs.VFileSystem;
import jext.vfs.VFileType;
import jext.vfs.util.FilePath;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FTPVFile extends AbstractFile {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private FTPFile ftpFile;
    private String ftproot;

    private static Logger logger = Logger.getLogger(FTPVFile.class);
    private static byte[] EMPTY = new byte[0];

    // ----------------------------------------------------------------------
    // Constructors
    // ----------------------------------------------------------------------

    protected FTPVFile(VFileSystem fs, String path, boolean dummy) {
        super(fs, "");
        this.ftproot = path;
    }

    protected FTPVFile(VFileSystem fs, String path) {
        super(fs, path);
    }


    protected FTPVFile(VFileSystem fs, String parentPath, FTPFile ftpFile) {
        super(fs, parentPath, ftpFile.getName());

        this.ftpFile = ftpFile;
    }

    protected FTPVFile(FTPVFile parent, String path) {
        super(parent.getfs());

        this.name = new FilePath(parent.getFileName().getPath(), path);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    // used to retreieve the FTP root
    String getftproot() { return this.ftproot; }
    String getpath(boolean parent) {
        return FilePath.concat(
            getfs().getrootpath(),
            parent
                ? getFileName().getParentPath()
                : getFileName().getPath());
    }

    FTPFile getf() {
        checkf();
        return ftpFile;
    }

    FTPClient getcli() { return getfs().getcli(); }
    private FTPFileSystem getfs() { return (FTPFileSystem) fs; }

    // ----------------------------------------------------------------------

    @Override
    public VFile getParentFile() {
        return new FTPVFile(getfs(), this.name.getParentPath());
    }

    @Override
    public List<VFile> listFiles() {
        try {
            FTPClient client = getcli();
            String ftppath = getpath(false);
            FTPFile[] files = client.listFiles(ftppath);

            if (files == null || files.length == 0)
                return Collections.emptyList();

            String path = getFileName().getPath();

            return Arrays.asList(files)
                .stream()
                .map(file -> new FTPVFile(getfs(), path, file))
                .collect(Collectors.toList());
        }
        catch (IOException e) {
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
            return new FTPVFile(this, path);
    }

    @Override
    public boolean exists() {
        this.checkf();

        return ftpFile != null && ftpFile.isValid();
    }

    @Override
    public VFileType getType() {
        checkf();

        if (ftproot != null)
            return VFileType.FOLDER;

        if (ftpFile == null)
            return VFileType.UNKNOWN;

        if (ftpFile.isDirectory())
            return VFileType.FOLDER;

        if (ftpFile.isFile())
            return VFileType.FILE;

        return VFileType.UNKNOWN;
    }

    @Override
    public boolean delete() {
        try {
            String path = getpath(false);
            return getcli().deleteFile(path);
        }
        catch (IOException e) {
            logger.error(e, e);
            return false;
        }
    }

    @Override
    public boolean createFile() {
        try {
            String path = getpath(false);
            return getcli().storeFile(path, new ByteArrayInputStream(EMPTY));
        }
        catch (IOException e) {
            logger.error(e, e);
            return false;
        }
    }

    @Override
    public boolean createFolder() {
        try {
            String path = getpath(false);
            return getcli().makeDirectory(path);
        }
        catch (IOException e) {
            logger.error(e, e);
            return false;
        }
    }

    @Override
    public VFileContent getContent() {
        this.checkf();
        return new FTPFileContent(this);
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private void checkf() {
        if (ftpFile == null)
        try {
            // retrieve the name of the file
            String name = getName();

            // retrieve the path of the parent directory
            String parentpath = getpath(true);

            // retrieve the list of the files inside the parent directory
            FTPFile[] files = getcli().listFiles(parentpath);

            // serach the FTPFile inside the file list
            if (files != null)
            for(FTPFile f : files)
                if (name.equals(f.getName())) {
                    ftpFile = f;
                    break;
                }
        }
        catch (IOException e) {
            logger.error(e, e);
        }
    }

}
