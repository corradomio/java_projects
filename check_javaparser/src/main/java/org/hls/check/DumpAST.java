package org.hls.check;

import com.github.javaparser.JavaParser;
import jext.cache.CacheManager;
import jext.javaparser.JavaParserPool;
import jext.javaparser.analysis.LogVoidVisitorAdapter;
import jext.logging.Logger;

import java.io.File;

public class DumpAST {

    public static void main(String[] args) {
        Logger.configure();
        CacheManager.configure();

        File source = new File("D:\\SPLGroup\\BTProjects\\DEUM\\src\\optimtools\\test\\TestDEUMdOnOnemax.java");

        JavaParserPool jpp = JavaParserPool.getPool();;

        jpp.parse(source).ifSuccessful(cu -> {
            new LogVoidVisitorAdapter<>().analyze(cu);
        });
    }
}
