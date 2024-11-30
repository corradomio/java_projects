package com.example;

import jakarta.persistence.TypedQuery;
import jext.persistence.Persistence;
import jext.persistence.PersistentFactory;
import jext.persistence.PersistentManager;
import jext.util.PropertiesUtils;
import org.ebtic.btdigital.entities.Hierarchy;

import java.io.File;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        Properties props = PropertiesUtils.load(new File("dbms.openjpa.properties"));

        PersistentFactory pf = Persistence.createFactory("btdigital", props);

        try(PersistentManager pm = pf.createManager()) {
            TypedQuery<Hierarchy> query = pm.createQuery("SELECT h FROM Hierarchy h", Hierarchy.class).query();
            // query.setMaxResults(1);
            List<Hierarchy> results = query.getResultList();
            // give all those hard-working employees a raise
            for (Hierarchy hier : results) {
                System.out.println(hier.getName());
            }

            System.out.println("Done");

        }

        pf.close();
    }
}
