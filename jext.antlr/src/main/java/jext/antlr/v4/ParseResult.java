package jext.antlr.v4;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class ParseResult<T> {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public T result;
    public Exception exception;
    public List<Problem> problems = new ArrayList<>();

    public static final String SYSTEM_EOL = "\n";

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ParseResult() {

    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public boolean isSuccessful() {
        return result != null;
    }

    public void ifSuccessful(Consumer<T> consumer) {
        if (isSuccessful()) {
            consumer.accept(result);
        }
    }

    public Optional<T> getResult() {
        return Optional.ofNullable(result);
    }

    public List<Problem> getProblems() {
        return problems;
    }

    // ----------------------------------------------------------------------
    // Problems
    // ----------------------------------------------------------------------

    public void addProblem(Problem problem) {
        problems.add(problem);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public String toString() {
        if (isSuccessful()) {
            return "Parsing successful";
        }
        StringBuilder message = new StringBuilder("Parsing failed:").append(SYSTEM_EOL);
        for (Problem problem : problems) {
            message.append(problem.toString()).append(SYSTEM_EOL);
        }
        return message.toString();
    }
}
