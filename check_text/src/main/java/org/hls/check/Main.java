package org.hls.check;

import jext.text.TokensIterator;
import jext.text.TokensPredicate;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.function.Predicate;

public class Main {

    public static void main1(String[] args) {

        Predicate<List<String>> p = TokensPredicate.parse("a*");

        p = TokensPredicate.parse("A&B");

    }

    public static void main(String[] args) throws IOException {
        try(Reader rdr = new FileReader("D:\\Projects.github\\other_projects\\jgrapht\\jgrapht-ext\\src\\main\\java\\org\\jgrapht\\ext\\JGraphXAdapter.java")) {
            TokensIterator tokit = new TokensIterator(rdr);

            while(tokit.hasNext())
                System.out.println(tokit.next());
        }
    }
}
