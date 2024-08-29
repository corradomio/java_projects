package com.example;

import jext.antlr.v4.regexp.regexLexer;
import jext.antlr.v4.regexp.regexParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        String re = "[0-9]{4}-[0-9]{2}-[0-9]{2}";

        ANTLRInputStream is = new ANTLRInputStream(re);
        regexLexer lexer = new regexLexer(is);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        regexParser parser = new regexParser(tokens);

        ParseTree tree = parser.root();

        System.out.println(tree.toStringTree(parser));

    }
}