package test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Test {

    public static void main(String[] args) {

        List<Integer> l = Arrays.asList(1,2,3,4,5,6,7,8,10);

        l.stream().filter(i -> 0 == i%2).map(i -> i+1).forEach(i -> System.out.println(i));

        System.out.println(l.size());

    }

}
