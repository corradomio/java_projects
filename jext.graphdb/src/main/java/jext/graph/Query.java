package jext.graph;

import java.util.Map;

public interface Query {

    /**
     * Add a limit on the query result: start and count
     * @param limit
     * @return
     */
    Query limit(Limit limit);
    Query limit(int count);

    /**
     * If the results must be unique
     *
     * @return
     */
    Query distinct();

    /**
     * Count the results
     * @return
     */
    long count();
    long count(String alias);

    /**
     * Check if the result is not empty (count() > 0)
     * @return
     */
    boolean exists();
    boolean exists(String alias);

    /**
     * Delete the selected elements
     * @return
     */
    long delete();
    long delete(String alias);

    /**
     * Return a single id
     * @return
     */
    String id();
    String id(String alias);

    /**
     * Return the values of SINGLE element
     * @return
     */
    Map<String, Object> values();
    Map<String, Object> values(String alias);

    /**
     * Return the id of the selected elements
     * @return
     */
    GraphIterator<String> ids();
    GraphIterator<String> ids(String alias);

    /**
     * Return the value of the selected elements
     * @return
     */
    GraphIterator<Map<String, Object>> allValues();
    GraphIterator<Map<String, Object>> allValues(String alias);

    /**
     * Return the result of the query
     * @return
     */
    GraphIterator<Map<String, Object>> result();

}
