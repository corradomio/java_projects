package jext.springframework.data.query;

public interface Query<T> {

    /**
     * Add a limit on the query result: start and count
     * @param limit
     */
    Query<T> limit(Limit limit);
    Query<T> limit(int limit);

    /**
     * If the results must be unique
     */
    Query<T> distinct();

    Query<T> edge();

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
    String id();
    String id(String alias);

    // /**
    //  * Return the values of SINGLE element
    //  */
    // Map<String, Object> values();
    // Map<String, Object> values(String alias);

    /**
     * Return the id of the selected elements
     */
    Iterable<String> ids();
    Iterable<String> ids(String alias);

    /**
     * Return the value of the selected elements
     */
    Iterable<T> allValues();
    Iterable<T> allValues(String alias);

    /**
     * Return the result of the query
     */
    Iterable<T> result();

}
