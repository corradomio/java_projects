package jext.springframework.data.neo4j;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.SessionConfig;
import org.springframework.beans.factory.annotation.Autowired;

public class Neo4JConnector {

    @Autowired(required = true)
    private org.neo4j.driver.Driver driver;

    public Neo4JConnector(Driver driver) {
        this.driver = driver;
    }

    public Neo4JConnector() { }

    public Session session() {
        return driver.session();
    }

    public Session session(SessionConfig sessionConfig) {
        return driver.session(sessionConfig);
    }

    public void close() {
        driver.close();
    }
}
