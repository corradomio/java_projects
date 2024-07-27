package jext.persistence.data.repository;

import java.util.List;

public interface JpaRepository<T, ID> extends ListCrudRepository<T, ID>, ListPagingAndSortingRepository<T, ID>
    // , QueryByExampleExecutor<T>
{

    void flush();

    <S extends T> S saveAndFlush(S entity);

    <S extends T> List<S> saveAllAndFlush(Iterable<S> entities);

    @Deprecated
    default void deleteInBatch(Iterable<T> entities) {
        deleteAllInBatch(entities);
    }

    void deleteAllInBatch(Iterable<T> entities);

    void deleteAllByIdInBatch(Iterable<ID> ids);

    void deleteAllInBatch();

    @Deprecated
    T getOne(ID id);

    @Deprecated
    T getById(ID id);

    T getReferenceById(ID id);

    // @Override
    // <S extends T> List<S> findAll(Example<S> example);

    // @Override
    // <S extends T> List<S> findAll(Example<S> example, Sort sort);
}
