package jext.vfs.local;

import jext.net.URL;
import jext.vfs.VFileSystem;
import jext.vfs.VFileSystemFactory;

import java.util.Properties;

public class LocalFileSystemFactory implements VFileSystemFactory {

    @Override
    public VFileSystem newFileSysytem(URL url, Properties props) {
        return new LocalFileSystem(url, props);
    }
}
