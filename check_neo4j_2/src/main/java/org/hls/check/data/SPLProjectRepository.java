package org.hls.check.data;

import jext.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

// @CacheConfig(cacheNames={"data.dependency.splproject"})
@Repository
public interface SPLProjectRepository extends Neo4jRepository<SPLProjectEntity, Long>
{
    // @Cacheable
    SPLProjectEntity findByRefId(String refId);
    /*@Cacheable*/ SPLProjectEntity findByFullname(String fullname);
    /*@Cacheable*/ SPLProjectEntity findByRepositoryAndName(String repositoryName, String projectName);

    List<SPLProjectEntity> findAllByRepository(String repositoryName);
    Page<SPLProjectEntity> findAllByRepository(String repositoryName, Pageable pageable);

    long countByRepository(String repository);

    @Override
    // @CacheEvict(allEntries = true)
    SPLProjectEntity save(SPLProjectEntity entity);

    @Override
    // @CacheEvict(allEntries = true)
    void deleteById(Long id);

    @Override
    @CacheEvict(allEntries = true)
    void delete(SPLProjectEntity entity);

    @Override
    // @CacheEvict(allEntries = true)
    void deleteAll(Iterable<? extends SPLProjectEntity> entities);

    @Override
    // @CacheEvict(allEntries = true)
    void deleteAll();
}
