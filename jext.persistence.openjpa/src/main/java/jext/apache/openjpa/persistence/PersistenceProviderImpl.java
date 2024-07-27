package jext.apache.openjpa.persistence;

import jakarta.persistence.spi.PersistenceUnitInfo;
import org.apache.openjpa.persistence.OpenJPAEntityManagerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Replacement of OpenJPA 'PersistenceProviderImpl' to support STANDARD
 * properties used to configure the database access:
 *
 *      javax.persistence.jdbc.[url, user, password, drive]
 *      jakarta.persistence.jdbc.[url, user, password, drive]
 *
 * converted into
 *
 *      openjpa.[ConnectionURL, ConnectionUserName,
 *               ConnectionPassword, ConnectionDriverName]
 */
public class PersistenceProviderImpl extends org.apache.openjpa.persistence.PersistenceProviderImpl {

    @Override
    public OpenJPAEntityManagerFactory createEntityManagerFactory(String name, String resource, Map m) {
        m = replaceProperties(m);
        return super.createEntityManagerFactory(name, resource, m);
    }

    @Override
    public OpenJPAEntityManagerFactory createContainerEntityManagerFactory(PersistenceUnitInfo pui, Map m) {
        m = replaceProperties(m);
        return super.createContainerEntityManagerFactory(pui, m);
    }

    @Override
    public boolean acceptProvider(Map properties) {
        properties = replaceProperties(properties);
        return super.acceptProvider(properties);
    }

    private static Map<String, String> JAKARTA_PROPERTIES = new HashMap<>() {{
        put("javax.persistence.jdbc.url", "openjpa.ConnectionURL");
        put("javax.persistence.jdbc.user", "openjpa.ConnectionUserName");
        put("javax.persistence.jdbc.password", "openjpa.ConnectionPassword");
        put("javax.persistence.jdbc.driver", "openjpa.ConnectionDriverName");
        // "javax.persistence.jdbc.type",
        // "javax.persistence.jdbc.version",
        // "javax.persistence.jdbc.xa",
        put("jakarta.persistence.jdbc.url", "openjpa.ConnectionURL");
        put("jakarta.persistence.jdbc.user", "openjpa.ConnectionUserName");
        put("jakarta.persistence.jdbc.password", "openjpa.ConnectionPassword");
        put("jakarta.persistence.jdbc.driver", "openjpa.ConnectionDriverName");
    }};

    private static Map replaceProperties(Map m) {
        boolean found = false;
        for (String name : JAKARTA_PROPERTIES.keySet()) {
            found = m.containsKey(name);
            if(found) break;
        }
        if (!found) return m;

        m = new HashMap(m);
        for (String oldKey : JAKARTA_PROPERTIES.keySet()) {
            String newKey = JAKARTA_PROPERTIES.get(oldKey);
            if (m.containsKey(oldKey)) {
                m.put(newKey, m.get(oldKey));
                m.remove(oldKey);
            }
        }

        return m;
    }
}
