package org.hls.check;

import jext.cache.CacheManager;
import jext.javaparser.JavaParserPool;
import jext.javaparser.analysis.LogVoidVisitorAdapter;
import jext.logging.Logger;

import java.io.File;

public class DumpAST {

    public static void main(String[] args) {
        Logger.configure();
        CacheManager.configure();

        File source = new File(
        // "D:\\SPLGroup\\BTProjects\\DEUM\\src\\optimtools\\problems\\trap\\Trap.java"
            "D:\\SPLGroup\\spl-workspaces\\dev-workspace\\workspace\\example_repo\\cocome-maven-project\\cloud-logic-service\\cloud-logic-core-impl\\src\\main\\java\\org\\cocome\\tradingsystem\\inventory\\data\\enterprise\\Product.java"
        );

        JavaParserPool jpp = JavaParserPool.getPool();

        jpp.parse(source).ifSuccessful(cu -> {
            new LogVoidVisitorAdapter<>().analyze(cu);
        });
    }
}
