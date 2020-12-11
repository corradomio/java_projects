package jext.springframework.data.domain;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;

public class PageRequest implements Pageable {

    public static Pageable of(int page, int size) {
        return new PageRequest(page, size);
    }

    private static final int DEFAULT_SIZE = 20;

    private int page;
    private int size;

    public PageRequest() {
        page = 0;
        size = DEFAULT_SIZE;
    }

    public PageRequest(int p, int s) {
        this.page = p;
        this.size = s;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size){
        this.size = size;
    }

    @Override
    public int getPageNumber() {
        return page;
    }

    @Override
    public int getPageSize() {
        return size;
    }

    @Override
    public long getOffset() {
        return ((long)page)*size;
    }

    @Override
    public Sort getSort() {
        return Sort.unsorted();
    }

    @Override
    public Pageable next() {
        return pageOf(page+1, size);
    }

    @Override
    public Pageable previousOrFirst() {
        if (page > 0)
            return pageOf(page-1, size);
        else
            return this;
    }

    @Override
    public Pageable first() {
        if (page == 0)
            return this;
        else
            return pageOf(0, size);
    }

    @Override
    public boolean hasPrevious() {
        return page > 0;
    }

    protected Pageable pageOf(int page, int size) {
        return new PageRequest(page, size);
    }
}
