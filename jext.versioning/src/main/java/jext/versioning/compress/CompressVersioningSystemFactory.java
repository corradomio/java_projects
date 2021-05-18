package jext.versioning.compress;

import jext.versioning.VersioningSystem;
import jext.versioning.VersioningSystemFactory;

import java.io.File;
import java.util.Properties;

public class CompressVersioningSystemFactory implements VersioningSystemFactory {

    @Override
    public VersioningSystem newInstance(String surl, Properties properties, File localDirectory) {
        return new CompressVersioningSystem(surl, properties, localDirectory);
    }

}
