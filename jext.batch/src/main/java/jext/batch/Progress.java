package jext.batch;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class Progress {

    private static Task[] NO_TASKS = new Task[0];
    private static Step[] NO_STEPS = new Step[0];

    public static class ToDo<T> implements Iterator<T> {
        T[] todo;
        int current;

        public int getCurrent() { return current; }
        public int getTotal(){ return todo != null ? todo.length : 0; }
        public double getDone() {
            double total = getTotal();
            return total != 0. ? current/total : 0.;
        }

        Optional<T> get() { return todo == null || todo.length == 0
                ? Optional.empty()
                : Optional.of(todo[current]);  }

        @Override
        public boolean hasNext() { return getCurrent() < getTotal(); }

        @Override
        public T next() { return todo[current++]; }
    }

    private ToDo<Task> tasks = new ToDo<>();
    private ToDo<Step> steps = new ToDo<>();

    public void setTasks(List<Task> tasks) {
        this.tasks.todo = tasks.toArray(NO_TASKS);
    }

    public void setSteps(List<Step> steps) {
        this.steps.todo = steps.toArray(NO_STEPS);
    }

    public ToDo<Task> tasks() {
        return tasks;
    }

    public ToDo<Step> steps() {
        return steps;
    }

    public Optional<Step> get() {
        return steps.get();
    }
}
