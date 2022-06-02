package org.hls.check;

import jext.antlr.v4.csharp.CSharpLexer;
import jext.antlr.v4.csharp.CSharpParser;
import jext.antlr.v4.java.Java9Lexer;
import jext.antlr.v4.java.Java9Parser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {

    static void checkjava() throws IOException {
        File file = new File("D:\\Projects.github\\java_projects\\check_antlr\\src\\main\\java\\org\\hls\\check\\Main.java");
        CharStream cs = CharStreams.fromPath(file.toPath());
        Java9Lexer lexer = new Java9Lexer(cs);
        TokenStream ts = new CommonTokenStream(lexer);
        Java9Parser p = new Java9Parser(ts);

        Java9Parser.CompilationUnitContext cu = p.compilationUnit();
    }

    static void checkcsharp() throws IOException {

        File file = new File("D:\\Projects\\CSharp\\CheckCSharp\\CheckCSharp\\Program.cs");
        // ANTLRInputStream cs = new ANTLRInputStream(new FileInputStream(file));
        CharStream cs = CharStreams.fromPath(file.toPath());
        CSharpLexer lexer = new CSharpLexer(cs);
        TokenStream ts = new CommonTokenStream(lexer);
        CSharpParser p = new CSharpParser(ts);

        CSharpParser.Compilation_unitContext cu = p.compilation_unit();

    }

    public static void main(String[] args) throws IOException {
        checkcsharp();
        checkjava();
    }
}
