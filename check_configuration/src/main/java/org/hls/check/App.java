package org.hls.check;

import jext.configuration.Configurable;
import jext.logging.Logger;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.HierarchicalConfiguration;
import org.apache.commons.configuration2.ImmutableHierarchicalConfiguration;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.tree.ImmutableNode;

import java.io.File;
import java.util.List;

public class App {

    public static void main(String[] args) throws ConfigurationException {
        Logger.configure();

        // Configurations configs = new Configurations();
        // XMLConfiguration xconfig = configs.xml(new File("config\\splserver.xml"));
        // System.out.println(xconfig.getString("splreposManager.workspace[@path]"));
        // xconfig.setProperty("splreposManager.workspace[@path]", "ciccio");
        // System.out.println(xconfig.getString("splreposManager.workspace[@path]"));

        Configurable config = Configurable.getConfigurable();
        config.addConfigurationFile(new File("config\\splserver.xml"));
        config.addConfigurationFile(new File("config\\splconfig.xml"));
        config.configure();

        System.out.println(config.getString("splreposManager.workspace[@path]"));
        config.setProperty("splreposManager.workspace[@path]", "ciccio");
        System.out.println(config.getString("splreposManager.workspace[@path]"));

        // Configuration c = config.configurationAt("splreposManager");
        // System.out.println(c.getString("workspace[@path]"));

        config.save();
    }

    public static void main1(String[] args) {
        Logger.configure();

        org.apache.commons.configuration2.builder.fluent.Configurations configs = new org.apache.commons.configuration2.builder.fluent.Configurations();
        try
        {
            Configuration config = configs.properties(new File("config.properties"));
            System.out.println(config.getClass());
            // class org.apache.commons.configuration2.PropertiesConfiguration


            // access configuration properties
            String dbHost = config.getString("database.host");
            int dbPort = config.getInt("database.port");
            String dbUser = config.getString("database.user");
            String dbPassword = config.getString("database.password", "secret");  // provide a default
            long dbTimeout = config.getLong("database.timeout");

            System.out.println(dbHost);
            System.out.println(dbPort);

        }
        catch (ConfigurationException cex)
        {
            // Something went wrong
        }
        try
        {
            XMLConfiguration config = configs.xml("paths.xml");
            System.out.println(config.getClass());
            // class org.apache.commons.configuration2.XMLConfiguration


            // access configuration properties
            String stage = config.getString("processing[@stage]");
            List<String> paths = config.getList(String.class, "processing.paths.path");
            String secondPath = config.getString("processing.paths.path(1)");

            System.out.println(stage);
            System.out.println(secondPath);

            config.setProperty("database.port", 8200);
            config.addProperty("database.type", "production");

        }
        catch (ConfigurationException cex)
        {
            // Something went wrong
        }
        try
        {
            // obtain the configuration
            FileBasedConfigurationBuilder<XMLConfiguration> builder = configs.xmlBuilder("paths.xml");
            XMLConfiguration config = builder.getConfiguration();

            // update property
            config.addProperty("newProperty", "newValue");

            // save configuration
            builder.save();
        }
        catch (ConfigurationException cex)
        {
            // Something went wrong
        }
    }
}
