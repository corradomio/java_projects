package jext.antlr.v4.util;

import jext.antlr.v4.Problem;

public class LexerProblem implements Problem {

    private int line;
    private int column;
    protected String message;
    protected Exception exception;

    public LexerProblem(int line, int column, String msg, Exception e) {
        this.line = line;
        this.column = column;
        this.message = msg;
        this.exception = e;
    }
}
