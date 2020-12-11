package org.hls.check.web;

import org.hls.check.data.BookEntity;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class BookAssembler extends RepresentationModelAssemblerSupport<BookEntity, BookModel> {

    public BookAssembler() {
        super(BooksController.class, BookModel.class);
    }

    @Override
    public BookModel toModel(BookEntity entity) {
        BookModel model = new BookModel(entity);

        Link self = linkTo(
                methodOn(BooksController.class)
                        .getBook(model.getId())).withSelfRel();
        model.add(self);
        return model;
    }
}
