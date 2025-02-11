package jext.optim.heuristics.genetics;

public class ChromosomePair<T> {

    private final Chromosome<T> first;
    private final Chromosome<T> second;

    public ChromosomePair(Chromosome<T> first, Chromosome<T> second) {
        this.first = first;
        this.second = second;
    }

    public Chromosome<T> first() {
        return first;
    }
    public Chromosome<T> second() {
        return second;
    }

    public static <T> ChromosomePair<T> makePair(T first, T second, final Chromosome<T> template) {
        Chromosome<T> c1 = new Chromosome<>(first, template);
        Chromosome<T> c2 = new Chromosome<>(second, template);
        return new ChromosomePair<>(c1, c2);
    }
}
