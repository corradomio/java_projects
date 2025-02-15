package org.hls.check;

import jext.configuration.Configuration;
import jext.configuration.ConfigurationUtils;
import jext.configuration.HierarchicalConfiguration;
import jext.configuration.PriorityConfiguration;
import jext.util.logging.Logger;
import jext.util.StringUtils;
import lombok.var;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App4 {

    public static void main(String[] args) {
        Logger.configure();

        PriorityConfiguration pconfig = PriorityConfiguration.getConfiguration();
        pconfig.setConfigurationFile(new File("config/splserver.xml"));

        Configuration config = pconfig.configurationAt("system");

        String entry = "graphdb.namedqueries.query";
        String ekey = "[@name]";
        String evalue = "";
        String prefix = StringUtils.prefixOf(entry, ".");
        String dkey = StringUtils.lastOf(entry, ".");

        Configuration subConfig = ((HierarchicalConfiguration)config).configurationAt(prefix);
        List<?> pnames = subConfig.getList(dkey + ekey);
        List<?> values = subConfig.getList(dkey + evalue);

        Map<String,String> props = new HashMap<>();
        for (int i=0; i<pnames.size(); ++i) {
            String pname = pnames.get(i).toString();
            String value = values.get(i).toString();

            props.put(pname, value);
        }


    }
}
