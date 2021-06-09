package jext.text;

import java.util.regex.Pattern;

public class Tokenizer {

    private static final String[] NO_TOKENS = new String[0];
    private static final Pattern TOKENIZER = Pattern.compile("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");


    public static String[] split(String text) {
        return tokenize(text, false);
    }

    public static String[] tokenize(String text) {
        return tokenize(text, false);
    }

    public static String[] tokenize(String text, boolean reverse) {
        if (text == null || text.length() == 0)
            return NO_TOKENS;

        String[] tokens = TOKENIZER.split(text);

        //
        // it is not necessary because text IS NOT A FULL qualified name
        //
        // for (int i=0; i<tokens.length; ++i) {
        //     String token = tokens[i];
        //     if (token.endsWith("."))
        //         tokens[i] = token.substring(0, token.length()-1);
        // }

        if (reverse) {
            int i = 0;
            int j = tokens.length - 1;

            while (i < j) {
                String t = tokens[i];
                tokens[i] = tokens[j];
                tokens[j] = t;
                i++;
                j--;
            }
        }
        return tokens;
    }
}
