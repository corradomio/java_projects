package com.example;

import org.datanucleus.samples.jdo.tutorial.Inventory;
import org.datanucleus.samples.jdo.tutorial.Product;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Tutorial");
        PersistenceManager pm = pmf.getPersistenceManager();

        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            Inventory inv = new Inventory("My Inventory");
            Product product = new Product("Sony Discman", "A standard discman from Sony", 49.99);
            inv.getProducts().add(product);
            pm.makePersistent(inv);
            tx.commit();
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
    }
}