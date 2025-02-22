package jext.optim.domain.partition;

import jext.optim.domain.CandidateNeighbours;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

public class PartitionNeighbours implements CandidateNeighbours<Partition> {

    @Override
    public List<Partition> neighbours(Partition candidate, int n, int distance, RandomGenerator rng) {
        List<Partition> neighbours = new ArrayList<Partition>();
        int length = candidate.length();
        int nparts = candidate.nparts();
        for (int i = 0; i < n; i++) {
            Partition partition = candidate.clone();

            for (int j = 0; j < n; j++) {
                int elt = rng.nextInt(length);
                int part = rng.nextInt(nparts);
                partition.set(elt, part);
            }

            neighbours.add(partition);
        }
        return neighbours;
    }

    @Override
    public List<Partition> neighbours(Partition candidate) {
        List<Partition> neighbours = new ArrayList<>();
        int length = candidate.length();
        int nparts = candidate.nparts();

        for (int i = 0; i < length; i++) {
            Partition partition = candidate.clone();
            int part = partition.get(i) + 1;
            if (part >= nparts) part = 0;
            partition.set(i, part);

            neighbours.add(partition);
        }

        return neighbours;
    }
}
