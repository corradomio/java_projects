package jext.antlr.v4.util;

import jext.antlr.v4.Problem;
import org.antlr.v4.runtime.atn.ATNConfigSet;

import java.util.BitSet;

public class ParserProblem implements Problem {

    private int startIndex;
    private int endIndex;
    private BitSet ambigAlts;
    private ATNConfigSet configs;

    public ParserProblem(int startIndex, int endIndex, BitSet ambigAlts, ATNConfigSet configs) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.ambigAlts = ambigAlts;
        this.configs = configs;
    }
}
