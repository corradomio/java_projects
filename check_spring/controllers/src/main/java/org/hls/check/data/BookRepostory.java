package org.hls.check.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookRepostory {

    public List<BookEntity> findAll() {
        List<BookEntity> content = new ArrayList<>();
        for (int i=0; i<30; ++i)
            content.add(new BookEntity(i));
        return content;
    }

    public Page<BookEntity> findAll(Pageable pageable) {
        List<BookEntity> content = new ArrayList<>();
        for (int i = (int) pageable.getOffset(); content.size() < pageable.getPageSize(); ++i)
            content.add(new BookEntity(i));
        return new PageImpl<>(content, pageable, 100);
    }

    public BookEntity findById(int id) {
        return new BookEntity(id);
    }

}
