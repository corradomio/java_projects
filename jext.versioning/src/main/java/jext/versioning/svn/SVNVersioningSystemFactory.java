package jext.versioning.svn;

import jext.versioning.VersioningSystem;
import jext.versioning.VersioningSystemFactory;

import java.io.File;
import java.util.Properties;

public class SVNVersioningSystemFactory implements VersioningSystemFactory {
    @Override
    public VersioningSystem newInstance(String surl, Properties properties, File localDirectory) {
        return new SVNVersioningSystem(surl, properties, localDirectory);
    }
}
