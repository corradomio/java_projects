package org.hls.spring.logging;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class LoggerController {

    private final DefaultErrorAttributes defaultErrorAttributes;

    public LoggerController(DefaultErrorAttributes defaultErrorAttributes) {
        Logger logger = LoggerFactory.getLogger(LoggerController.class);
        logger.info("Initialized");
        this.defaultErrorAttributes = defaultErrorAttributes;
    }

    @GetMapping(value="/log", produces = "application/json")
    public ResponseEntity<?> logByGet(
        @RequestParam(value="message") String message,
        @RequestParam(value="level", defaultValue="INFO") String level,
        @RequestParam(value="name", defaultValue = "web") String name
    ) {
        Logger logger = LoggerFactory.getLogger(name);

        if ("DEBUG".equalsIgnoreCase(level))
            logger.debug(message);
        if ("INFO".equalsIgnoreCase(level))
            logger.info(message);
        if ("WARN".equalsIgnoreCase(level))
            logger.warn(message);
        if ("ERROR".equalsIgnoreCase(level))
            logger.error(message);
        if ("FATAL".equalsIgnoreCase(level))
            logger.error(message);

        return ResponseEntity.ok(MapUtils.asMap(
            "level", level,
            "name", name)
        );
    }

    @PostMapping(value="/log", consumes="application/json", produces="application/json")
    public ResponseEntity<?> log(HttpServletRequest request, @RequestBody Map<String, Object> payload) {
        String name = MapUtils.getDefault(payload, "web", "name");
        String level = MapUtils.getDefault(payload, "info", "level");
        String message = MapUtils.get(payload, "message");

        Logger logger = LoggerFactory.getLogger(name);

        if ("DEBUG".equalsIgnoreCase(level))
            logger.debug(message);
        if ("INFO".equalsIgnoreCase(level))
            logger.info(message);
        if ("WARN".equalsIgnoreCase(level))
            logger.warn(message);
        if ("ERROR".equalsIgnoreCase(level))
            logger.error(message);
        if ("FATAL".equalsIgnoreCase(level))
            logger.error(message);

        return ResponseEntity.ok(MapUtils.asMap(
            "level", level,
            "name", name)
        );
    }

}
