package org.hls.check;

import lombok.var;

public class Main {

    public static void main(String[] args) {
        var s = "String";
        var d = new MyData("Hello", "World");

        System.out.println(d.getName());
    }
}
