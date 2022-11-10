package jext.springframework.data.domain;

import org.springframework.data.domain.Sort;

public class Pageable extends PageRequest {

    // ----------------------------------------------------------------------
    // Factory methods
    // ----------------------------------------------------------------------

    public static Pageable of(int page, int size) {
        return of(page, size, Sort.unsorted());
    }

    public static Pageable of(int page, int size, Sort sort) {
        return new Pageable(page, size, sort);
    }

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public Pageable() {

    }

    public Pageable(int page, int size) {
        super(page, size);
    }

    public Pageable(int page, int size, Sort sort) {
        super(page, size, sort);
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    @Override
    protected PageRequest pageOf(int page) {
        return new Pageable(page, size, sort);
    }
}
