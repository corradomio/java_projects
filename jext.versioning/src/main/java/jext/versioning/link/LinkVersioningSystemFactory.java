package jext.versioning.link;

import jext.versioning.VersioningSystem;
import jext.versioning.VersioningSystemFactory;
import jext.versioning.novs.NovsVersioningSystem;

import java.io.File;
import java.util.Properties;

public class LinkVersioningSystemFactory implements VersioningSystemFactory {

    @Override
    public VersioningSystem newInstance(String surl, Properties properties, File localDirectory) {
        return new LinkVersioningSystem(surl, properties, localDirectory);
    }

}
