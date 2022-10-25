package org.example;

import org.glassfish.hk2.api.IterableProvider;
import org.jvnet.hk2.annotations.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.LinkedList;

@Service
public class Library {
    @Inject
    private IterableProvider<Book> allBooks;

    public Library() {

    }

    @PostConstruct
    private void postConstruct() {

    }

    public LinkedList<Book> getAllBooks() {
        LinkedList<Book> retVal = new LinkedList<Book>();

        for (Book book : allBooks) {
            retVal.add(book);
        }

        return retVal;
    }
}
