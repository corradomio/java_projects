package jext.hashing;

import jext.hashing.provider.config.ConfigurableProvider;
import jext.hashing.provider.util.AlgorithmProvider;
import jext.hashing.provider.util.ClassUtil;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Provider;
import java.util.Map;


public class DistanceSensitiveProvider extends Provider implements ConfigurableProvider {
    private static String info = "DistanceSensitive Provider v1.0";
    public static final String PROVIDER_NAME = "DS";

    /* Constructs a provider with the specified name, version number,
     * and information.
     *
     * @param name    the provider name.
     * @param version the provider version number.
     * @param info    a description of the provider and its services.
     */
    public DistanceSensitiveProvider() {
        super(PROVIDER_NAME, 1.0, info);
        AccessController.doPrivileged((PrivilegedAction) () -> {
            setup();
            return null;
        });
    }

    private void setup() {
        loadAlgorithms(DIGEST_PACKAGE, DIGESTS);
    }


    private static final String DIGEST_PACKAGE = "jext.hashing.provider.digest.";
    private static final String[] DIGESTS =
        {
            "Simple"
        };

    private void loadAlgorithms(String packageName, String[] names)
    {
        for (int i = 0; i != names.length; i++)
        {
            Class clazz = ClassUtil.loadClass(DistanceSensitiveProvider.class, packageName + names[i] + "$Mappings");

            if (clazz != null)
            {
                try
                {
                    ((AlgorithmProvider)clazz.newInstance()).configure(this);
                }
                catch (Exception e)
                {   // this should never ever happen!!
                    throw new InternalError("cannot create instance of "
                        + packageName + names[i] + "$Mappings : " + e);
                }
            }
        }
    }

    @Override
    public void setParameter(String parameterName, Object parameter)
    {

    }

    @Override
    public boolean hasAlgorithm(String type, String name)
    {
        return containsKey(type + "." + name) || containsKey("Alg.Alias." + type + "." + name);
    }

    @Override
    public void addAttributes(String key, Map<String, String> attributeMap) {

    }

    @Override
    public void addAlgorithm(String key, String value)
    {
        if (containsKey(key))
        {
            throw new IllegalStateException("duplicate provider key (" + key + ") found");
        }

        put(key, value);
    }

}
