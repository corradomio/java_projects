package jext.persistence.data.domain;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Sort {

    private static final Sort UNSORTED = Sort.by(new Order[0]);

    public static Sort unsorted() {
        return UNSORTED;
    }

    public static Sort by(Order... orders) {
        return new Sort(Arrays.asList(orders));
    }

    public static Sort by(String... properties) {
        return properties.length == 0 //
            ? Sort.unsorted() //
            : new Sort(Direction.ASC, Arrays.asList(properties));
    }

    public static Sort by(List<Order> orders) {
        return new Sort(orders);
    }

    // ----------------------------------------------------------------------

    private final List<Order> orders;

    // ----------------------------------------------------------------------

    protected Sort(List<Order> orders) {
        this.orders = orders;
    }

    private Sort(Direction direction, @Nullable List<String> properties) {

        if (properties == null || properties.isEmpty()) {
            throw new IllegalArgumentException("You have to provide at least one property to sort by");
        }

        this.orders = properties.stream() //
            .map(it -> new Order(direction, it)) //
            .collect(Collectors.toList());
    }

    // ----------------------------------------------------------------------

    public Sort descending() {
        return with(Direction.DESC);
    }

    public Sort ascending() {
        return with(Direction.ASC);
    }


    public Sort with(Direction direction) {

        List<Order> result = new ArrayList<>(orders.size());

        for (Order order : this.orders) {
            result.add(order.with(direction));
        }

        return Sort.by(result);
    }

    // ----------------------------------------------------------------------

    public enum Direction {
        ASC, DESC;

        public boolean isDescending() { return this.equals(DESC); }
        public boolean isAscending()  { return this.equals(ASC);  }
    }

    public static class Order implements Serializable {
        private final Direction direction;
        private final String property;

        public Order(@NotNull Direction direction, String property) {
            this.direction = direction;
            this.property = property;
        }

        public static Order by(String property) {
            return new Order(Direction.ASC, property);
        }

        public static Order asc(String property) {
            return new Order(Direction.ASC, property);
        }

        public static Order desc(String property) {
            return new Order(Direction.DESC, property);
        }

        public Direction getDirection() {
            return direction;
        }
        public String getProperty() {
            return property;
        }
        public boolean isAscending() {
            return this.direction.isAscending();
        }
        public boolean isDescending() {
            return this.direction.isDescending();
        }

        public Order with(Direction direction) {
            return new Order(direction, this.property);
        }
        public Order reverse() {
            return with(this.direction == Direction.ASC ? Direction.DESC : Direction.ASC);
        }

    }

    // ----------------------------------------------------------------------

}
