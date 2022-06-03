package jext.antlr.v4;

import jext.antlr.v4.scala.ScalaLexer;
import jext.antlr.v4.scala.ScalaParser;
import jext.antlr.v4.util.ErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import java.io.File;
import java.io.IOException;

public class ScalaParsing {

    public static ParseResult<ScalaParser.CompilationUnitContext> parse(File file) {
        ScalaParsing parser = new ScalaParsing(file);
        return parser.parse();
    }

    private File file;

    private ScalaParsing(File file) {
        this.file = file;
    }

    private ParseResult<ScalaParser.CompilationUnitContext> parse() {
        ParseResult<ScalaParser.CompilationUnitContext> result = new ParseResult<>();
        try {

            CharStream cs = CharStreams.fromPath(file.toPath());
            ScalaLexer lexer = new ScalaLexer(cs);
            TokenStream ts = new CommonTokenStream(lexer);
            ScalaParser p = new ScalaParser(ts);

            p.removeErrorListeners();
            p.addErrorListener(new ErrorListener<>(result));

            result.result = p.compilationUnit();
        }
        catch (IOException e) {
            result.exception = e;
        }

        return result;
    }
}
