package org.hls.check.check_spring_paginate.util;

import java.util.ArrayList;
import java.util.List;

public class Sort {

    private List<Order> orders = new ArrayList<Order>();

    public Sort() { }

    public boolean isEmpty() {
        return orders.isEmpty();
    }

    public void add(Order order) {
        orders.add(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public String toQueryParams() {
        StringBuilder sb = new StringBuilder();
        for (Order order : orders)
            if (sb.length() > 0)
                sb.append("&").append(order.toQueryParam());
            else
                sb.append(order.toQueryParam());

        return sb.toString();
    }

    public String toString() {
        return toQueryParams();
    }
}
