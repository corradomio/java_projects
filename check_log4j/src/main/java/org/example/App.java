package org.example;

import jext.logging.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Logger.configure();

        Logger l = Logger.getLogger("main");
        l.debug("debug");
        l.info("info");
        l.warn("warn");
        l.error("error");
        l.fatal("fatal");
        System.out.println( "Hello World!");
    }
}
