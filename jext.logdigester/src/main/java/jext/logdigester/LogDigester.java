package jext.logdigester;

import jext.util.logging.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String call(String state, Matcher matcher, String line);
    }

    private static class NextState implements Rule {

        private String nextState;

        NextState(String nextState) {
            this.nextState = nextState;
        }

        @Override
        public String call(String state, Matcher matcher, String line) {
            return nextState;
        }
    }

    public static final String STATE_BEGIN = "";
    public static final String STATE_DONE = "$DONE";

    private String state = STATE_BEGIN;

    private static class RuleMatcher {
        private Pattern pattern;
        private Rule consumer;

        public RuleMatcher(String regexp, Rule consumer) {
            this.pattern = Pattern.compile(regexp);
            this.consumer = consumer;
        }
    }

    private Map<String, List<RuleMatcher>> matchers = new HashMap<>();

    public LogDigester() {

    }

    // ----------------------------------------------------------------------

    public LogDigester addRule(String regexp, String nextState) {
        return addRule(STATE_BEGIN, regexp, nextState);
    }

    public LogDigester addRule(String state, String regexp, String nextState) {
        return addRule(state, regexp, new NextState(nextState));
    }

    // ----------------------------------------------------------------------

    public LogDigester addRule(String regexp, Rule consumer) {
        return addRule(STATE_BEGIN, regexp, consumer);
    }

    public LogDigester addRule(String state, String regexp, Rule consumer) {
        if (!matchers.containsKey(state))
            matchers.put(state, new ArrayList<>());
        matchers.get(state).add(new RuleMatcher(regexp, consumer));
        return this;
    }

    // ----------------------------------------------------------------------

    public void consume(String line) {
        if (STATE_DONE.equals(state))
            return;

        if (!matchers.containsKey(state)) {
            logger.errorf("Used an unknown state with NO rules", state);
            matchers.put(state, new ArrayList<>());
        }

        for(RuleMatcher rm : matchers.get(state)) {
            Matcher matcher = rm.pattern.matcher(line);
            if (matcher.matches()) {
                String newState = rm.consumer.call(state, matcher, line);
                if (newState != null)
                    state = newState;
                break;
            }
        }
    }
}
