package jext.graph;

import javax.annotation.Nullable;
import java.util.Map;

public interface Query {

    Query assign(Map<String,Object> values);
    long execute();

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

    /**
     * Check if the result is not empty (count() > 0)
     */
    boolean exists();

    /**
     * Delete the selected elements
     */
    long delete();

    /**
     * Return a single id
     */
    @Nullable String id();

    /**
     * Return the values of SINGLE element
     */
    @Nullable Map<String,Object> values();

    /**
     * Return the id of the selected elements
     */
    GraphIterator<String> ids();

    /**
     * Return the value of the selected elements
     */
    GraphIterator<Map<String,Object>> allValues();

    /**
     * Return the result of the query
     */
    GraphIterator<Map<String,Object>> result();

}
