// package org.hls.check;
//
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.RestControllerAdvice;
//
// import javax.servlet.http.HttpServletRequest;
//
// // @RestController
// @RestControllerAdvice
// public class ErrorController {
//
//     @ExceptionHandler(value = Error.class)
//     public ResponseEntity defaultErrorHandler(HttpServletRequest request, Error e) throws Exception {
//         // If the exception is annotated with @ResponseStatus rethrow it and let
//         // the framework handle it - like the OrderNotFoundException example
//         // at the start of this post.
//         // AnnotationUtils is a Spring Framework utility class.
//         return InternalServerError.response(request, e);
//     }
//     @ExceptionHandler(value = Exception.class)
//     public ResponseEntity defaultExceptionHandler(HttpServletRequest request, Exception e) throws Exception {
//         // If the exception is annotated with @ResponseStatus rethrow it and let
//         // the framework handle it - like the OrderNotFoundException example
//         // at the start of this post.
//         // AnnotationUtils is a Spring Framework utility class.
//         return InternalServerError.response(request, e);
//     }
// }
