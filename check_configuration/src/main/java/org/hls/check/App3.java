package org.hls.check;

import jext.configuration.Configuration;
import jext.configuration.PriorityConfiguration;

import java.io.File;

public class App3 {

    public static void main(String[] args) {
        PriorityConfiguration config = PriorityConfiguration.getConfiguration();
        config.addConfigurationFile(new File("config/splserver.xml"));
        config.addConfigurationFile(new File("config/splconfig.xml"));

        System.out.println(config.getString("splreposManager.workspace[@path]"));

        Configuration c = config.configurationAt("splreposManager");

        System.out.println(c.getString("workspace[@path]"));
    }
}
