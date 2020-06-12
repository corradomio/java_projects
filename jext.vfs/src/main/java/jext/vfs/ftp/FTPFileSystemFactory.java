package jext.vfs.ftp;

import jext.net.URL;
import jext.vfs.VFileSystem;
import jext.vfs.VFileSystemFactory;

import java.util.Properties;

public class FTPFileSystemFactory implements VFileSystemFactory {

    @Override
    public VFileSystem newFileSysytem(URL url, Properties props) {
        return new FTPFileSystem(url, props);
    }
}
