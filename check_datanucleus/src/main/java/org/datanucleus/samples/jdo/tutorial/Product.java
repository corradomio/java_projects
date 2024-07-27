package org.datanucleus.samples.jdo.tutorial;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Product
{
    @PrimaryKey
    @Persistent(valueStrategy= IdGeneratorStrategy.INCREMENT)
    long id;

    String name = null;
    String description = null;
    double price = 0.0;

    public Product(String name, String desc, double price)
    {

        this.name = name;
        this.description = desc;
        this.price = price;
    }
}
