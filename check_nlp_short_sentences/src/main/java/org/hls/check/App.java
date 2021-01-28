package org.hls.check;

import ca.pfv.spmf.algorithms.frequentpatterns.apriori.AlgoApriori;
import ca.pfv.spmf.patterns.itemset_array_integers_with_count.Itemset;
import ca.pfv.spmf.patterns.itemset_array_integers_with_count.Itemsets;
import jext.util.Bag;
import jext.util.Indexer;
import jext.util.Pair;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.IdentityHashMap;
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

    private static Indexer<String> indicize(List<String[]> sentences) {
        Indexer<String> idx = new Indexer<>();
        for(String[] tokens : sentences)
            for(String tok : tokens)
                idx.add(tok);
        return idx;
    }

    private static void apriori() {
        List<String[]> sentences = sentences();
        Indexer<String> idx = indicize(sentences);
        printap(sentences, idx);
        checkapriori(idx);
    }

    private static void printap(List<String[]> sentences, Indexer<String> idx) {
        try (PrintStream out = new PrintStream(new FileOutputStream("bt_type_apriori.csv"))) {
            for(String[] tokens : sentences) {
                for (String token : tokens) {
                    out.print(idx.get(token));
                    out.print(" ");
                }
                out.println();
            }
        }
        catch(Throwable t) {

        }

    }

    public static void checkapriori(Indexer<String> idx) {
        AlgoApriori ap = new AlgoApriori();
        try {
            Itemsets itemsets = ap.runAlgorithm(25./2515, "bt_type_apriori.csv", null);
            for(List<Itemset> litemset : itemsets.getLevels()) {
                for(Itemset itemset : litemset) {
                    String[] tokens = idx.items(itemset.getItems(), String.class);

                    print(tokens, itemset.getAbsoluteSupport());
                }
            }
        } catch (IOException e) {

        }
    }

    // private static void tocsv(List<String[]> sentences) {
    //
    //     try (PrintStream w = new PrintStream(new FileOutputStream("bt_type_apriori.csv"))) {
    //         Indexer<String> idx = new Indexer<>();
    //
    //         for(String[] tokens : sentences)
    //             for(String token : tokens)
    //                 idx.add(token);
    //
    //         printh(w, idx.items());
    //
    //         for (String[] s : sentences)
    //             prints(w, s, idx);
    //
    //     }
    //     catch(Throwable t) {
    //
    //     }
    //
    // }

    // private static void printh(PrintStream out, List<String> header) {
    //     // print header
    //     boolean first = true;
    //     for(String h : header) {
    //         if (!first) out.print(",");
    //         out.print(h);
    //         first = false;
    //     }
    //     out.println();
    // }

    // private static void prints(PrintStream out, String[] tokens, Indexer<String> idx) {
    //     int n = idx.size();
    //     int[] flags = new int[n];
    //     for (String token : tokens) {
    //         int tok = idx.index(token);
    //         flags[tok] = 1;
    //     }
    //
    //     boolean first = true;
    //     for (int i=0; i<n; ++i) {
    //         if (!first) out.print(",");
    //         out.print(flags[i]);
    //         first = false;
    //     }
    //     out.println();
    // }

    private static void countLengths() {
        List<String[]> sentences = sentences();
        Bag<Integer> sizes = new Bag<>();

        for (String[] tokens : sentences)
            sizes.add(tokens.length);

        for (Pair<Integer, Integer> p : sizes.items())
            System.out.printf("%s -> %s\n", p.getKey(), p.getValue());
    }

    public static void main(String[] args) {
        // forward();
        // backward();
        // statistics();
        apriori();
        // countLengths();
        // List<String[]> s = sentences();
        // tocsv(s);
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

    private static void print(String[] tokens, int count) {
        System.out.print(tokens[0]);
        for(int i=1; i<tokens.length; ++i) {
            System.out.print(", ");
            System.out.print(tokens[i]);
        }
        System.out.printf("\t: %d\n", count);
    }

    private static void print(Pair<String, Integer>[] tokens) {
        for(int i=0; i<tokens.length; ++i) {
            System.out.printf("%s, %d\n", tokens[i].getKey(), tokens[i].getValue());
        }
        System.out.println();
    }
}
