package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        OctTree otree = OctTree.create(Coords.of(-200,-200,-200), Coords.of(200,200,200), 100);
    }
}
