package jext.vfs;

import jext.net.URL;

import java.util.Properties;

public interface VFileSystemFactory {

    VFileSystem newFileSysytem(URL url, Properties props);
}
