package jext.exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExceptionUtils {

    public static String getMessages(Throwable t) {
        Set<String> messages = new HashSet<>();
        String message = t.getMessage();
        if (message == null)
            message = t.getClass().getName();
        StringBuilder sb = new StringBuilder(message);
        Throwable cause = t.getCause();
        messages.add(t.getMessage());

        Throwable prev = null;
        while (cause != null && cause != prev) {
            message = cause.getMessage();
            if (!messages.contains(message)) {
            sb.append("\n  [").append(messages.size()).append("] ")
                .append(message);
            messages.add(message);
            }
            prev = cause;
            cause = prev.getCause();
        }
        return sb.toString();
    }

    public static List<String> getCallstackAsString(Throwable t, int n) {
        StackTraceElement[] stelts = t.getStackTrace();
        if (stelts == null || stelts.length == 0)
            return Collections.emptyList();

        List<String> cs = new ArrayList<>();
        n = Math.min(n, stelts.length);
        for(int i=1; i<n; ++i)
            cs.add(stelts[i].toString());
        return cs;
    }
}
