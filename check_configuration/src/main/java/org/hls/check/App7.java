package org.hls.check;

import jext.configuration.Configuration;
import jext.configuration.ConfigurationUtils;
import jext.configuration.PriorityConfiguration;
import jext.logging.Logger;

import java.io.File;
import java.util.Map;

public class App7 {
    public static void main(String[] args) {
        Logger.configure();

        PriorityConfiguration pconfig = PriorityConfiguration.getConfiguration();
        pconfig.setConfigurationFile(new File("config/splserver.xml"));

        Configuration dconfig = pconfig.getDefaultConfiguration();
        Configuration ocongif = pconfig.getOverrideConfiguration();

        String ovalue = ocongif.getString("splreposManager.workspace[@path]");
        String dvalue = dconfig.getString("splreposManager.workspace[@path]");
        String description = dconfig.getString("splreposManager.workspace.documentation");

        System.out.printf("ovalue: %s\ndvalue: %s\ndesc: %s\n", ovalue, dvalue, description);

        String nvalue = dvalue + "-" + (System.currentTimeMillis() / 1000) % 10000;
        System.out.printf("nvalue: %s\n", nvalue);
        ocongif.setProperty("splreposManager.workspace[@path]", nvalue);
        System.out.println(pconfig.getString("splreposManager.workspace[@path]"));
        ocongif.save();
    }
}
