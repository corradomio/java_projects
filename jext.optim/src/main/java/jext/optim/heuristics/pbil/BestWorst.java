package jext.optim.heuristics.pbil;

public class BestWorst<T> {
    public Learner<T> best = null;
    public Learner<T> worst = null;

    public BestWorst(Learner<T> initLearner) {
        this.best = initLearner;
        this.worst = initLearner;
    }

    public void update(Learner<T> learner) {
        if (learner.compareTo(best) < 0)
            best = learner;
        if (learner.compareTo(worst) > 0)
            worst = learner;
    }
}
