package org.hls.check;

import jext.servlet.HttpRequest;

import java.io.File;

public class App {

    public static void main(String[] args) {

        HttpRequest req = HttpRequest.of("", "a=1,2&b=11&b=22");
        System.out.println(req.get("a")[0]);
        System.out.println(req.get("b")[0]);

        File f = new File(".");

    }
}
