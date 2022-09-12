package jext.antlr.v4;

import jext.antlr.v4.csharp.CSharpParser;
import jext.antlr.v4.util.ErrorListener;
import jext.antlr.v4.csharp.CSharpLexer;
import jext.antlr.v4.csharp.SkipByteOrderMarkerInputStream;
import jext.logging.Logger;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import java.io.File;
import java.io.IOException;

public class CSharpParsing implements LanguageParser {

    public static ParseResult<CSharpParser.Compilation_unitContext>
    parse(File file) {
        CSharpParsing parser = new CSharpParsing(file);
        return parser.parse();
    }

    private static Logger logger = Logger.getLogger(CSharpParsing.class);

    private File file;

    public CSharpParsing(File file) {
        this.file = file;
    }

    public ParseResult<CSharpParser.Compilation_unitContext>
    parse() {
        ParseResult<CSharpParser.Compilation_unitContext> result =
            new ParseResult<>();

        try {
            CharStream cs = CharStreams.fromStream(new SkipByteOrderMarkerInputStream(file));
            CSharpLexer lexer = new CSharpLexer(cs);
            TokenStream ts = new CommonTokenStream(lexer);
            CSharpParser p = new CSharpParser(ts);

            p.removeErrorListeners();
            // p.addErrorListener(new ErrorListener<>(this, result));

            result.result = p.compilation_unit();
        }
        catch (IOException e) {
            logger.errorf("Unable to parse '%s': %s", file.getAbsolutePath(), e);
            result.exception = e;
        }

        return result;
    }

    @Override
    public File getFile() {
        return file;
    }
}
