package org.hls.check;

import jext.logging.Logger;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;

public class App1 {

    public static void main(String[] args) throws ConfigurationException {
        Logger.configure();

        Configurations configs = new Configurations();
        XMLConfiguration config = configs.xml(new File("config/splserver.xml"));

        System.out.println(config.getString("override[@path]"));

        System.out.println(config.getString("models/dependency/property[@name='size.limit'][@value]"));
    }
}
