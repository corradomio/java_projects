package jext.versioning;

import java.io.File;
import java.util.Properties;

public interface VersioningSystemFactory {

    VersioningSystem newInstance(String url, Properties properties, File localDirectory);
}
