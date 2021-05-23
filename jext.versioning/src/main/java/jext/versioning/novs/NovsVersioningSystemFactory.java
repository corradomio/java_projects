package jext.versioning.novs;

import jext.versioning.VersioningSystem;
import jext.versioning.VersioningSystemFactory;
import jext.versioning.local.LocalVersioningSystem;

import java.io.File;
import java.util.Properties;

public class NovsVersioningSystemFactory implements VersioningSystemFactory {
    @Override
    public VersioningSystem newInstance(String surl, Properties properties, File localDirectory) {
        return new NovsVersioningSystem(surl, properties, localDirectory);
    }
}
