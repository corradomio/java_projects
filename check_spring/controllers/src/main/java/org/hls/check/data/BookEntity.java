package org.hls.check.data;

import lombok.Data;

@Data
public class BookEntity {

    static int gid = 0;

    int id;
    String title;

    public BookEntity() {
        this(++gid);
    }

    public BookEntity(int id) {
        this.id = id;
        title = String.format("Title %d", id);
    }
}
