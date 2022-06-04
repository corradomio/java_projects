package jext.antlr.v4;

import jext.antlr.v4.kotlin.KotlinLexer;
import jext.antlr.v4.kotlin.KotlinParser;
import jext.antlr.v4.util.ErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import java.io.File;
import java.io.IOException;

public class KotlinParsing {

    public static ParseResult<KotlinParser.KotlinFileContext> parse(File file) {
        KotlinParsing parser = new KotlinParsing(file);
        return parser.parse();
    }

    private File file;

    private KotlinParsing(File file) {
        this.file = file;
    }

    private ParseResult<KotlinParser.KotlinFileContext> parse() {
        ParseResult<KotlinParser.KotlinFileContext> result = new ParseResult<>();
        try {

            CharStream cs = CharStreams.fromPath(file.toPath());
            KotlinLexer lexer = new KotlinLexer(cs);
            TokenStream ts = new CommonTokenStream(lexer);
            KotlinParser p = new KotlinParser(ts);

            p.removeErrorListeners();
            p.addErrorListener(new ErrorListener<>(result));

            result.result = p.kotlinFile();
        }
        catch (IOException e) {
            result.exception = e;
        }

        return result;
    }
}
