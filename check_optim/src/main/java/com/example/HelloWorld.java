package com.example;

import io.jenetics.BitChromosome;
import io.jenetics.BitGene;
import io.jenetics.Genotype;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;
import io.jenetics.util.Factory;

public class HelloWorld {

    private static int eval(final Genotype<BitGene> gt) {
        return gt.chromosome()
            .as(BitChromosome.class)
            .bitCount();
    }

    public static void main(final String[] args) {
        final Factory<Genotype<BitGene>> gtf =
            Genotype.of(BitChromosome.of(10, 0.5));

        final Engine<BitGene, Integer> engine = Engine
            .builder(HelloWorld::eval, gtf)
            .build();

        final Genotype<BitGene> result = engine.stream()
            .limit(100)
            .collect(EvolutionResult.toBestGenotype());

        System.out.println("HelloWorld:\n\t" + result);
    }
}
