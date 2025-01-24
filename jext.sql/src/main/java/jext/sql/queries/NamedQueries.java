package jext.sql.queries;

import jext.sql.SQLException;

import java.util.Iterator;

public interface NamedQueries extends Iterable<NamedQuery> {

    Iterator<NamedQuery> iterator();

    NamedQuery getQuery(String namespace, String queryId) throws SQLException;
    NamedQuery getQuery(String name) throws SQLException;

}
