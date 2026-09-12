package org.hls.check;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Map;

@RestControllerAdvice
public class GlobalErrorController {

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map handleCustomNotFoundException(HttpServletRequest request, Throwable ex) {
        Map problemDetail = InternalServerError.of(request, ex);
        return problemDetail;
    }
}
