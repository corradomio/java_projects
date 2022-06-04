package jext.antlr.v4;

import jext.antlr.v4.util.ErrorListener;
import jext.antlr.v4.java.Java9Lexer;
import jext.antlr.v4.java.Java9Parser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import java.io.File;
import java.io.IOException;

public class JavaParsing implements LanguageParser {

    public static ParseResult<Java9Parser.CompilationUnitContext> parse(File file) {
        JavaParsing parser = new JavaParsing(file);
        return parser.parse();
    }

    private File file;

    private JavaParsing(File file) {
        this.file = file;
    }

    private ParseResult<Java9Parser.CompilationUnitContext> parse() {
        ParseResult<Java9Parser.CompilationUnitContext> result = new ParseResult<>();
        try {

            CharStream cs = CharStreams.fromPath(file.toPath());
            Java9Lexer lexer = new Java9Lexer(cs);
            TokenStream ts = new CommonTokenStream(lexer);
            Java9Parser p = new Java9Parser(ts);

            // p.removeErrorListeners();
            p.addErrorListener(new ErrorListener<>(this, result));

            result.result = p.compilationUnit();
        }
        catch (IOException e) {
            result.exception = e;
        }

        return result;
    }

    @Override
    public File getFile() {
        return file;
    }
}
