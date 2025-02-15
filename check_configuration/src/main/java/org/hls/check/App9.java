package org.hls.check;

import jext.configuration.Configuration;
import jext.configuration.ConfigurationException;
import jext.configuration.HierarchicalConfiguration;
import jext.configuration.XMLConfiguration;
import jext.util.logging.Logger;

import java.io.File;
import java.util.List;

public class App9 {

    public static void main(String[] args) throws ConfigurationException {
        Logger.configure();

        XMLConfiguration config = new XMLConfiguration(new File("config/extlibsconfig.xml"));

        HierarchicalConfiguration cfg = config.configurationAt("extlibsManager/language[@name='csharp']/library[@name='6.0,net60,.NET Core 6']");
        List<Configuration> paths = cfg.configurationsAt("path");
        for (Configuration path : paths) {
            System.out.println(path.getString("@value"));
        }

        System.out.println(cfg);
    }
}
