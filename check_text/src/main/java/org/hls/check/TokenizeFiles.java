package org.hls.check;

import jext.util.logging.v2.Logger;
import jext.text.tokens.TokensScanner;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class TokenizeFiles {

    public static void main(String[] args) throws IOException {
        Logger.configure();

        TokensScanner.scan(
            Arrays.asList(
            new File("D:\\Dropbox\\Software\\SPLGroup\\cocome\\cocome-maven-project"),
            new File("D:\\Projects\\java-big\\deeplearning4j-1.0.0-M2.1"),
            new File("D:\\Projects\\java-big\\elasticsearch-8.5.1"),
            new File("D:\\Projects\\java-big\\hibernate-orm-5.2.0")
        ), ".java");

        System.out.println("done");
    }
}
