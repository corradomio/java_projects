package org.hls.check;

import jext.configuration.Configuration;
import jext.configuration.ConfigurationUtils;
import jext.configuration.PriorityConfiguration;
import jext.util.logging.Logger;

import java.io.File;
import java.util.Map;

public class App6 {

    public static void main(String[] args) {
        Logger.configure();

        PriorityConfiguration pconfig = PriorityConfiguration.getConfiguration();
        pconfig.setConfigurationFile(new File("config/splserver.xml"));

        Configuration config = pconfig.configurationAt("system");

        Map<String,String> dict = ConfigurationUtils.loadDictionary(config, "graphdb.namedqueries.query");

        for(Map.Entry e : dict.entrySet()) {
            System.out.printf("%s:  %s\n", e.getKey(), e.getValue());
        }
    }
}
