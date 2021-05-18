package jext.versioning.local;

import jext.versioning.VersioningSystem;
import jext.versioning.VersioningSystemFactory;

import java.io.File;
import java.util.Properties;

public class LocalVersioningSystemFactory implements VersioningSystemFactory {
    @Override
    public VersioningSystem newInstance(String surl, Properties properties, File localDirectory) {
        return new LocalVersioningSystem(surl, properties, localDirectory);
    }
}
