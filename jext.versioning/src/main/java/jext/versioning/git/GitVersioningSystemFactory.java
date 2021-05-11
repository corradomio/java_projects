package jext.versioning.git;

import jext.versioning.VersioningSystem;
import jext.versioning.VersioningSystemFactory;

import java.util.Properties;

public class GitVersioningSystemFactory implements VersioningSystemFactory {
    @Override
    public VersioningSystem newInstance(String surl, Properties properties) {
        return new GitVersioningSystem(surl, properties);
    }
}
