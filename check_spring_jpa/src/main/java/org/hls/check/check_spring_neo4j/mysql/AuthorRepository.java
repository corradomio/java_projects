package org.hls.check.check_spring_neo4j.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "author", path = "author")
public interface AuthorRepository extends JpaRepository<Author, Long>,
        JpaSpecificationExecutor<Author>,
        QuerydslPredicateExecutor<Author> {

}
