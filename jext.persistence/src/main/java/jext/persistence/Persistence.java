package jext.persistence;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.SynchronizationType;

import java.util.Properties;

/**
 * Alternative to the standard creation of  a EntityManager in two steps:
 * .
 *      EntityManagerFactory factory = Persistence.createEntityManagerFactory(unitName, factoryProps);
 *      EntityManager em = factory.createEntityManager(synchronizeType, managerProps);
 * .
 * In this case it is enough to write
 * .
 *      EntityManager em = Persistence.createEntityManager(unitName, factoryProps, synchronizeType, managerProps);
 * .
 */
public class Persistence {

    private static final Properties NO_PROPS = new Properties();

    public static PersistentFactory createFactory(String persistenceUnitName, Properties factoryProps) {
        return createFactory(persistenceUnitName, factoryProps, null, null);
    }

    public static PersistentFactory createFactory(
        String persistenceUnitName,
        Properties factoryProps,
        Properties managerProps) {
        return createFactory(persistenceUnitName, factoryProps, null, managerProps);
    }

    public static PersistentFactory createFactory(
        String persistenceUnitName,
        Properties factoryProps,
        SynchronizationType synchronizeType,
        Properties managerProps) {
        if (factoryProps == null)
            factoryProps = NO_PROPS;

        EntityManagerFactory factory = jakarta.persistence.Persistence.createEntityManagerFactory(
            persistenceUnitName, factoryProps
        );

        return new PersistentFactory(factory, synchronizeType, managerProps);
    }


    public static PersistentManager createManager(String persistenceUnitName) {
        return createManager(persistenceUnitName, null, null, null);
    }

    public static PersistentManager createManager(String persistenceUnitName, Properties factoryProps) {
        return createManager(persistenceUnitName, factoryProps, null, null);
    }

    public static PersistentManager createManager(
        String persistenceUnitName,
        Properties factoryProps,
        Properties managerProps
    ) {
        return createManager(persistenceUnitName, factoryProps, null, managerProps);
    }

    public static PersistentManager createManager(
        String persistenceUnitName,
        Properties factoryProps,
        SynchronizationType synchronizeType,
        Properties managerProps
    ) {
        if (factoryProps == null)
            factoryProps = NO_PROPS;
        if (managerProps == null)
            managerProps = NO_PROPS;

        EntityManagerFactory factory = jakarta.persistence.Persistence.createEntityManagerFactory(
            persistenceUnitName, factoryProps
        );

        EntityManager em;

        if (synchronizeType == null)
            em = factory.createEntityManager(managerProps);
        else
            em = factory.createEntityManager(synchronizeType, managerProps);

        return new PersistentManager(em);
    }

}
