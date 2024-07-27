package jext.persistence.data.repository;

import jext.persistence.data.domain.Page;
import jext.persistence.data.domain.Pageable;
import jext.persistence.data.domain.Sort;

public interface PagingAndSortingRepository<T, ID> extends Repository<T, ID> {

    Iterable<T> findAll(Sort sort);

    Page<T> findAll(Pageable pageable);
}
