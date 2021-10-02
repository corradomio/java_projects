package org.hls.check;

import jext.jgrapht.Graphs;
import jext.jgrapht.alg.closure.Closure;
import jext.jgrapht.alg.closure.ClosuresGraph;
import jext.jgrapht.generate.RandomCavemanGraphGenerator;
import jext.jgrapht.util.Utils;
import jext.logging.Logger;
import jext.util.Assert;
import jext.util.concurrent.Parallel;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class CheckClosures {

    public static void main(String[] args) {
        Logger.configure();

        Random r = new Random(1000);

        RandomCavemanGraphGenerator<Integer, DefaultEdge> rcgg = new RandomCavemanGraphGenerator<>(
            1000,       // v
            10000,       // e
        10,     // c
            .001,
            .9
        );

        Graph<Integer, DefaultEdge> g = Graphs.newGraph(Integer.class, DefaultEdge.class, true);

        rcgg.generateGraph(g, null);
        Graphs.describe(g);

        ClosuresGraph<Integer, DefaultEdge> cg = new ClosuresGraph<>(g);
        Map<Integer, Closure<Integer>> slowClosures;
        Map<Integer, Closure<Integer>> fastClosures;

        {
            System.out.println("start (slow) ...");
            long start = System.currentTimeMillis();
            cg.computeClosures();
            System.out.printf("done in %ds \n", (System.currentTimeMillis()-start)/1000);
            slowClosures = cg.getClosures();
        }
        {
            System.out.println("start (fast) ...");
            long start = System.currentTimeMillis();
            cg.computeClosures();
            System.out.printf("done in %ds \n", (System.currentTimeMillis()-start)/1000);
            fastClosures = cg.getClosures();
        }
        {
            compareClosures(slowClosures, fastClosures);
        }

        Parallel.shutdown();
    }

    private static void compareClosures(Map<Integer, Closure<Integer>> slow, Map<Integer, Closure<Integer>> fast) {
        Assert.check(slow.size() == fast.size(), "closure size");

        for (Integer k : slow.keySet()) {
            Set<Integer> sc = slow.get(k).members();
            Set<Integer> fc = fast.get(k).members();

            Assert.check(sc.equals(fc), "closure: " + sc.toString() + " <> " + fc.toString());
        }

        System.out.println("OK");
    }
}
