package jext.versioning.ftp;

import jext.util.FileUtils;
import jext.util.PathUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class RemoteFile {

    private FTPClient client;
    private FTPFile ftpFile;
    private String path;

    RemoteFile(FTPClient client, String path) {
        this.client = client;
        this.path = path;
    }

    RemoteFile(FTPClient client, String parent, FTPFile ftpFile) {
        this.client = client;
        this.ftpFile = ftpFile;
        this.path = PathUtils.concat(parent, ftpFile.getName());
    }

    String getName() {
        return ftpFile == null ? "" : ftpFile.getName();
    }

    boolean isDirectory() {
        return ftpFile == null || ftpFile.isDirectory();
    }

    List<RemoteFile> listFiles() throws IOException {
        FTPFile[] ftpFiles = client.listFiles(path);
        if (ftpFiles == null || ftpFiles.length == 0)
            return Collections.emptyList();

        List<RemoteFile> files = new ArrayList<>();
        for (FTPFile ftpFile : ftpFiles)
            files.add(new RemoteFile(client, path, ftpFile));
        return files;
    }

    public void copyInto(File file) throws IOException {
        if (isDirectory())
            copyDir(file);
        else
            copyFile(file);
    }

    private void copyFile(File file) throws IOException {
        try (InputStream istream = client.retrieveFileStream(path);
            OutputStream ostream = new FileOutputStream(file)) {
            if (istream != null)
                FileUtils.copy(istream, ostream);
        }
        catch (IOException e) {
            throw e;
        }
    }

    private void copyDir(File dir) throws IOException {
        FileUtils.mkdirs(dir);

        for (RemoteFile rfile : listFiles()) {
            String name = rfile.getName();
            File file = new File(dir, name);
            rfile.copyInto(file);
        };
    }

    public void alignWith(File file) {

    }
}
