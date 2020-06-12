package jext.vfs.git;

import jext.net.URL;
import jext.vfs.VFileSystem;
import jext.vfs.VFileSystemFactory;

import java.util.Properties;

public class GitFileSystemFactory implements VFileSystemFactory {

    @Override
    public VFileSystem newFileSysytem(URL url, Properties props) {
        return new GitFileSystem(url, props);
    }
}
