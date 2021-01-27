package org.hls.check;

import jext.util.Bag;
import jext.util.Indexer;
import jext.util.Pair;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

public class App {

    private static List<String[]> sentences() {

        List<String[]> sentences = new ArrayList<>();

        try (LineNumberReader r = new LineNumberReader(new FileReader("bt_type_names.csv"))) {
            for(String line = r.readLine(); line != null; line = r.readLine()) {
                // line = line.trim();
                String name = line.substring(1, line.length() - 1);
                String[] tokens = name.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
                // List<String> tokens = Arrays.asList(name.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])"));
                // print(tokens);
                sentences.add(tokens);
            }
        }
        catch (Exception t) {
            t.printStackTrace();
        }

        return sentences;
    }

    private static void backward() {

        int maxlen = 0;
        String[] maxtoks = null;
        try (LineNumberReader r = new LineNumberReader(new FileReader("bt_type_names.csv"))) {
            for(String line = r.readLine(); line != null; line = r.readLine()) {
                // line = line.trim();
                String name = line.substring(1, line.length() - 1);
                String[] tokens = name.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
                invert(tokens);
                // List<String> tokens = Arrays.asList(name.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])"));
                print(tokens);
                if (tokens.length > maxlen) {
                    maxlen = tokens.length;
                    maxtoks = tokens;
                }
            }
            System.out.println("----------");
            System.out.printf("Max length: %d\n", maxlen);
            print(maxtoks);
        }
        catch (Exception t) {
            t.printStackTrace();
        }
    }

    private static void forward() {
        int maxlen = 0;
        String[] maxtoks = null;
        try (LineNumberReader r = new LineNumberReader(new FileReader("bt_type_names.csv"))) {
            for(String line = r.readLine(); line != null; line = r.readLine()) {
                // line = line.trim();
                String name = line.substring(1, line.length() - 1);
                String[] tokens = name.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
                // List<String> tokens = Arrays.asList(name.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])"));
                print(tokens);
                if (tokens.length > maxlen) {
                    maxlen = tokens.length;
                    maxtoks = tokens;
                }
            }
            System.out.println("----------");
            System.out.printf("Max length: %d\n", maxlen);
            print(maxtoks);
        }
        catch (Exception t) {
            t.printStackTrace();
        }
    }

    private static Bag<String> statistics() {
        Bag<String> bag = new Bag<>();

        try (LineNumberReader r = new LineNumberReader(new FileReader("bt_type_names.csv"))) {
            for(String line = r.readLine(); line != null; line = r.readLine()) {
                // line = line.trim();
                String name = line.substring(1, line.length() - 1);
                String[] tokens = name.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
                // List<String> tokens = Arrays.asList(name.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])"));
                // print(tokens);

                for(String token : tokens)
                    bag.add(token);
            }
            print(bag.items());
            System.out.println("----------");
            System.out.printf("Max count: %d\n", bag.maxCount());
        }
        catch (Exception t) {
            t.printStackTrace();
        }

        return bag;
    }

    private static void apriori() {

    }

    private static void tocsv(List<String[]> sentences) {
        Indexer<String> idx = new Indexer<>();

        for(String[] tokens : sentences)
            for(String token : tokens)
                idx.add(token);

        // print header

        System.out.println(idx.index("ciccio"));
    }

    public static void main(String[] args) {
        // forward();
        // backward();
        // statistics();
        // apriori();
        List<String[]> s = sentences();
        tocsv(s);
    }

    private static void invert(String[] tokens) {
        int i=0;
        int j=tokens.length-1;
        while(i < j) {
            String t = tokens[i];
            tokens[i] = tokens[j];
            tokens[j] = t;
            i++;
            j--;
        }
    }

    private static void print(String[] tokens) {
        System.out.print(tokens[0]);
        for(int i=1; i<tokens.length; ++i) {
            System.out.print(", ");
            System.out.print(tokens[i]);
        }
        System.out.println();
    }

    private static void print(Pair<String, Integer>[] tokens) {
        for(int i=0; i<tokens.length; ++i) {
            System.out.printf("%s, %d\n", tokens[i].getKey(), tokens[i].getValue());
        }
        System.out.println();
    }
}
