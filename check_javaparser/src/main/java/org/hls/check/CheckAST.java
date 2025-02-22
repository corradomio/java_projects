package org.hls.check;

import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import jext.cache.CacheManager;
import jext.javaparser.JavaParserPool;
import jext.javaparser.analysis.LogVoidVisitorAdapter;
import jext.javaparser.symbolsolver.resolution.typesolvers.ClassPoolRegistry;
import jext.javaparser.util.ContextSolvedSymbols;
import jext.javaparser.util.UnsolvedSymbols;
import jext.util.logging.Logger;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Projects;
import jext.sourcecode.project.util.ProjectUtils;
import jext.util.PropertiesUtils;
import jext.util.concurrent.Parallel;

import java.io.File;

public class CheckAST {

    static JavaParserPool pool;
    static ClassPoolRegistry cpr;
    static ContextSolvedSymbols css;
    static UnsolvedSymbols us;

    static Logger log = Logger.getLogger("CheckAST");

    public static void main(String[] args) {
        Parallel.setup();
        Logger.configure();
        CacheManager.configure();

        try {
            log.infof("Load project");
            Project project = Projects.newProject(
                new File(
                    // "D:\\Projects.github\\ml_projects\\elasticsearch-5.6.16"
                    // "D:\\SPLGroup\\spl-workspaces\\ext-workspace\\BTProjects\\DEUM"
                    // "D:\\Projects.github\\ml_projects\\deeplearning4j-deeplearning4j-1.0.0-beta7"
                    // "D:\\Projects.github\\ml_projects\\elasticsearch-7.11.0"
                    // "D:\\SPLGroup\\spl-workspaces\\dev-workspace\\workspace\\example_repo\\bookstore"
                    // "D:\\Projects.github\\java_projects\\check_javaparser\\src\\test"
                    // "D:\\Projects.github\\java_projects\\check_java_syntax"
                        "D:\\SPLGroup\\spl-workspaces\\sample-projects\\cocome-maven-project\\cloud-logic-service\\cloud-store-logic\\store-logic-ejb\\src\\main\\java\\org\\cocome\\tradingsystem\\inventory\\application\\store\\StoreServer.java"
                ), PropertiesUtils.empty());

            project.getLibraryDownloader().setDownloadDirectory(new File("C:\\Users\\Corrado Mio\\.m2\\repository"));

            // ProjectDump.dump(project, ProjectDump.NO_LIBRARIES | ProjectDump.NO_TYPES | ProjectDump.NO_DEPENDENCIES);

            log.infof("JavaParserPool");

            pool = JavaParserPool.getPool().withCache();
            project.getModules().forEach(module -> {
                pool.addAll(module.getSources().getSourceRootDirectories());
            });

            log.infof("ClassPoolRegistry");

            cpr = new ClassPoolRegistry()
                .addJdk(new File("D:\\Java\\Jdk8.0.x64"))
                .addAll(ProjectUtils.getLibraryFiles(project));

            css = new ContextSolvedSymbols();
            us = new UnsolvedSymbols();

            log.infof("Solve");

            project.getModules().forEach(module -> {
                // Parallel.forEach(module.getSources(), source -> {
                //     solve(source.getFile());
                // });
                module.getSources().forEach(source -> {
                    solve(source.getFile());
                });
            });

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("== DONE ==");
        Parallel.shutdown();
    }

    private static void solve(File source) {
        System.out.printf("== %s ==\n", source.getName());

        ParseResult<CompilationUnit> result = pool.parse(source);
        result.ifSuccessful(cu -> {

            //SolveSymbolsVisitor ssv = new SolveSymbolsVisitor();
            //MemberDeclarations ssv = new MemberDeclarations();
            LogVoidVisitorAdapter ssv = new LogVoidVisitorAdapter();
            ssv.analyze(cu);
        });
    }
}
