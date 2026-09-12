package org.hls.check;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InternalServerError {

    public static ResponseEntity<Map> response(HttpServletRequest request, Throwable e){
        Map einfo = of(request, e);
        return new ResponseEntity<>(einfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static Map of(HttpServletRequest request, Throwable e){
        Map einfo = MapUtils.asLinkedMap(
            "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "error", HttpStatus.INTERNAL_SERVER_ERROR,
            "uri", request.getRequestURI(),
            "url", request.getRequestURL(),
            "context-path", request.getContextPath(),
            "message", e.getMessage(),
            "timestamp", new Date(),
            "causes", innerErrorInfo(e)
        );

        return einfo;
    }

    private static List<Map> innerErrorInfo(Throwable e){
        List<Map> iex = new ArrayList<>();
        Throwable cause = e;
        while (cause != null) {
            iex.add(errorInfo(cause));
            cause = cause.getCause();
        }

        return iex;
    }

    private static Map errorInfo(Throwable e){
        Map errorInfo =  MapUtils.asLinkedMap(
            "message", e.getMessage(),
            "type", e.getClass().getTypeName(),
            "callstack", stacktrace(e)
        );
        return errorInfo;
    }

    private static String[] IGNORE_NAMESPACES = {
        "jdk.",
        "java.",
        "javax.",
        "org.apache.",
        "org.springframework."
    };

    private static boolean isIgnored(StackTraceElement ste) {
        String cn =  ste.getClassName();
        for (String ins : IGNORE_NAMESPACES)
            if (cn.startsWith(ins))
                return true;
        return false;
    }

    private static List<String> stacktrace(Throwable e){
        List<String> stacktrace = new ArrayList<>();
        for (StackTraceElement ste : e.getStackTrace()) {
            if (!isIgnored(ste))
                stacktrace.add(ste.toString());
        }
        return stacktrace;
    }

}
