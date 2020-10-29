package org.hls.check.check_spring_neo4j.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class AuthorServiceImpl implements AuthorService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private AuthorRepository repository;

    @Override
    public List<Author> findByQuery(int delta) {

        TypedQuery query = em.createQuery("select a from author a where mod(a.id, ?1) = 0", Author.class);
        query.setParameter(1, delta);

        return query.getResultList();
    }

    @Override
    public List<Author> findByCriteria(int delta) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Author> query = builder.createQuery(Author.class);
        Root<Author> root = query.from(Author.class);

        Predicate isModular = builder.equal(builder.mod(root.get(/*Author_.id*/"id"), delta), 0);
        query.where(isModular);
        return em.createQuery(query.select(root)).getResultList();
    }

    @Override
    public List<Author> findBySpecification(int delta) {
        Specification<Author> isModular = new Specification<Author>() {
            @Override
            public Predicate toPredicate(Root<Author> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                return builder.equal(builder.mod(root.get(/*Author_.id*/"id"), delta), 0);
            }
        };

        return repository.findAll(isModular);
    }

    @Override
    public List<Author> findByQuerydsl(int delta) {
        QAuthor author = QAuthor.author;

        com.querydsl.core.types.Predicate pred = author.id.mod(Long.valueOf(delta)).eq(0L);

        List<Author> authors = new ArrayList<>();
        repository.findAll(pred).forEach(authors::add);
        return authors;
    }
}
