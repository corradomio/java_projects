package org.example;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Component;

import java.util.logging.Logger;


@Component(immediate=true)
public class Activator implements BundleActivator {
    private static final Logger logger = Logger.getLogger(Activator.class.toString());

    public Activator() {
    }

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        if (logger!= null)
            logger.info("[log] Hello OSGi! My Bundle has started.");
        else
            System.err.println("logger is null");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        if (logger!= null)
            logger.info("[log] Goodbye OSGi! My Bundle has stopped.");
    }

}
