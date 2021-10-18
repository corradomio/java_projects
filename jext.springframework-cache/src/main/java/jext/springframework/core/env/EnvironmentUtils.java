package jext.springframework.core.env;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/*
PropertiesPropertySource {name='systemProperties'}
OriginAwareSystemEnvironmentPropertySource {name='systemEnvironment'}
RandomValuePropertySource {name='random'}
OriginTrackedMapPropertySource {name='applicationConfig: [file:./application.properties]'}
 */

/**
 * Try to extract the list of properties from the list of property sources
 * registered in the environment.
 *
 * Method:
 *
 *  1) try to convert 'Environment' in a 'ConfigurableEnvironment'.
 *     'ConfigurableEnvironment' contains the method 'getPropertySources()'
 *  2) scan the list of 'PropertySource's
 *  3) try to convert the 'PropertySource' in a 'EnumerablePropertySource'
 *     'EnumerablePropertySource' contains the method 'getPropertyNames()'
 *  4) for each property name, retrieve the value as String and populate 'pros'
 *
 */
public class EnvironmentUtils {

    private static Logger logger = LoggerFactory.getLogger(EnvironmentUtils.class);

    public static Properties getProperties(ApplicationContext ctx) {
        return getProperties(ctx.getEnvironment());
    }

    public static Properties getProperties(Environment env) {

        Properties props = new Properties();

        if (!(env instanceof ConfigurableEnvironment))
            return props;

        ConfigurableEnvironment cenv = (ConfigurableEnvironment) env;

        cenv.getPropertySources().forEach(psource -> {
            if (psource instanceof EnumerablePropertySource) {
                List<String> names = Arrays.asList(((EnumerablePropertySource) psource).getPropertyNames());
                // names.sort(String::compareTo);
                names.forEach(name -> {
                    String value = psource.getProperty(name).toString();
                    props.put(name, value);
                });
            } else {
                logger.debug(String.format("Unsupported %s", psource.getClass()));
            }
        });

        return props;
    }

}