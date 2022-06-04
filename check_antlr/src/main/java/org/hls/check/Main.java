package org.hls.check;

import jext.antlr.v4.AspectJParsing;
import jext.antlr.v4.CSharpParsing;
import jext.antlr.v4.ParseResult;
import jext.antlr.v4.aspectj.AspectJParser;
import jext.antlr.v4.aspectj.AspectJParserBaseListener;
import jext.antlr.v4.csharp.CSharpLexer;
import jext.antlr.v4.csharp.CSharpParser;
import jext.antlr.v4.csharp.CSharpParserBaseListener;
import jext.antlr.v4.csharp.SkipByteOrderMarkerInputStream;
import jext.antlr.v4.java.Java9Lexer;
import jext.antlr.v4.java.Java9Parser;
import jext.antlr.v4.python3.Python3Lexer;
import jext.antlr.v4.python3.Python3Parser;
import jext.antlr.v4.scala.ScalaLexer;
import jext.antlr.v4.scala.ScalaParser;
import jext.logging.Logger;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class Main {

    static Logger logger;

    static void checkJava() throws IOException {
        logger.println("checkJava");
        File file = new File("data/Nd4jCpu.java");
        CharStream cs = CharStreams.fromPath(file.toPath());
        Java9Lexer lexer = new Java9Lexer(cs);
        TokenStream ts = new CommonTokenStream(lexer);
        Java9Parser p = new Java9Parser(ts);

        Java9Parser.CompilationUnitContext cu = p.compilationUnit();
    }

    static void checkCSharp() throws IOException {
        logger.println("checkCSharp");
        File file = new File("data/Document.cs");
        // ANTLRInputStream cs = new ANTLRInputStream(new FileInputStream(file));
        // CharStream cs = CharStreams.fromPath(file.toPath());
        CharStream cs = CharStreams.fromStream(new SkipByteOrderMarkerInputStream(file));
        CSharpLexer lexer = new CSharpLexer(cs);
        TokenStream ts = new CommonTokenStream(lexer);
        CSharpParser p = new CSharpParser(ts);

        CSharpParser.Compilation_unitContext cu = p.compilation_unit();
    }

    static void checkPython() throws IOException {
        logger.println("checkPython");
        File file = new File("data/_implementation.py");
        CharStream cs = CharStreams.fromPath(file.toPath());
        Python3Lexer lexer = new Python3Lexer(cs);
        TokenStream ts = new CommonTokenStream(lexer);
        Python3Parser p = new Python3Parser(ts);

        Python3Parser.File_inputContext cu = p.file_input();
    }

    // static void checkAspectJ() throws IOException {
    //     logger.println("checkPython");
    //     File file = new File("data/AbstractTransactionAspect.aj");
    //     CharStream cs = CharStreams.fromPath(file.toPath());
    //     AspectJLexer lexer = new AspectJLexer(cs);
    //     TokenStream ts = new CommonTokenStream(lexer);
    //     AspectJ
    // }

    static void checkScala() throws IOException {
        logger.println("checkScala");
        File file = new File("data/SQLConf.scala");
        CharStream cs = CharStreams.fromPath(file.toPath());
        ScalaLexer lexer = new ScalaLexer(cs);
        TokenStream ts = new CommonTokenStream(lexer);
        ScalaParser p = new ScalaParser(ts);

        ScalaParser.CompilationUnitContext cu = p.compilationUnit();
    }

    static void checkScanCSharp() throws IOException {
        File root = new File(
            // "D:\\Projects\\CSharp\\Apache-Lucene.Net-4.8.0-beta00016.src\\src"
            "D:\\Projects\\csharp\\Apache-Lucene.Net-4.8.0-beta00016.src\\src"
        );
        Files.walkFileTree(root.toPath(), new SimpleFileVisitor<Path>(){

            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                File file = path.toFile();
                if (!file.getName().endsWith(".cs"))
                    return FileVisitResult.CONTINUE;

                logger.println(file);

                ParseResult<CSharpParser.Compilation_unitContext>
                    result =  CSharpParsing.parse(file);

                result.ifSuccessful(tree -> {
                    ParseTreeWalker ptw = new ParseTreeWalker();
                    ptw.walk(new CSharpParserBaseListener() {

                        // Namespace_declaration
                        //         NAMESPACE qi=qualified_identifier namespace_body ';'?
                        @Override
                        public void enterNamespace_declaration(CSharpParser.Namespace_declarationContext ctx) {
                            String qi = ctx.getChild(1).getText();
                            logger.printf("    namespace %s", qi);
                        }

                        // USING identifier '=' namespace_or_type_name ';'            #usingAliasDirective
                        @Override
                        public void enterUsingAliasDirective(CSharpParser.UsingAliasDirectiveContext ctx) {
                            String identifier = ctx.getChild(1).getText();
                            String namespace_or_type_name = ctx.getChild(3).getText();
                            logger.printf("    using %s = %s", identifier, namespace_or_type_name);
                        }

                        // USING namespace_or_type_name ';'                           #usingNamespaceDirective
                        @Override
                        public void enterUsingNamespaceDirective(CSharpParser.UsingNamespaceDirectiveContext ctx) {
                            String namespace_or_type_name = ctx.getChild(1).getText();
                            logger.printf("    using %s", namespace_or_type_name);
                        }

                        // USING STATIC namespace_or_type_name ';'                    #usingStaticDirective
                        @Override
                        public void enterUsingStaticDirective(CSharpParser.UsingStaticDirectiveContext ctx) {
                            String namespace_or_type_name = ctx.getChild(2).getText();
                            logger.printf("    using static %s", namespace_or_type_name);
                        }

                    }, tree);
                });

                return super.visitFile(path, attrs);
            }
        });
    }

    static void checkScanAspectJ() throws IOException {
        File root = new File(
            "D:\\Projects.github\\spring_projects\\spring-framework\\spring-aspects"
        );
        Files.walkFileTree(root.toPath(), new SimpleFileVisitor<Path>(){

            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                File file = path.toFile();
                if (!file.getName().endsWith(".aj"))
                    return FileVisitResult.CONTINUE;

                logger.println(file);

                ParseResult<AspectJParser.CompilationUnitContext>
                    result =  AspectJParsing.parse(file);

                result.ifSuccessful(tree -> {
                    ParseTreeWalker ptw = new ParseTreeWalker();
                    ptw.walk(new AspectJParserBaseListener() {


                    }, tree);
                });

                return super.visitFile(path, attrs);
            }
        });
    }

    public static void main(String[] args) throws IOException {
        Logger.configure();

        logger = Logger.getLogger("main");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        logger.fatal("fatal");
        // logger.println( "Hello World!");

        // checkCSharp();
        // checkJava();
        // checkPython();
        // checkScala();

        // checkScanCSharp();
        checkScanAspectJ();

        logger.println("end");
    }
}
