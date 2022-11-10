package jext.graph;

import javax.annotation.Nullable;
import java.util.Map;

public interface Query {

    /**
     * Add a limit on the query result: start and count
     */
    Query limit(long limit);
    Query limit(long start, long limit);

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
     * Update some properties
     */
    long update(Map<String, Object> update);

    /**
     * Return a single id
     */
    @Nullable String id();

    /**
     * Return the values of SINGLE element
     */
    @Nullable Map<String, Object> values();

    /**
     * Return the id of the selected elements
     */
    GraphIterator<String> ids();

    /**
     * Return the values of the selected elements
     */
    GraphIterator<Map<String, Object>> allValues();

    /**
     * Return the result of the query
     */
    GraphIterator<Map<String, Object>> result();

    /**
     * Execute everything
     */
    long execute();

}
