package jext.data.kv.berkleydb;

import com.sleepycat.bind.EntryBinding;
import com.sleepycat.bind.serial.StoredClassCatalog;
import com.sleepycat.collections.StoredMap;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;

class BerkleyDB {
    EnvironmentConfig envconfig = new EnvironmentConfig();
    DatabaseConfig dbconfig = new DatabaseConfig();
    DatabaseConfig catconfig = new DatabaseConfig();

    Environment env;
    Database catalogdb;
    StoredClassCatalog catalog;

    EntryBinding keyBinding;
    EntryBinding valueBinding;

    Database db;
    StoredMap smap;

    void close() {
        try {
            db.close();
            catalog.close();
            catalogdb.close();
            env.close();
        } catch (DatabaseException e) { }
    }
}
