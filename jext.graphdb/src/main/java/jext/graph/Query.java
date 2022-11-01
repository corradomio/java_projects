package jext.graph;

import javax.annotation.Nullable;
import java.util.Map;

public interface Query {

    /**
     * Add a limit on the query result: start and count
     * @param limit
     */
    Query limit(Limit limit);
    Query limit(long limit);

    /**
     * If the results must be unique
     */
    Query distinct();

    /**
     * Count the results
     */
    long count();
    long count(String alias);

    /**
     * Check if the result is not empty (count() > 0)
     */
    boolean exists();
    boolean exists(String alias);

    /**
     * Delete the selected elements
     */
    long delete();
    long delete(String alias);

    /**
     * Return a single id
     */
    @Nullable String id();
    @Nullable String id(String alias);

    /**
     * Return the values of SINGLE element
     */
    @Nullable Map<String,Object> values();
    @Nullable Map<String,Object> values(String alias);

    /**
     * Return the id of the selected elements
     */
    GraphIterator<String> ids();
    GraphIterator<String> ids(String alias);

    /**
     * Return the value of the selected elements
     */
    GraphIterator<Map<String,Object>> allValues();
    GraphIterator<Map<String,Object>> allValues(String alias);

    /**
     * Return the result of the query
     */
    GraphIterator<Map<String,Object>> result();
    GraphIterator<Map<String,Object>> result(String alias);

}
