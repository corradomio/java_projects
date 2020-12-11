package org.hls.check.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hls.check.data.BookEntity;
import org.hls.check.data.BookRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping("/test")
public class ExampleController {

    @Autowired private BookRepostory bookRepository;

    private Logger logger = LogManager.getLogger(ExampleController.class);

    public ExampleController() {
        logger.info("new");
    }

    @GetMapping("version")
    public Map getVersion() {
        return new HashMap() {{
            put("version", "v1.0");
        }};
    }

    @GetMapping("books/{id}")
    public ResponseEntity<BookModel> getBook(@PathVariable("id") int id) {

        BookEntity entity = bookRepository.findById(id);
        return ResponseEntity.ok()

    }
}
