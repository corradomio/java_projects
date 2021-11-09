package org.hls.check;

import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import jext.cache.CacheManager;
import jext.io.filters.FileFilters;
import jext.javaparser.JavaParserPool;
import jext.javaparser.symbolsolver.resolution.typesolvers.ContextTypeSolver;
import jext.logging.Logger;
import jext.util.FileUtils;
import jext.util.concurrent.Parallel;
import org.hls.java.analysis.MemberDeclarations;
import org.hls.java.analysis.TypeDeclarations;

import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CheckParallelParser {

    private static Logger log = Logger.getLogger(CheckParallelParser.class);
    private static AtomicInteger count;
    private static int size;

    public static void main(String[] args) {
        Logger.configure();
        Parallel.setup();
        CacheManager.configure();

        try {
            log.infof("Scan directory");
            List<File> sources = FileUtils.listFiles(new File(
                "D:\\Projects.github\\other_projects\\elasticsearch"
                // "D:\\Projects.github\\other_projects\\deeplearning4j"
            ), FileFilters.IS_JAVA);

            size = sources.size();
            count = new AtomicInteger();

            log.infof("Parallel parse %5d files", size);
            Parallel.forEach(sources, CheckParallelParser::parse);
            log.infof("Done");
        }
        finally {
            Parallel.shutdown();
            CacheManager.shutdown();
        }
    }

    private static void parse(File file) {
        int n = count.incrementAndGet();
        if (n%500 == 0) log.infof("... processed %5d/%d files", n, size);

        try {
            ParseResult<CompilationUnit> presult = JavaParserPool.getPool().parse(file);
            if (presult.isSuccessful()) {
                CompilationUnit cu = presult.getResult().get();

                TypeDeclarations td = new TypeDeclarations();
                MemberDeclarations md = new MemberDeclarations();
                ContextTypeSolver ts = new ContextTypeSolver();

                // td.analyze(cu, ts);
                md.analyze(cu, ts);
            }
            else {
                log.errorf("Unable to parse %s: %s", file.getAbsolutePath(), presult.getProblems().get(0).getMessage());
            }
        }
        catch (Throwable e) {
            log.error(e, e);
        }
    }
}
