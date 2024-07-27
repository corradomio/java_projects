package jext.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class AutoCommitTransaction implements AutoCloseable {

    private final EntityTransaction tx;
    private boolean aborted;

    AutoCommitTransaction(EntityManager em) {
        this.tx = em.getTransaction();
        this.tx.begin();
    }

    public EntityTransaction entityTransaction() {
        return this.tx;
    }

    public void rollback() {
        this.aborted = true;
    }

    public void aborted() {
        this.aborted = true;
    }

    @Override
    public void close() {
        if (this.aborted)
            this.tx.rollback();
        else
            this.tx.commit();
    }
}
