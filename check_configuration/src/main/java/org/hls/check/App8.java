package org.hls.check;

import jext.configuration.PriorityConfiguration;
import jext.util.logging.Logger;
import jext.util.MapUtils;

import java.io.File;

public class App8 {

    public static void main(String[] args) {
        Logger.configure();

        PriorityConfiguration pconfig = PriorityConfiguration.getConfiguration();
        pconfig.setConfigurationFile(new File("config/splserver.xml"));

        System.out.println(pconfig.getInt("models.dependency.property[@name='size.limit'].[@value]"));
        pconfig.setProperty("models.dependency.property[@name='size.limit'].[@value]", 333);
        pconfig.save();
    }
}
