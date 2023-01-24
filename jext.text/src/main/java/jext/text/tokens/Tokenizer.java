package jext.text.tokens;

import java.util.ArrayList;
import java.util.List;
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

        tokens = normalize(tokens);
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

    // ensures that there are no tokens of length 1
    private static String[] normalize(String[] array){
        List<String> newArray = new ArrayList<>();
        for(int i=0;i<array.length;i++){
            String temp = array[i];
            if(temp.length() == 1){
                if(i+1< array.length){
                    temp+=array[i+1];
                    i++;
                }
            }
            newArray.add(temp);
        }
        return newArray.toArray(new String[0]);
    }
}
