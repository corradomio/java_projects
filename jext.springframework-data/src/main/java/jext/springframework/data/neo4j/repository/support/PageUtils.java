package jext.springframework.data.neo4j.repository.support;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class PageUtils {

    // private static Pageable NO_PAGEABLE = new Pageable() {
    //     @Override
    //     public int getPageNumber() {
    //         return 0;
    //     }
    //
    //     @Override
    //     public int getPageSize() {
    //         return 0;
    //     }
    //
    //     @Override
    //     public long getOffset() {
    //         return 0;
    //     }
    //
    //     @Override
    //     public Sort getSort() {
    //         return Sort.unsorted();
    //     }
    //
    //     @Override
    //     public Pageable next() {
    //         return NO_PAGEABLE;
    //     }
    //
    //     @Override
    //     public Pageable previousOrFirst() {
    //         return NO_PAGEABLE;
    //     }
    //
    //     @Override
    //     public Pageable first() {
    //         return NO_PAGEABLE;
    //     }
    //
    //     @Override
    //     public boolean hasPrevious() {
    //         return false;
    //     }
    // };

    // private static Page NO_PAGE = new Page() {
    //     @Override
    //     public int getTotalPages() {
    //         return 0;
    //     }
    //
    //     @Override
    //     public long getTotalElements() {
    //         return 0;
    //     }
    //
    //     @Override
    //     public Page map(Function converter) {
    //         return null;
    //     }
    //
    //     @Override
    //     public int getNumber() {
    //         return 0;
    //     }
    //
    //     @Override
    //     public int getSize() {
    //         return 0;
    //     }
    //
    //     @Override
    //     public int getNumberOfElements() {
    //         return 0;
    //     }
    //
    //     @Override
    //     public List getContent() {
    //         return Collections.emptyList();
    //     }
    //
    //     @Override
    //     public boolean hasContent() {
    //         return false;
    //     }
    //
    //     @Override
    //     public Sort getSort() {
    //         return Sort.unsorted();
    //     }
    //
    //     @Override
    //     public boolean isFirst() {
    //         return true;
    //     }
    //
    //     @Override
    //     public boolean isLast() {
    //         return true;
    //     }
    //
    //     @Override
    //     public boolean hasNext() {
    //         return false;
    //     }
    //
    //     @Override
    //     public boolean hasPrevious() {
    //         return false;
    //     }
    //
    //     @Override
    //     public Pageable nextPageable() {
    //         return NO_PAGEABLE;
    //     }
    //
    //     @Override
    //     public Pageable previousPageable() {
    //         return NO_PAGEABLE;
    //     }
    //
    //     @Override
    //     public Iterator iterator() {
    //         return Collections.emptyList().iterator();
    //     }
    // };

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
