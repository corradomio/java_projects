package org.hls.check.web;

import jext.springframework.data.domain.Pageable;
import jext.springframework.data.web.PagedResourcesAssembler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hls.check.data.BookEntity;
import org.hls.check.data.BookRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping(value = "/b")
public class BooksController {

    @Autowired private BookAssembler bookAssembler;
    @Autowired private BookRepostory bookRepository;

    private Logger logger = LogManager.getLogger(BooksController.class);

    public BooksController() {
        logger.info("new");
    }

    @GetMapping("v")
    public Map getVersion() {
        return new HashMap() {{
            put("version", "v1.0");
        }};
    }

    @GetMapping("{id}")
    public ResponseEntity<BookModel> getBook(@PathVariable("id") int id) {
        BookEntity entity = bookRepository.findById(id);
        BookModel  model  = bookAssembler.toModel(entity);
        return ResponseEntity.ok(model);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<BookModel>> getBooks(Pageable pageable) {

        if (pageable.isPaged()) {
            Page<BookEntity> page = bookRepository.findAll(pageable);
            // PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(page.getSize(), page.getNumber(), 100);

            Link link = linkTo(methodOn(BooksController.class).getBooks(pageable)).withSelfRel();

            PagedResourcesAssembler<BookEntity, BookModel> pra = new PagedResourcesAssembler<>(bookAssembler);
            return ResponseEntity.ok(pra.toModel(page, link));
        }
        else {
            List<BookEntity> content = bookRepository.findAll();
            return ResponseEntity.ok(bookAssembler.toCollectionModel(content));
        }
    }
}
