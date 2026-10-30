package check_equinox;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.launch.Framework;
import org.osgi.framework.launch.FrameworkFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;



public class RunEquinox {

    public static void main(String[] args) {
        try {
            // 1. Discover the Equinox FrameworkFactory via SPI
            ServiceLoader<FrameworkFactory> loader = ServiceLoader.load(FrameworkFactory.class);
            FrameworkFactory factory = loader.iterator().next();

            // 2. Set up Equinox configuration properties
            Map<String, String> config = new HashMap<>();

            // Clean up the previous cached runtime storage on initial boot
            config.put("org.osgi.framework.storage.clean", "onFirstInit");
            // Automatically launch the interactive OSGi console
            config.put("osgi.console", "");

            // (Optional) Declare the list of external bundles to automatically discover/start
            // config.put("osgi.bundles", "file:/path/to/my-bundle.jar@start");

            // 3. Instantiate and start the Equinox framework
            Framework framework = factory.newFramework(config);
            System.out.println("Initializing Equinox...");
            framework.init();

            // 4. Programmatically install or manage bundles directly via standard OSGi API
            BundleContext context = framework.getBundleContext();
            // Bundle myBundle = context.installBundle("file:/absolute/path/to/your/bundle.jar");
            // myBundle.start();

            // 5. Fire up the OSGi runtime environment
            framework.start();
            System.out.println("Equinox started successfully.");

            // 6. Keep the main application thread alive until Equinox shuts down
            framework.waitForStop(0);
            System.exit(0);

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
