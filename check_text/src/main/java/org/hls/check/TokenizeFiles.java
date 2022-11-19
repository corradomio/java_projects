package org.hls.check;

import jext.text.TokensScanner;

import java.io.File;
import java.io.IOException;

public class Check2 {

    public static void main(String[] args) throws IOException {
        TokensScanner.scan(new File("D:\\Projects\\elasticsearch-8.5.1"), ".java");
        System.out.println("done");
    }
}
