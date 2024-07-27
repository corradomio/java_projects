package com.example;

import jakarta.persistence.Query;
import jext.persistence.PersistentFactory;
import jext.persistence.PersistentManager;
import jext.persistence.Persistence;
import jext.util.PropertiesUtils;
import org.ebtic.btdigital.entities.Hierarchy;

import java.io.File;
import java.util.List;
import java.util.Properties;

public class Main5 {

    public static void main(String[] args) {
        // get an EntityManagerFactory using the Persistence class
        // It is not recommended to obtain a factory often, as construction of a
        // factory is a costly operation. Typically you will like to cache
        // a factory and then refer it for repeated use

        Properties props = PropertiesUtils.load(new File("dbms.openjpa.properties"));

        PersistentFactory pf = Persistence.createFactory("btdigital", props);

        try(PersistentManager em = pf.createManager())
        {
            // try(AutoCommitTransaction tx = em.beginTransaction()) {
            //     Query query = em.createQuery("SELECT h FROM Hierarchy h");
            //     List results = query.getResultList();
            //     // give all those hard-working employees a raise
            //     for (Object res : results) {
            //         Hierarchy hier = (Hierarchy) res;
            //         System.out.println(hier.getName());
            //     }
            // }

            // Begin a transaction
            // em.getTransaction().begin();
            // query for all employees who work in our research division
            // and put in over 40 hours a week average
            Query query = em.createQuery("SELECT h FROM Hierarchy h");
            List results = query.getResultList();
            // give all those hard-working employees a raise
            for (Object res : results) {
                Hierarchy hier = (Hierarchy) res;
                System.out.println(hier.getName());
            }
            // commit will detect all updated entities and save them in database
            // em.getTransaction().commit();
            // free the resources
            // em.close();
        }

        pf.close();

    }
}
