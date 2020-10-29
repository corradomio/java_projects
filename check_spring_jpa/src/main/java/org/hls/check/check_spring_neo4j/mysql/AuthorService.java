package org.hls.check.check_spring_neo4j.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorService {

    List<Author> findByQuery(int delta);

    List<Author> findByCriteria(int delta);

    List<Author> findBySpecification(int delta);

    List<Author> findByQuerydsl(int delta);
}
