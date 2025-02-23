package org.hls.examples;

import jext.optim.heuristics.pbil.ProbUtils;

import java.util.Random;

public class Check5 {

    public static void main(String[] args) {
        Random rnd = new Random();
        double[] probs = new double[]{1,2,3,4,5};
        ProbUtils.normalize(probs);
        System.out.println(ProbUtils.sum(probs));

        int member = ProbUtils.nextMember(rnd, probs);

        ProbUtils.improveMember(1, probs, 0);
        System.out.println(ProbUtils.sum(probs));

        ProbUtils.improveMember(1, probs, .5);
        System.out.println(ProbUtils.sum(probs));

        ProbUtils.improveMember(1, probs, 1);
        System.out.println(ProbUtils.sum(probs));
    }
}
