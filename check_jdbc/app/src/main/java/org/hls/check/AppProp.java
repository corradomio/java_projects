package org.hls.check;

import java.io.File;
import java.io.FileInputStream;
import java.util.PropertyResourceBundle;

public class AppProp {

    public final static String overwriteFilePath = "conf/resources/application.properties";
    public final static PropertyResourceBundle propertyBundle = existsAllDBProperties(overwriteFilePath) == true ?
        getPropertyResourceBundleFromFile(overwriteFilePath) :
        (PropertyResourceBundle) PropertyResourceBundle.getBundle("resources.application");

    private static PropertyResourceBundle getPropertyResourceBundleFromFile(String overwriteFileName)  {

        try {
            FileInputStream fis = new FileInputStream(overwriteFileName);
            PropertyResourceBundle propertyBundle = new PropertyResourceBundle(fis);
            return propertyBundle;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean existsAllDBProperties(String overwriteFileName) {

        // if file does not exist return false
        File f = new File(overwriteFileName);
        if (!f.exists() || f.isDirectory()) {
            System.out.println("DB details : application.properties does not exist at "+ f.getAbsolutePath()+", Using default application.properties");
            return false;
        }

        // if any of the properties does not exist return false
        try {
            FileInputStream fis = new FileInputStream(overwriteFileName);
            PropertyResourceBundle propertyBundle = new PropertyResourceBundle(fis);

            if (propertyBundle.containsKey("DRIVER_RDBMS")
                &&  propertyBundle.containsKey("HOST_RDBMS")
                // &&  propertyBundle.containsKey("USER_RDBMS")
                // &&  propertyBundle.containsKey("PW_RDBMS")
            ) {
                System.out.println("DB details : using application.properties at "+f.getAbsolutePath());
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("DB details : Not all properties found in application.properties at "+ f.getAbsolutePath()+", Using default application.properties");
        return false;
    }

    private static String getOrDefault(String key, String defaultValue) {
        if (propertyBundle == null)
            return defaultValue;
        if (!propertyBundle.containsKey(key))
            return defaultValue;
        else
            return propertyBundle.getString(key);
    }

    // private static String getAlternate(String key1, String key2, String defaultValue) {
    //     if (propertyBundle == null)
    //         return defaultValue;
    //     if (propertyBundle.containsKey(key1))
    //         return propertyBundle.getString(key1);
    //     if (propertyBundle.containsKey(key2))
    //         return propertyBundle.getString(key2);
    //     else
    //         return defaultValue;
    // }

    //
    // Used to access to the database:
    //
    //      "ae.ac.ebtic.tools.sm.utility.ReadWriteOnDB"     connection using JDBC
    //      "utility.ReadWriteProxy"    connection using BT RESTFul server Proxy
    //
    public static String READ_WRITE_DB_CLASS = getOrDefault("READ_WRITE_DB_CLASS", "ae.ac.ebtic.tools.sm.utility.ReadWriteOnDB");

    public static String QUERIES_CONFIGURATION_FILE = getOrDefault("QUERIES_CONFIGURATION_FILE", "conf/resources/sparemanagement-queries.json");

    //
    // Maps
    //

    public final static String MAP_PROVIDER = getOrDefault("MAP_PROVIDER", "bing");
    // propertyBundle == null ? null :
    // propertyBundle.getString("MAP_PROVIDER");

    public final static String MAP_API_KEY = getOrDefault("MAP_API_KEY", null);
    // propertyBundle == null ? null :
    // propertyBundle.getString("MAP_API_KEY");

    //
    // DBMS connection
    //

    public final static String DRIVER_RDBMS = getOrDefault("DRIVER_RDBMS", null);
    // propertyBundle == null ? null :
    // propertyBundle.getString("DRIVER_RDBMS");

    public final static String HOST_RDBMS = getOrDefault("HOST_RDBMS", null);
    // propertyBundle == null ? null :
    // propertyBundle.getString("HOST_RDBMS");

    public final static String USER_RDBMS = getOrDefault("USER_RDBMS", "");
    // propertyBundle == null ? null :
    // propertyBundle.getString("USER_RDBMS");

    public final static String PW_RDBMS = getOrDefault("PW_RDBMS", "");
    // propertyBundle == null ? null :
    // propertyBundle.getString("PW_RDBMS");

    //
    // Database Tables
    //

    public final static String TABLE_WAREHOUSE = getOrDefault("TABLE_WAREHOUSE", null);
    // propertyBundle == null ? null :
    // propertyBundle.getString("TABLE_WAREHOUSE");

    public final static String TABLE_TRAVEL = getOrDefault("TABLE_TRAVEL", null);
    // propertyBundle == null ? null :
    // propertyBundle.getString("TABLE_TRAVEL");

    public final static String TABLE_TRAVEL_WAREHOUSE = getOrDefault("TABLE_TRAVEL_WAREHOUSE", null);
    // propertyBundle == null ? null :
    // propertyBundle.getString("TABLE_TRAVEL_WAREHOUSE");

    public final static String TABLE_ITEM = getOrDefault("TABLE_ITEM", null);
    // propertyBundle == null ? null :
    // propertyBundle.getString("TABLE_ITEM");

    public final static String TABLE_ITEM_NETWORK = getOrDefault("TABLE_ITEM_NETWORK", null);
    // propertyBundle == null ? null :
    // propertyBundle.getString("TABLE_ITEM_NETWORK");

    public final static String TABLE_STOCK = getOrDefault("TABLE_STOCK", null);
    // propertyBundle == null ? null :
    // propertyBundle.getString("TABLE_STOCK");

    public final static String TABLE_SCENARIO_PLANT = getOrDefault("TABLE_SCENARIO_PLANT", null);
    // propertyBundle == null ? null :
    // propertyBundle.getString("TABLE_SCENARIO_PLANT");

    //
    // Warehouse Optimization (WO) parameters
    //

    public final static String TOTAL_EQUIP = getOrDefault("TOTAL_EQUIP", "1");
    // propertyBundle == null ? null :
    // propertyBundle.getString("TOTAL_EQUIP");

    public final static String TOTAL_DIST = getOrDefault("TOTAL_DIST", "5");
    // propertyBundle == null ? null :
    // propertyBundle.getString("TOTAL_DIST");

    public final static String LOC_EXCEEDING_MAX_DIST =  getOrDefault("LOC_EXCEEDING_MAX_DIST", "100");
    // propertyBundle == null ? null :
    // propertyBundle.getString("LOC_EXCEEDING_MAX_DIST");

    public final static String NO_WAREHOUSE = getOrDefault("NO_WAREHOUSE", "5");
    // propertyBundle == null ? null :
    // propertyBundle.getString("NO_WAREHOUSE");

    public final static String EQUAL_COVERAGE = getOrDefault("EQUAL_COVERAGE", "1");
    // propertyBundle == null ? null :
    // propertyBundle.getString("EQUAL_COVERAGE");

    public final static String STOCK_AVAIL = getOrDefault("STOCK_AVAIL", "50");
    // propertyBundle == null ? null :
    // propertyBundle.getString("STOCK_AVAIL");

    public final static String UNASSIGNED_WAREHOUSE = getOrDefault("UNASSIGNED_WAREHOUSE", "50");
    // propertyBundle == null ? null :
    // propertyBundle.getString("UNASSIGNED_WAREHOUSE");

    public final static String OOH_TYPE = getOrDefault("OOH_TYPE", "count");
    // propertyBundle == null ? null :
    // propertyBundle.getString("OOH_TYPE");

    public final static String TRAVEL_TIME_DESCRIPTION =getOrDefault("TRAVEL_TIME_DESCRIPTION", "Generic");
    // propertyBundle == null ? null :
    // propertyBundle.getString("TRAVEL_TIME_DESCRIPTION");

    public final static String DIST = getOrDefault("DIST", "360");
    // propertyBundle == null ? null :
    // propertyBundle.getString("DIST");

    public final static String MAX_LOCATIONS = getOrDefault("MAX_LOCATIONS", "35000");
    // propertyBundle == null ? "35000" :
    // propertyBundle.getString("MAX_LOCATIONS");

    public final static String AVOID_FERRY = getOrDefault("AVOID_FERRY", "false");
    // propertyBundle == null ? null :
    // propertyBundle.getString("AVOID_FERRY");

    public final static String AVOID_SLA = getOrDefault("AVOID_SLA", "false");


    //
    // WO GA parameters
    //

    public final static String MAX_GEN = getOrDefault("MAX_GEN", "50");
    // propertyBundle == null ? null :
    // propertyBundle.getString("MAX_GEN");

    public final static String POP_SIZE = getOrDefault("POP_SIZE", "200");
    // propertyBundle == null ? null :
    // propertyBundle.getString("POP_SIZE");

    public final static String CR_PROB = getOrDefault("CR_PROB", "0.6");
    // propertyBundle == null ? null :
    // propertyBundle.getString("CR_PROB");

    public final static String MT_PROB = getOrDefault("MT_PROB", "0.001");
    // propertyBundle == null ? null :
    // propertyBundle.getString("MT_PROB");

    public final static String SEL_OPR = getOrDefault("SEL_OPR", "tournament");
    // propertyBundle == null ? null :
    // propertyBundle.getString("SEL_OPR");

    public final static String CR_OPR = getOrDefault("CR_OPR", "uniform0.5");
    // propertyBundle == null ? null :
    // propertyBundle.getString("CR_OPR");

    public final static String MT_OPR = getOrDefault("MT_OPR", "one bit");
    // propertyBundle == null ? null :
    // propertyBundle.getString("MT_OPR");

    public final static String ELITISM = getOrDefault("ELITISM", "2");
    // propertyBundle == null ? null :
    // propertyBundle.getString("ELITISM");

    public final static String RAND_SEED = getOrDefault("RAND_SEED", "0");
    // propertyBundle == null ? null :
    // propertyBundle.getString("RAND_SEED");


    //
    // SD Problem parameters
    //

    public final static String SD_TRAVEL_TIME_DESCRIPTION = getOrDefault("SD_TRAVEL_TIME_DESCRIPTION", "Generic");
    // propertyBundle == null ? "Generic" :
    // getAlternate("SD_TRAVEL_TIME_DESCRIPTION", "TRAVEL_TIME_DESCRIPTION", "Generic");

    public final static String SD_STOCK_TO_MOVE_TYPE = getOrDefault("SD_STOCK_TO_MOVE_TYPE", "stock_to_move_by_ratio_footprint");
    // propertyBundle == null ? "stock_to_move_by_ratio_footprint" :
    // getAlternate("SD_STOCK_TYPE", "STOCK_TYPE", "stock_to_move_by_ratio_footprint");

    //
    // SD GA parameters
    //

    public final static String SD_MAX_GEN = getOrDefault("SD_MAX_GEN", "100");
    // propertyBundle == null ? null :
    // propertyBundle.getString("MAX_GEN");

    public final static String SD_POP_SIZE = getOrDefault("SD_POP_SIZE", "20");
    // propertyBundle == null ? null :
    // propertyBundle.getString("POP_SIZE");

    public final static String SD_CR_PROB = getOrDefault("SD_CR_PROB", "0.5");
    // propertyBundle == null ? null :
    // propertyBundle.getString("CR_PROB");

    public final static String SD_MT_PROB = getOrDefault("SD_MT_PROB", "0.2");
    // propertyBundle == null ? null :
    // propertyBundle.getString("MT_PROB");

    public final static String SD_SEL_OPR = getOrDefault("SD_SEL_OPR", "tournament");
    // propertyBundle == null ? null :
    // propertyBundle.getString("SEL_OPR");

    public final static String SD_CR_OPR = getOrDefault("SD_CR_OPR", "uniformOrdBased0.5");
    // propertyBundle == null ? null :
    // propertyBundle.getString("CR_OPR");

    public final static String SD_MT_OPR = getOrDefault("SD_MT_OPR", "permMutationCooling");
    // propertyBundle == null ? null :
    // propertyBundle.getString("MT_OPR");

    public final static String SD_ELITISM = getOrDefault("SD_ELITISM", "2");
    // propertyBundle == null ? null :
    // propertyBundle.getString("ELITISM");

    public final static String SD_RAND_SEED = getOrDefault("SD_RAND_SEED", "0");
    // propertyBundle == null ? null :
    // propertyBundle.getString("RAND_SEED");


    //
    // About Box
    //

    public final static String URL1 = getOrDefault("URL1", "https://espares.nat.bt.com/");
    // propertyBundle == null ? null :
    // propertyBundle.getString("URL1");

    public final static String URL2 = getOrDefault("URL2", "https://espares.nat.bt.com/");
    // propertyBundle == null ? null :
    // propertyBundle.getString("URL2");

}
