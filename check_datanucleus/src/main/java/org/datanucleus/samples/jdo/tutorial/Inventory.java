package org.datanucleus.samples.jdo.tutorial;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import java.util.HashSet;
import java.util.Set;


@PersistenceCapable
public class Inventory
{
    @PrimaryKey
    String name = null;
    Set<Product> products = new HashSet<>();

    public Inventory(String name)
    {
        this.name = name;
    }

    public Set<Product> getProducts() {return products;}
}
