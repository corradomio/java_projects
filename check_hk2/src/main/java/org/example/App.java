package org.example;

import org.glassfish.hk2.api.DynamicConfigurationService;
import org.glassfish.hk2.api.Populator;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.api.ServiceLocatorFactory;
import org.glassfish.hk2.extension.ServiceLocatorGenerator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args)
    {
        System.out.println( "Hello World!" );
        ServiceLocator locator = ServiceLocatorUtilities.createAndPopulateServiceLocator();
        Foo foo = locator.getService(Foo.class);
        System.out.println(foo.getBook().getName());

        Library lib = locator.getService(Library.class);
        lib.getAllBooks().forEach(book -> {
            System.out.println(book.getName());
        });
    }


    static void m() {
        ServiceLocatorFactory slf;
        ServiceLocatorGenerator slg;
        DynamicConfigurationService dcs;
        Populator p ;
    }
}
