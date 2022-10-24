package org.hls.check;

import javax.inject.Inject;
import java.util.LinkedList;

public class Library {
    @Inject
    private Iterable<Book> allBooks;

    public LinkedList<Book> getAllBooks() {
        LinkedList<Book> retVal = new LinkedList<Book>();

        for (Book book : allBooks) {
            retVal.add(book);
        }

        return retVal;
    }
}
