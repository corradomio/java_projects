package org.hls.check;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class LoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {

        //String loggerName = "org.ebtic.bt.iplan.iplan10.controller.ServerRestLoggerFilter" + request.getRequestURI().replace("/", ".");
        String loggerName = "org.hls.check.LoggingFilter" + request.getRequestURI().replace("/", ".");
        Logger logger = LoggerFactory.getLogger(loggerName);

        // Wrap the request and response to cache their content
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        // minimum level: DEBUG
        if (!logger.isInfoEnabled()) {
            responseWrapper.copyBodyToResponse();
            return;
        }

        // try
        {
            // Log the request details
            logRequest(logger, requestWrapper);

            // Proceed with the request
            filterChain.doFilter(requestWrapper, responseWrapper);

        }
        // finally
        {
            // Log the response details
            logResponse(logger, responseWrapper);

            // IMPORTANT: copy the response body to the original response
            responseWrapper.copyBodyToResponse();
        }

    }

    private void logRequest(Logger logger, ContentCachingRequestWrapper request) throws IOException {
        // byte[] contentAsByteArray = request.getContentAsByteArray();
        // String requestBody = new String(contentAsByteArray, request.getCharacterEncoding());

        Map json = JSONUtils.parse(request.getInputStream(), HashMap.class);
        String requestBody = JSONUtils.serialize(json);

        logger.debug("\nRequest: Method={}, URI={}\nBody={}\n\n", request.getMethod(), request.getRequestURI(), requestBody);
    }

    private void logResponse(Logger logger, ContentCachingResponseWrapper response) throws IOException {
        // byte[] contentAsByteArray = response.getContentAsByteArray();
        // String responseBody = new String(contentAsByteArray, response.getCharacterEncoding());

        Map json = JSONUtils.parse(response.getContentInputStream(), HashMap.class);
        String responseBody = JSONUtils.serialize(json);

        logger.debug("\nResponse: Status={}\nBody={}\n\n", response.getStatus(), responseBody);
    }

}
