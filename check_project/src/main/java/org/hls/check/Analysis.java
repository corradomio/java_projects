package org.hls.check;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import jext.cache.CacheManager;
import jext.javaparser.JavaParserPool;
import jext.javaparser.analysis.BaseVoidVisitorAdapter;
import jext.javaparser.symbolsolver.resolution.typesolvers.ClassPoolRegistry;
import jext.javaparser.symbolsolver.resolution.typesolvers.ClassPoolRegistryTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.ContextTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.JarsTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.JavaParserPoolTypeSolver;
import jext.logging.Logger;
import jext.name.Name;
import jext.name.PathName;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Projects;
import jext.sourcecode.project.Source;
import jext.util.Parameters;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static jext.sourcecode.project.Project.ROOT_MODULE_NAME;

public class Analysis extends BaseVoidVisitorAdapter {

    private Project project;
    private ClassPoolRegistry classPoolRegistry;
    private Map<Name, JavaParserPool> pools;
    private ContextTypeSolver ctx;

    Analysis(Project project) {
        this.project = project;
        this.pools = new HashMap<>();
    }

    void analyze() {
        project.getModules().forEach(module -> {
            analyze(module);
        });

    }

    private JavaParserPool ppool(Module module) {
        Name name = module.getName();
        if (!pools.containsKey(name))
            pools.put(name, JavaParserPool.newPool(module.getName().getName()));
        return pools.get(name);
    }

    private TypeSolver modulets(Module module) {
        JavaParserPool pool = ppool(module);
        Name name = module.getName();
        return new  JavaParserPoolTypeSolver(name.getName(), pool);
    }

    private TypeSolver runtimets(Module module) {
        // Library rt = module.getRuntimeLibrary();
        TypeSolver rtts = new ReflectionTypeSolver(false);
        return rtts;
    }

    private void initialize(Module module)  {

        // 1)
        if (classPoolRegistry == null) {
            classPoolRegistry = new ClassPoolRegistry();
            project.getLibraries().forEach(library -> {
                classPoolRegistry.addAll(library.getFiles(), library.getName().getFullName());
            });
        }

        // 2)
        ctx = new ContextTypeSolver(module.getId());

        // global libraries
        ctx.add(new ClassPoolRegistryTypeSolver(project.getId(), classPoolRegistry));

        // current module
        ctx.add(modulets(module));

        // dependent modules
        module.getDependencies()
            .forEach(dmodule -> {
                ctx.add(modulets(dmodule));
            });

        // runtime
        ctx.add(runtimets(module));

    }

    private void analyze(Module module) {
        initialize(module);

        module.getSources()
            .forEach(this::analyze);
    }

    private void analyze(Source source) {
        Module module = source.getModule();
        JavaParserPool pool = ppool(module);

        ParseResult<CompilationUnit> result = pool.parse(source.getFile());
        result.ifSuccessful(cu -> {
            logger.info("Start ...");
            visit(cu, null);
            logger.info("Done");
        });
    }

    @Override
    public void visit(NameExpr n, Void args) {
        String symbol = n.getNameAsString();
        if (symbol.equals("Assert") || symbol.equals("Math"))
            System.out.printf("    %s\n", symbol);
        try {
            n.resolve();
        }
        catch (UnsolvedSymbolException e) {
            System.out.printf("    %s UnsolvedSymbolException\n", symbol);
        }
    }




    public static void main(String[] args) throws Exception {
        CacheManager.configure();
        Logger.configure();

        Name name = PathName.of("test/dl4j");
        Parameters params = Parameters.params();

        Project project = Projects.newProject(name,
            //new File("D:\\Projects.github\\ml_projects\\deeplearning4j-deeplearning4j-1.0.0-beta7"),
            new File("D:\\Projects.github\\java_projects\\check_test"),
            params
        );

        test2(project);
    }

    static void test1(Project project) {

        Analysis analysis = new Analysis(project);
        analysis.analyze(project.getModules().getModule(ROOT_MODULE_NAME));
    }

    static void test2(Project project) throws Exception {
        Module module = project.getModules().getModule();
        List<File> libs = project.getLibraries().stream()
            .flatMap(lib -> lib.getFiles().stream())
            .collect(Collectors.toList());

        CombinedTypeSolver ts = new CombinedTypeSolver();
        ts.add(new JarsTypeSolver()
            .addJdk(new File("D:\\Java\\Jdk1.8.0.x64"))
            .addAll(libs));
        module.getSources().getSourceRootDirectories().forEach(src -> {
            ts.add(new JavaParserTypeSolver(src));
        });

        JavaParser parser = new JavaParser();
        CompilationUnit cu = parser.parse(
            new File("D:\\Projects.github\\java_projects\\check_test\\src\\main\\java\\org\\hls\\check\\Test.java"))
            .getResult().get();

        // JPUtils.setSymbolSolver(cu, combinedTypeSolver);
        //
        // cu.accept(new LogVisitorAdapter<Object, Object>(), null);


    }
}
