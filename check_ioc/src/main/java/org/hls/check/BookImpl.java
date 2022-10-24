package org.hls.check;


public class BookImpl implements Book {
    @Override
    public String getName() {
        return BookImpl.class.getName();
    }
}
