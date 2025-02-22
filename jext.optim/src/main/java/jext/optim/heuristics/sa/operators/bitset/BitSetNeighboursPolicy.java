package jext.optim.heuristics.sa.operators.bitset;

import jext.optim.domain.bitset.BitSet;
import jext.optim.domain.bitset.BitSetNeighbours;
import jext.optim.heuristics.sa.NeighboursPolicy;
import jext.optim.heuristics.sa.Particle;

import java.util.List;
import java.util.random.RandomGenerator;

public class BitSetNeighboursPolicy implements NeighboursPolicy<BitSet> {

    private final BitSetNeighbours bsn = new BitSetNeighbours();

    @Override
    public List<Particle<BitSet>> neighbours(Particle<BitSet> particle, int n, RandomGenerator rng) {
        BitSet bs = particle.candidate();
        List<BitSet> neighbours = bsn.neighbours(bs, n, rng);
        return neighbours.stream().map(nbs -> new Particle<>(nbs, particle)).toList();
    }
}
