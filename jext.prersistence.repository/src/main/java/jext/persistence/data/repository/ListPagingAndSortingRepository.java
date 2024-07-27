package jext.persistence.data.repository;

import jext.persistence.data.domain.Sort;

import java.util.List;

public interface ListPagingAndSortingRepository<T, ID> extends PagingAndSortingRepository<T, ID> {

    List<T> findAll(Sort sort);

}
