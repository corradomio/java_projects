package jext.vfs.svn;

import jext.net.URL;
import jext.vfs.VFileSystem;
import jext.vfs.VFileSystemFactory;

import java.util.Properties;

public class SVNFileSystemFactory implements VFileSystemFactory {

    @Override
    public VFileSystem newFileSysytem(URL url, Properties props) {
        return new SVNFileSystem(url, props);
    }
}
