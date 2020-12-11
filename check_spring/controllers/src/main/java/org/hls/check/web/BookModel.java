package org.hls.check.web;

import org.hls.check.data.BookEntity;
import org.springframework.hateoas.RepresentationModel;

public class BookModel extends RepresentationModel<BookModel> {

    private BookEntity entity;

    public BookModel(BookEntity entity) {
        this.entity = entity;
    }

    public int getId() {
        return entity.getId();
    }

    public String getTitle() {
        return entity.getTitle();
    }
}
