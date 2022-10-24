package org.hls.check;

import javax.inject.Inject;

public class FooImpl implements Foo {
    @Inject
    private Book book;

    // public FooImpl() { }

    // @Inject
    // public FooImpl(Book book) {
    //     // constructor injected!
    //     this.book = book;
    // }

    // @Inject
    // public void setBook(Book book) {
    //     this.book = book;
    // }

    @Override
    public Book getBook() {
        return book;
    }
}