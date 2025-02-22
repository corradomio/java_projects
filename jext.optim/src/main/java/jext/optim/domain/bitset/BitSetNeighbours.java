package jext.optim.domain.bitset;

import jext.optim.domain.CandidateNeighbours;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

public class BitSetNeighbours implements CandidateNeighbours<BitSet> {

    @Override
    public List<BitSet> neighbours(BitSet candidate, int n, int distance, RandomGenerator rng) {
        List<BitSet> neighbours = new ArrayList<>();
        int nbits = candidate.length();
        for (int i = 0; i < n; i++) {
            BitSet bs = candidate.clone();

            for (int j = 0; j < n; j++) {
                int bitIndex = rng.nextInt(nbits);
                bs.flip(bitIndex);
            }

            neighbours.add(bs);
        }
        return neighbours;
    }

    @Override
    public List<BitSet> neighbours(BitSet candidate) {
        List<BitSet> neighbours = new ArrayList<>();
        int nbits = candidate.length();
        for (int bitIndex=0; bitIndex<nbits; bitIndex++) {
            BitSet bs = candidate.clone();
            bs.flip(bitIndex);
            neighbours.add(bs);
        }
        return neighbours;
    }
}
