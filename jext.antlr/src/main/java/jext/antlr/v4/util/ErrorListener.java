package jext.antlr.v4.util;

import jext.antlr.v4.ParseResult;
import jext.antlr.v4.Problem;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

import java.util.BitSet;

public class ErrorListener<T> extends BaseErrorListener {

    private ParseResult<T> result;

    public ErrorListener(ParseResult<T> result) {
        this.result = result;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        Problem problem = new LexerProblem(line, charPositionInLine, msg, e);
        result.addProblem(problem);
    }

    // @Override
    // public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact, BitSet ambigAlts, ATNConfigSet configs) {
    //     Problem problem = new ParserProblem(startIndex, stopIndex, ambigAlts, configs);
    //     result.addProblem(problem);
    // }

    // @Override
    // public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex, BitSet conflictingAlts, ATNConfigSet configs) {
    //     Problem problem = new ParserProblem(startIndex, stopIndex, null, configs);
    //     result.addProblem(problem);
    // }

    // @Override
    // public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction, ATNConfigSet configs) {
    //     Problem problem = new ParserProblem(startIndex, stopIndex, null, configs);
    //     result.addProblem(problem);
    // }
}
