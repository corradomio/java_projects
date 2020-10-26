package jext.springframework.data.neo4j.repository.support;

import jext.springframework.data.neo4j.domain.Specification;
import jext.springframework.data.neo4j.repository.Neo4jRepository;
import org.neo4j.ogm.session.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public class ExtendedNeo4jRepository<T, ID extends Serializable>
    extends org.springframework.data.neo4j.repository.support.SimpleNeo4jRepository<T, ID>
    implements Neo4jRepository<T, ID> {

    public ExtendedNeo4jRepository(Class<T> domainClass, Session session) {
        super(domainClass, session);
    }

    @Override
    public Optional<T> findOne(Specification<T> spec) {
        return Optional.empty();
    }

    @Override
    public List<T> findAll(Specification<T> spec) {
        return null;
    }

    @Override
    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        return null;
    }

    @Override
    public List<T> findAll(Specification<T> spec, Sort sort) {
        return null;
    }

    @Override
    public long count(Specification<T> spec) {
        return 0;
    }
}
