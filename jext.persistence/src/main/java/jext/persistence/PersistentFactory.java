package jext.persistence;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.metamodel.Metamodel;

import java.util.Map;
import java.util.Properties;

public class PersistentFactory implements EntityManagerFactory, AutoCloseable {

    private final EntityManagerFactory emf;
    private final SynchronizationType synchronizeType;
    private final Properties managerProps;

    PersistentFactory(EntityManagerFactory emf,
                      SynchronizationType synchronizeType,
                      Properties managerProps) {
        this.emf = emf;
        this.synchronizeType = synchronizeType;
        this.managerProps = managerProps;
    }

    public PersistentManager createManager() {
        EntityManager em;
        if (synchronizeType == null)
            em = emf.createEntityManager(managerProps);
        else
            em = emf.createEntityManager(synchronizeType, managerProps);
        return new PersistentManager(em);
    }

    @Override
    public EntityManager createEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public EntityManager createEntityManager(Map map) {
        return emf.createEntityManager(map);
    }

    @Override
    public EntityManager createEntityManager(SynchronizationType synchronizationType) {
        return emf.createEntityManager(synchronizationType);
    }

    @Override
    public EntityManager createEntityManager(SynchronizationType synchronizationType, Map map) {
        return emf.createEntityManager(synchronizationType, map);
    }

    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        return emf.getCriteriaBuilder();
    }

    @Override
    public Metamodel getMetamodel() {
        return emf.getMetamodel();
    }

    @Override
    public boolean isOpen() {
        return emf.isOpen();
    }

    @Override
    public void close() {
        emf.close();
    }

    @Override
    public Map<String, Object> getProperties() {
        return emf.getProperties();
    }

    @Override
    public Cache getCache() {
        return emf.getCache();
    }

    @Override
    public PersistenceUnitUtil getPersistenceUnitUtil() {
        return emf.getPersistenceUnitUtil();
    }

    @Override
    public void addNamedQuery(String name, Query query) {
        emf.addNamedQuery(name, query);
    }

    @Override
    public <T> T unwrap(Class<T> cls) {
        return emf.unwrap(cls);
    }

    @Override
    public <T> void addNamedEntityGraph(String graphName, EntityGraph<T> entityGraph) {
        emf.addNamedEntityGraph(graphName, entityGraph);
    }
}
