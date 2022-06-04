package jext.antlr.v4;

import jext.antlr.v4.aspectj.AspectJLexer;
import jext.antlr.v4.aspectj.AspectJParser;
import jext.antlr.v4.util.ErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import java.io.File;
import java.io.IOException;

public class AspectJParsing implements LanguageParser {

    public static ParseResult<AspectJParser.CompilationUnitContext> parse(File file) {
        AspectJParsing parser = new AspectJParsing(file);
        return parser.parse();
    }

    private File file;

    private AspectJParsing(File file) {
        this.file = file;
    }

    private ParseResult<AspectJParser.CompilationUnitContext> parse() {
        ParseResult<AspectJParser.CompilationUnitContext> result = new ParseResult<>();
        try {

            CharStream cs = CharStreams.fromPath(file.toPath());
            AspectJLexer lexer = new AspectJLexer(cs);
            TokenStream ts = new CommonTokenStream(lexer);
            AspectJParser p = new AspectJParser(ts);

            p.removeErrorListeners();
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
