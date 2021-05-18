package jext.versioning.ftp;

import jext.versioning.VersioningSystem;
import jext.versioning.VersioningSystemFactory;

import java.io.File;
import java.util.Properties;

public class FTPVersioningSystemFactory implements VersioningSystemFactory {
    @Override
    public VersioningSystem newInstance(String surl, Properties properties, File localDirectory) {
        return new FTPVersioningSystem(surl, properties, localDirectory);
    }
}
