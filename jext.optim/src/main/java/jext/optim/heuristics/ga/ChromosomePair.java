package jext.optim.heuristics.ga;

public class ChromosomePair<T> {

    public static <T> ChromosomePair<T> of(T first, T second, final Chromosome<T> template) {
        Chromosome<T> c1 = new Chromosome<>(first, template);
        Chromosome<T> c2 = new Chromosome<>(second, template);
        return new ChromosomePair<>(c1, c2);
    }

    // public static <T> ChromosomePair<T> of(Chromosome<T> first, Chromosome<T> second) {
    //     return new ChromosomePair<>(first, second);
    // }

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

}
