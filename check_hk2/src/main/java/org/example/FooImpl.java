package org.example;

import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;

@Service
public class FooImpl implements Foo {
    @Inject
    private Book book;

    @Override
    public Book getBook() {
        return book;
    }
}