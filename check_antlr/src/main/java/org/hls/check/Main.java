package org.hls.check;

import jext.antlr.v4.csharp.CSharpLexer;
import jext.antlr.v4.csharp.CSharpParser;
import jext.antlr.v4.csharp.SkipByteOrderMarkerInputStream;
import jext.antlr.v4.java.Java9Lexer;
import jext.antlr.v4.java.Java9Parser;
import jext.antlr.v4.python3.Python3Lexer;
import jext.antlr.v4.python3.Python3Parser;
import jext.antlr.v4.scala.ScalaLexer;
import jext.antlr.v4.scala.ScalaParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import java.io.File;
import java.io.IOException;

public class Main {

    static void checkJava() throws IOException {
        System.out.println("checkJava");
        File file = new File("data/Nd4jCpu.java");
        CharStream cs = CharStreams.fromPath(file.toPath());
        Java9Lexer lexer = new Java9Lexer(cs);
        TokenStream ts = new CommonTokenStream(lexer);
        Java9Parser p = new Java9Parser(ts);

        Java9Parser.CompilationUnitContext cu = p.compilationUnit();
    }

    static void checkCSharp() throws IOException {
        System.out.println("checkCSharp");
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
        System.out.println("checkPython");
        File file = new File("data/_implementation.py");
        CharStream cs = CharStreams.fromPath(file.toPath());
        Python3Lexer lexer = new Python3Lexer(cs);
        TokenStream ts = new CommonTokenStream(lexer);
        Python3Parser p = new Python3Parser(ts);

        Python3Parser.File_inputContext cu = p.file_input();
    }

    // static void checkAspectJ() throws IOException {
    //     System.out.println("checkPython");
    //     File file = new File("data/AbstractTransactionAspect.aj");
    //     CharStream cs = CharStreams.fromPath(file.toPath());
    //     AspectJLexer lexer = new AspectJLexer(cs);
    //     TokenStream ts = new CommonTokenStream(lexer);
    //     AspectJ
    // }

    static void checkScala() throws IOException {
        System.out.println("checkScala");
        File file = new File("data/SQLConf.scala");
        CharStream cs = CharStreams.fromPath(file.toPath());
        ScalaLexer lexer = new ScalaLexer(cs);
        TokenStream ts = new CommonTokenStream(lexer);
        ScalaParser p = new ScalaParser(ts);

        ScalaParser.CompilationUnitContext cu = p.compilationUnit();
    }

    public static void main(String[] args) throws IOException {
        checkCSharp();
        checkJava();
        checkPython();
        // checkAspectJ();
        checkScala();
        System.out.println("end");
    }
}
