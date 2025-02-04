package jext.sql.queries;

import jext.sql.Connection;

import java.sql.SQLException;
import java.util.Iterator;

public interface NamedQueries extends Iterable<NamedQuery> {

    Iterator<NamedQuery> iterator();

    NamedQuery getQuery(String namespace, String queryId) throws SQLException;
    NamedQuery getQuery(String name) throws SQLException;

    void registerTo(Connection c) throws SQLException;
}
