package jext.versioning.svn;

import jext.versioning.VersioningSystem;
import jext.versioning.VersioningSystemFactory;

import java.util.Properties;

public class SVNVersioningSystemFactory implements VersioningSystemFactory {
    @Override
    public VersioningSystem newInstance(String surl, Properties properties) {
        return new SVNVersioningSystem(surl, properties);
    }
}
