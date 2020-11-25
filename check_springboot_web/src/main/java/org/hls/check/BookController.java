package org.hls.check;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;
import java.util.Arrays;
import java.util.Collection;

@RestController
@RequestMapping("/api/book")
public class BookController {

    static class Book {
        Book() {

        }

        public String getName() {
            return "book-name";
        }
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable long id) {
        return new Book();
    }

    @GetMapping("/")
    public Collection<Book> findBooks() {
        return Arrays.asList(new Book());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book updateBook(
        @PathVariable("id") final String id, @RequestBody final Book book) {
        return book;
    }
}