package org.hls.check.check_spring_paginate;

public class Pageable {
    private int page = -1;
    private int size = 10;

    private Sort sort = new Sort();
    private String select = "";
    private String where = "";

    // -- page & size

    public boolean isPaged() {
        return page > 0;
    }

    public boolean isUnpaged() {
        return page <= 0;
    }

    public void setPage(int pg) {
        page = pg;
    }

    public void setSize(int sz) {
        size = sz;
    }

    public int getPageNumber() {
        return page;
    }

    public int getPageSize() {
        if (isUnpaged())
            return Integer.MAX_VALUE - getPageOffset();
        else
            return size;
    }

    public int getPageOffset() {
        if (isUnpaged())
            return 0;
        else
            return (page-1)*size;
    }

    // -- sort

    public void setSort(String order) {
        sort.add(Order.of(order));
    }

    public Sort getSort() {
        return sort;
    }

    // -- select

    public void setSelect(String select) {
        this.select = select;
    }

    public String getSelect() {
        return select;
    }

    // -- where

    public void setWhere(String where) {
        this.where = where;
    }

    public String getWhere() {
        return where;
    }


    /**
     * Convert the object in a url with request parameters:
     *
     *      href?page=[]&size=[]&...
     *
     * @param href
     * @param newPage
     * @return
     */
    public String toRequestParams(String href, int newPage) {
        StringBuilder sb = new StringBuilder(href).append("?");

        int page = getPageNumber();

        if (newPage != -1)
            page = newPage;

        sb.append("page=").append(page).append("&size=").append(getPageSize());

        if (!select.isEmpty())
            sb.append("&select=").append(select);
        if (!where.isEmpty())
            sb.append("&where=").append(where);
        if (!sort.isEmpty())
            sb.append("&").append(sort.toQueryParams());

        return sb.toString();
    }

    /**
     *
     */
    public String toKey(String prefix) {
        if (!where.isEmpty() && !select.isEmpty())
            return String.format("%s$%s$%s", prefix, where, select);
        if (!where.isEmpty())
            return String.format("%s$%s", prefix, where);
        if (!select.isEmpty())
            return String.format("%s$%s", prefix, select);
        else
            return prefix;
    }

    // --

    @Override
    public String toString() {
        return String.format("Pageable(p=%d,s=%d,sel=%s,w=%s,o=%s)", page, size, select, where, sort);
    }
}
