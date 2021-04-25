package org.hls.check;

import jext.configuration.Configuration;
import jext.configuration.PriorityConfiguration;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

public class App3 {

    public static void main(String[] args) throws IOException, TransformerException {
        PriorityConfiguration config = PriorityConfiguration.getConfiguration();
        config.addConfigurationFile(new File("config/splserver.xml"));
        config.addConfigurationFile(new File("config/splconfig.xml"));

        System.out.println(config.getString("splreposManager.workspace[@path]"));

        Configuration c = config.configurationAt("splreposManager");

        System.out.println(c.getString("workspace[@path]"));

        c.setProperty("workspace[@path]", "changedDir");
        c.save();
    }
}
