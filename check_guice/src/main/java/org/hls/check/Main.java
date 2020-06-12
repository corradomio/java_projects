package org.hls.check;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.hls.check.billing.BillingModule;
import org.hls.check.billing.BillingService;

public class Main {
    // public static void main(String[] args) {
    //     System.out.println("Hello World");
    // }
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new BillingModule());
        BillingService billingService = injector.getInstance(BillingService.class);
        //...
    }
}
