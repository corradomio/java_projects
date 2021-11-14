package jext.servlet;

import jext.net.QueryString;

import javax.servlet.http.HttpServletRequest;

public class HttpRequest {

    public static HttpRequest of(HttpServletRequest request) {
        // Note:
        //  HttpServletRequest::getRequestURI()            -> "/a/b/c"
        //  HttpServletRequest::getRequestURL().toString() -> "http://server:port/a/b/c"
        HttpRequest hreq = new HttpRequest(request.getRequestURL().toString(), request.getQueryString());
        return hreq;
    }

    public static HttpRequest of(String requestUrl, String queryString) {
        HttpRequest hreq = new HttpRequest(requestUrl, queryString);
        return hreq;
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private final String requestUrl;
    private final QueryString queryString;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    // public HttpRequest(HttpServletRequest request) {
    //     this(request.getRequestURI(), request.getQueryString());
    // }

    public HttpRequest(String requestUrl, String queryString) {
        this.requestUrl = requestUrl;
        this.queryString = QueryString.of(queryString);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public String getRequestUrl() {
        return requestUrl;
    }

    public QueryString getQueryString() {
        return queryString;
    }

    // ----------------------------------------------------------------------
    // Delegates
    // ----------------------------------------------------------------------

    public String getString(String key) {
        return queryString.getString(key);
    }

    public String getString(String key, String defaultValue) {
        return queryString.getString(key, defaultValue);
    }

    public int getInt(String key) {
        return queryString.getInt(key);
    }

    public int getInt(String key, int defaultValue) {
        return queryString.getInt(key, defaultValue);
    }

    public double getDouble(String key, double defaultValue) {
        return queryString.getDouble(key, defaultValue);
    }

    public int[] getIntArray(String key) {
        return queryString.getIntArray(key);
    }

    public int[] getIntArray(String key, int defaultValue) {
        return queryString.getIntArray(key, defaultValue);
    }

    public boolean getBoolean(String key) {
        return queryString.getBoolean(key);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return queryString.getBoolean(key, defaultValue);
    }

    public String[] getStringArray(String key) {
        return queryString.getStringArray(key);
    }


    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
