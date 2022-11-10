package jext.springframework.data.neo4j.repository.support;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class PageUtils {

    public static <T> List<T> toList(Iterable<T> iterable) {
        List<T> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }

    /**
     * Create the page using 'content' as NEW page content, and 'upage' as
     * page information
     *
     * @param content new content of the page
     * @param upage page information
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> Page<T> toPage(List<T> content, Page<U> upage) {
        return new PageImpl<>(content, upage.getPageable(), upage.getTotalElements());
    }

    /**
     * Create the Page from 'iterable' (that iter on ALL content) and 'pageable'
     *
     * @param iterable iterable on the complete content
     * @param pageable page to select
     * @param <T>
     * @return
     */
    public static <T> Page<T> selectPage(Iterable<T> iterable, Pageable pageable) {
        List<T> allContent = toList(iterable);

        int size = allContent.size();
        int fromIndex = (int)pageable.getOffset();
        int toIndex = fromIndex + pageable.getPageSize();

        if (fromIndex >= size)
            fromIndex = toIndex = size;
        if (toIndex >= size)
            toIndex = size;

        List<T> content = allContent.subList(fromIndex, toIndex);

        return new PageImpl<>(content, pageable, allContent.size());
    }

    // public static <T> Page<T> noPage() {
    //     return NO_PAGE;
    // }
}
