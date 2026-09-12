package org.hls.check;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    // public Throwable handleCustomException(HttpServletRequest request, Throwable ce) {
    //     return ce;
    // }
    public ResponseEntity handleCustomException(HttpServletRequest request, Throwable ce) {
        return InternalServerError.response(request, ce);
    }

}
