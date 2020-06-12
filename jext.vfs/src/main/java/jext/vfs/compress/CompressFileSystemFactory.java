package jext.vfs.compress;

import jext.net.URL;
import jext.vfs.VFileSystem;
import jext.vfs.VFileSystemFactory;

import java.util.Properties;

public class CompressFileSystemFactory implements VFileSystemFactory {

    @Override
    public VFileSystem newFileSysytem(URL url, Properties props) {
        return new CompressFileSystem(url, props);
    }
}
