package jext.persistence.data.repository;

import java.util.List;

public interface ListCrudRepository<T, ID> extends CrudRepository<T, ID> {

    <S extends T> List<S> saveAll(Iterable<S> entities);

    List<T> findAll();

    List<T> findAllById(Iterable<ID> ids);

}
