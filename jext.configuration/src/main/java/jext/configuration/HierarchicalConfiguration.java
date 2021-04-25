package jext.configuration;

import java.util.List;

public interface HierarchicalConfiguration extends Configuration {

    HierarchicalConfiguration configurationAt(String key);

    List<HierarchicalConfiguration> configurationsAt(String key);
}
