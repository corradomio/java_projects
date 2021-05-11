package jext.versioning;

import java.util.Properties;

public interface VersioningSystemFactory {

    VersioningSystem newInstance(String url, Properties properties);
}
