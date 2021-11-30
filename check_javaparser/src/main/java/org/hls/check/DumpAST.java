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
            // "D:\\SPLGroup\\spl-workspaces\\dev-workspace\\workspace\\example_repo\\cocome-maven-project\\cloud-logic-service\\cloud-logic-core-impl\\src\\main\\java\\org\\cocome\\tradingsystem\\inventory\\data\\enterprise\\Product.java"
            // "D:\\Projects.github\\other_projects\\hibernate-orm\\hibernate-core\\src\\main\\java\\org\\hibernate\\bytecode\\internal\\bytebuddy\\ByteBuddyState.java"
            // "D:\\SPLGroup\\spl-workspaces\\dev-ws-2\\NetworkRouting\\com\\bt\\ebtic\\test\\demo\\HelloJGraphT.java"
            "D:\\Projects.github\\java_projects\\check_javaparser\\src\\test\\java\\a\\b\\c\\d\\TestHLS8.java"
        );

        JavaParserPool jpp = JavaParserPool.getPool();

        jpp.parse(source).ifSuccessful(cu -> {
            new LogVoidVisitorAdapter<>().analyze(cu);
        });
    }
}
