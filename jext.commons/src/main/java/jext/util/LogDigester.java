package jext.util;

import jext.util.logging.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class simulates the behavior of Apache Commons Digester.
 * The idea is this:
 * The standard output/error in intercepted using a pipe.
 * The it is split in lines and each line is compared with a list of
 * regular expressions. When a RE match the text, it is executed the
 * registered action.
 *
 * In addition to this paradigm, it is added a state machine:
 * 1) the rules are registered under a specific state
 * 2) each rule can, or not, change the state
 */
public class LogDigester {

    private static Logger logger = Logger.getLogger(LogDigester.class);

    public interface Rule {
        /**
         *
         * @param state
         * @param matcher
         * @param line
         * @return new state, or null
         */
        int call(int state, Matcher matcher, String line);
    }

    private static class NextState implements Rule {

        private int nextState;

        NextState(int nextState) {
            this.nextState = nextState;
        }

        @Override
        public int call(int state, Matcher matcher, String line) {
            return nextState;
        }
    }

    public static final int STATE_BEGIN = -1;
    public static final int STATE_DONE = -2;
    public static final int KEEP_STATE = 0;

    private int state = STATE_BEGIN;

    private static class RuleMatcher {
        private Pattern pattern;
        private Rule consumer;

        public RuleMatcher(String regexp, Rule consumer) {
            this.pattern = Pattern.compile(regexp);
            this.consumer = consumer;
        }
    }

    // [state, List[rules]]
    private Map<Integer, List<RuleMatcher>> matchers = new HashMap<>();

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public LogDigester() {

    }

    // ----------------------------------------------------------------------

    public LogDigester addRule(String regexp, int nextState) {
        return addRule(STATE_BEGIN, regexp, nextState);
    }

    public LogDigester addRule(int state, String regexp, int nextState) {
        return addRule(state, regexp, new NextState(nextState));
    }

    // ----------------------------------------------------------------------

    public LogDigester addRule(String regexp, Rule consumer) {
        return addRule(STATE_BEGIN, regexp, consumer);
    }

    public LogDigester addRule(int state, String regexp, Rule consumer) {
        if (!matchers.containsKey(state))
            matchers.put(state, new ArrayList<>());
        matchers.get(state).add(new RuleMatcher(regexp, consumer));
        return this;
    }

    // ----------------------------------------------------------------------

    public void consume(String line) {
        if (STATE_DONE == state)
            return;

        if (!matchers.containsKey(state)) {
            logger.errorf("Unknown state %s", state);
            matchers.put(state, new ArrayList<>());
        }

        for(RuleMatcher rm : matchers.get(state)) {
            Matcher matcher = rm.pattern.matcher(line);
            if (matcher.matches()) {
                int newState = rm.consumer.call(state, matcher, line);
                if (newState != 0)
                    state = newState;
                break;
            }
        }
    }
}
