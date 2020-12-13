package jext.springframework.data.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageRequest implements Pageable {

    // ----------------------------------------------------------------------
    // Factory methods
    // ----------------------------------------------------------------------

    public static Pageable of(int page, int size) {
        return of(page, size, Sort.unsorted());
    }

    public static Pageable of(int page, int size, Sort sort) {
        return new PageRequest(page, size, sort);
    }

    // ----------------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------------

    private static final int DEFAULT_SIZE = 20;

    protected int page;
    protected int size;
    protected Sort sort;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public PageRequest() {
        this.page = 0;
        this.size = DEFAULT_SIZE;
        this.sort = Sort.unsorted();
    }

    public PageRequest(int page, int size) {
        this.page = page;
        this.size = size;
        this.sort = Sort.unsorted();
    }

    public PageRequest(int page, int size, Sort sort) {
        this.page = page;
        this.size = size;
        this.sort = sort;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size){
        this.size = size;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
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
        return sort;
    }

    @Override
    public Pageable next() {
        return pageOf(page+1);
    }

    @Override
    public Pageable previousOrFirst() {
        if (page > 0)
            return pageOf(page-1);
        else
            return this;
    }

    @Override
    public Pageable first() {
        if (page == 0)
            return this;
        else
            return pageOf(0);
    }

    @Override
    public boolean hasPrevious() {
        return page > 0;
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    protected PageRequest pageOf(int page) {
        return new PageRequest(page, size, sort);
    }
}
