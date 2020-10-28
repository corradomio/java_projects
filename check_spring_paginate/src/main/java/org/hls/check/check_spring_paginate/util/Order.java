package org.hls.check.check_spring_paginate;

public class Order {

    private static final String ASC = "asc";
    private static final String DESC = "desc";

    // name | name,asc == name+ | name,desc == name-
    public static Order of(String order) {
        String name = order;
        boolean desc = false;
        String dir = ASC;
        int sep = order.indexOf(",");

        if (sep != -1) {
            name = order.substring(0, sep);
            dir = order.substring(sep+1);
        }
        else if (order.startsWith("+")) {
            name = order.substring(1);
            dir = ASC;
        }
        else if (order.endsWith("+")) {
            name = order.substring(0, order.length()-1);
            dir = ASC;
        }
        else if (order.startsWith("-")) {
            name = order.substring(1);
            dir = DESC;
        }
        else if (order.endsWith("-")) {
            name = order.substring(0, order.length()-1);
            dir = DESC;
        }

        desc = DESC.equals(dir);

        return new Order(name, desc);
    }

    private String name;
    private boolean desc;

    private Order(String name, boolean desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public boolean isDesc() {
        return desc;
    }

    public String toQueryParam() {
        if (desc)
            return String.format("%s,desc", name);
        else
            return name;
    }

    @Override
    public String toString() {
        return String.format("%s,%s", name, desc ? DESC : ASC);
    }

}
