package jext.antlr.v4;

import jext.antlr.v4.python3.Python3Lexer;
import jext.antlr.v4.python3.Python3Parser;
import jext.antlr.v4.util.ErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import java.io.File;
import java.io.IOException;

public class PythonParsing implements LanguageParser {

    public static ParseResult<Python3Parser.File_inputContext> parse(File file) {
        PythonParsing parser = new PythonParsing(file);
        return parser.parse();
    }

    private File file;

    private PythonParsing(File file) {
        this.file = file;
    }

    private ParseResult<Python3Parser.File_inputContext> parse() {
        ParseResult<Python3Parser.File_inputContext> result = new ParseResult<>();
        try {

            CharStream cs = CharStreams.fromPath(file.toPath());
            Python3Lexer lexer = new Python3Lexer(cs);
            TokenStream ts = new CommonTokenStream(lexer);
            Python3Parser p = new Python3Parser(ts);

            // p.removeErrorListeners();
            p.addErrorListener(new ErrorListener<>(this, result));

            result.result = p.file_input();
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
