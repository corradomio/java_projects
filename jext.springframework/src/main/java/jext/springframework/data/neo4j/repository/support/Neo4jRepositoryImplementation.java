package jext.springframework.data.neo4j.repository.support;

import jext.springframework.data.neo4j.repository.Neo4jRepository;
import jext.springframework.data.neo4j.repository.Neo4jSpecificationExecutor;
import org.springframework.data.jpa.repository.query.EscapeCharacter;
import org.springframework.data.jpa.repository.support.CrudMethodMetadata;

import java.io.Serializable;

public interface Neo4jRepositoryImplementation<T, ID extends Serializable>
        extends Neo4jRepository<T, ID>, Neo4jSpecificationExecutor<T> {

    /**
     * Configures the {@link CrudMethodMetadata} to be used with the repository.
     *
     * @param crudMethodMetadata must not be {@literal null}.
     */
    void setRepositoryMethodMetadata(CrudMethodMetadata crudMethodMetadata);

    /**
     * Configures the {@link EscapeCharacter} to be used with the repository.
     *
     * @param escapeCharacter Must not be {@literal null}.
     */
    default void setEscapeCharacter(EscapeCharacter escapeCharacter) {

    }
}
