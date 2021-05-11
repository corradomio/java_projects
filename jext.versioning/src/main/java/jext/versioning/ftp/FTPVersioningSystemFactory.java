package jext.versioning.ftp;

import jext.versioning.VersioningSystem;
import jext.versioning.VersioningSystemFactory;

import java.util.Properties;

public class FTPVersioningSystemFactory implements VersioningSystemFactory {
    @Override
    public VersioningSystem newInstance(String surl, Properties properties) {
        return new FTPVersioningSystem(surl, properties);
    }
}
