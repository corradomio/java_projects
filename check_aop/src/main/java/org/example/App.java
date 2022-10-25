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

        Account a = new Account();
        System.out.println(a.withdraw(5));
        System.out.println(a.withdraw(100));
    }
}
