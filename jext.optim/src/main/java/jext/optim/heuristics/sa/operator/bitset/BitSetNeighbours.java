package jext.optim.heuristics.sa.operator.bitset;

import jext.optim.domain.bitset.BitSet;
import jext.optim.heuristics.sa.NeighboursPolicy;
import jext.optim.heuristics.sa.Particle;

import java.util.List;
import java.util.random.RandomGenerator;

public class BitSetNeighbours implements NeighboursPolicy<BitSet> {

    private final jext.optim.domain.bitset.BitSetNeighbours bsn = new jext.optim.domain.bitset.BitSetNeighbours();

    @Override
    public List<Particle<BitSet>> neighbours(Particle<BitSet> particle, int n, RandomGenerator rng) {
        BitSet bs = particle.candidate();
        List<BitSet> neighbours = bsn.neighbours(bs, n, rng);
        return neighbours.stream().map(nbs -> new Particle<>(nbs, particle)).toList();
    }
}
