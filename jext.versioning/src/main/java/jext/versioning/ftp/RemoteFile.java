package jext.versioning.ftp;

import jext.util.FileUtils;
import jext.util.PathUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    List<RemoteFile> listFiles() /*throws IOException*/ {
        try {
            FTPFile[] ftpFiles = client.listFiles(path);
            if (ftpFiles == null || ftpFiles.length == 0)
                return Collections.emptyList();

            List<RemoteFile> files = new ArrayList<>();
            for (FTPFile ftpFile : ftpFiles)
                files.add(new RemoteFile(client, path, ftpFile));
            return files;
        }
        catch (IOException e) {
            return Collections.emptyList();
        }
    }

    public void copyInto(File file) throws IOException {
        copy(file);
    }

    private void copy(File file) throws IOException {
        if (isDirectory())
            copyDir(file);
        else
            copyFile(file);
    }

    private void copyFile(File file) throws IOException {
        long timestamp = ftpFile.getTimestamp().getTimeInMillis();
        long length = ftpFile.getSize();
        if (file.exists() && file.lastModified() == timestamp && file.length() == length)
            return;

        try (OutputStream ostream = new FileOutputStream(file)) {
            client.retrieveFile(path, ostream);
        }

        file.setLastModified(timestamp);
    }

    private void copyDir(File dir) throws IOException {
        FileUtils.mkdirs(dir);

        for (RemoteFile rfile : listFiles()) {
            String name = rfile.getName();
            File file = new File(dir, name);
            rfile.copy(file);
        };
    }

    public void alignWith(File file) throws IOException {
        // 1) copy
        copyInto(file);
        mergeWith(file);
    }

    private void mergeWith(File dir) throws IOException {
        if (!isDirectory())
            return;

        Set<String> names = setNames();
        FileUtils.asList(dir.listFiles())
            .forEach(file -> {
                if (!names.contains(file.getName()))
                    FileUtils.delete(file);
            });

        for(RemoteFile rfile : listFiles()) {
            if (!rfile.isDirectory())
                continue;

            File sdir = new File(dir, rfile.getName());
            rfile.mergeWith(sdir);
        }
    }

    private Set<String> setNames() throws IOException {
        FTPFile[] ftpFiles = client.listFiles(path);
        if (ftpFiles == null || ftpFiles.length == 0)
            return Collections.emptySet();

        Set<String> names = new HashSet<>();
        for (FTPFile ftpFile : ftpFiles)
            names.add(ftpFile.getName());
        return names;
    }

    // void dump(int depth) {
    //     for (int s=0; s<depth; ++s)
    //         System.out.print("  ");
    //
    //     if (isDirectory()) {
    //         System.out.printf("[%s] %s\n", getName(), path);
    //
    //         for (RemoteFile file : listFiles()) {
    //             file.dump(depth+1);
    //         };
    //     }
    //     else {
    //         System.out.println(getName());
    //     }
    // }
}
