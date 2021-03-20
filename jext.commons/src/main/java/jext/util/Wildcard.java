package jext.util;

import java.util.regex.Pattern;

//  ?       [^/]
//  *       [^/]*
//  **      ([^/]*/)*
//  *   **  **/..
//  **/...  .../**  .../**/...
//
//  Regexp special chars    \ ^ $ . | ? * + ( ) [ ] { }
//
//  Special patterns: file extension, starting with a '.' but not containing
//      a meta character ('*', '?', '/')
//
//      .java  ->  *.java
//
//
public class Wildcard {

    private static final String RESERVED = "\\^$.|?*+()[]{}";

    private String pattern;
    private Pattern compiled;


    public Wildcard(String pattern) {
        if (isExtension(pattern))
            pattern = "*" + pattern;

        this.pattern = pattern;
        compile();
    }

    public boolean accept(String text) {
        return compiled.matcher(text).matches();
    }

    private void compile() {
        StringBuilder regex = new StringBuilder();

        for (int i = 0; i< pattern.length(); ++i) {
            char ch = charAt(i);

            //  '.'
            if (ch == '.')
                regex.append("\\.");
            //  '?'
            else if (ch == '?')
                regex.append("[^/]");
            //  '/'
            else if (ch == '/') {
                //  /**/...
                //if (charAt(i+1) == '*' && charAt(i+2) == '*' && charAt(i+3) == '/') {
                if (match(i+1, "**/")) {
                    regex.append("([^/]*/)*");
                    i += 3;
                }
                //  /**
                //else if (charAt(i+1) == '*' && charAt(i+2) == '*' && charAt(i+3) == 0) {
                else if (match(i+1, "**\0")) {
                    regex.append("/.*");
                    i += 2;
                }
                //  /*
                //else if (charAt(i+1) == '*' && charAt(i+2) == 0) {
                else if (match(i+1, "*\0")) {
                    regex.append(".*");
                    i += 1;
                }
                //  /...
                else {
                    //regex.append("[/]");
                    regex.append("/");
                }
            }
            //  '*'
            else if (ch == '*') {
                //  **/...
                //if (charAt(i+1) == '*' && charAt(i+2) == '/') {
                if (match(i+1, "*/")) {
                    regex.append("([^/]*/)*");
                    i += 2;
                }
                //  **
                //else if (charAt(i+1) == '*' && charAt(i+2) == 0) {
                else if (match(i+1, "*\0")) {
                    regex.append(".*");
                    i += 1;
                }
                //  *
                else if (charAt(i+1) == 0) {
                    regex.append(".*");
                }
                //  *...
                else {
                    regex.append("[^/]*");
                }
            }
            //  'reserved chars
            else if (RESERVED.indexOf(ch) != -1) {
                regex.append("\\").append(ch);
            }
            //  'any char'
            else {
                regex.append(ch);
            }
        }

        this.compiled = Pattern.compile(regex.toString());
    }

    private boolean match(int i, String pat) {
        for (int j=0; j<pat.length(); ++j)
            if (charAt(i+j) != pat.charAt(j))
                return false;
        return true;
    }

    private char charAt(int i) {
        return (i<0 || i>= pattern.length()) ? 0 : pattern.charAt(i);
    }

    private static boolean isExtension(String pattern) {
        return pattern.startsWith(".") &&
            !pattern.contains("*") &&
            !pattern.contains("?") &&
            !pattern.contains("/");
    }
}
