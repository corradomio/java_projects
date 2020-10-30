package jext.springframework.data.neo4j.repository.support;

import org.neo4j.cypherdsl.core.Expression;
import org.springframework.data.domain.Sort;


public class OrderBy {

    public static Expression of(Sort sort, String variable) {

        Expression orderBy = Cypher.o;

        for (Sort.Order order : sort) {

        }
        return null;
    }
}
