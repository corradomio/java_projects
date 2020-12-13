package org.hls.check;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;

@RestController
@RequestMapping("/api/book")
public class BookController {

    static class Book {

        private long id;
        Book(long id) {
            this.id = id;
        }

        public Long getId() { return id; }
        public String getName() {
            return "book-name";
        }
    }

    @GetMapping("/{id}")
    public EntityModel<Book> findById(@PathVariable long id) {
        return EntityModel.of(new Book(id));
    }

    @GetMapping("/")
    public CollectionModel<EntityModel<Book>> findBooks() {
        return CollectionModel.of(Arrays.asList(EntityModel.of(new Book(101))));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<Book> updateBook(
        @PathVariable("id") final Long id, @RequestBody final Book book) {
        return EntityModel.of(book);
    }
}