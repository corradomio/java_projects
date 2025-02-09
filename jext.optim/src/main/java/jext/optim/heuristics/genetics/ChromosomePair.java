package jext.optim.heuristics.genetics;

public class ChromosomePair<T> extends org.apache.commons.math4.legacy.genetics.ChromosomePair {

    public ChromosomePair(Chromosome<T> first, Chromosome<T> second) {
        super(first, second);
    }

    public Chromosome<T> getFirst() {
        return (Chromosome<T>) super.getFirst();
    }
    public Chromosome<T> getSecond() {
        return (Chromosome<T>) super.getSecond();
    }

    public static <T> ChromosomePair<T> makePair(T first, T second, final Chromosome<T> template) {
        Chromosome<T> c1 = new Chromosome<>(first, template);
        Chromosome<T> c2 = new Chromosome<>(second, template);
        return new ChromosomePair<>(c1, c2);
    }
}
