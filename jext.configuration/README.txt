Configuration path expressions
------------------------------

    Skip the root tag in a XML configuration file

    - node.node
    - node(index)
    - node[@attribute]



Apache Commons Configuration
----------------------------

    ImmutableConfiguration (I)
        Configuration (I)
            HierarchicalConfiguration<T> (I)
            FileBasedConfiguration (I)
        ImmutableHierarchicalConfiguration (I)
            HierarchicalConfiguration (I)


    Configuration (I)
        AbstractConfiguration (A)
            AbstractHierarchicalConfiguration (A)
                BaseHierarchicalConfiguration
                    XMLConfiguration
                    AbstractYAMLBasedConfiguration
                        JSONConfiguration
                        YAMLConfiguration
                    INIConfiguration
                    CombinedConfiguration
                        DynamicCombinedConfiguration
                    SubnodeConfiguration
                    XMLPropertyListConfiguration    // OpenStep .plist file
                    PropertyListConfiguration       // OpenStep .plist file

        HierarchicalConfiguration (I)
            AbstractHierarchicalConfiguration (A)
                XMLConfiguration
                AbstractYAMLBasedConfiguration
                    JSONConfiguration
                    YAMLConfiguration
                INIConfiguration
                PatternSubtreeConfigurationWrapper
                CombinedConfiguration
                    DynamicCombinedConfiguration
                SubnodeConfiguration
                XMLPropertyListConfiguration        // OpenStep .plist file
                PropertyListConfiguration           // OpenStep .plist file

        FileBasedConfiguration (I)
            XMLConfiguration
            XMLPropertiesConfiguration
            INIConfiguration
            PropertiesConfiguration
            PatternSubtreeConfigurationWrapper
            JSONConfiguration
            YAMLConfiguration
            XMLPropertyListConfiguration        // OpenStep .plist file
            PropertyListConfiguration           // OpenStep .plist file


    ConfigurationBuilder
        BasicConfigurationBuilder
            FileBasedConfigurationBuilder
                ReloadingFileBasedConfigurationBuilder
            MultiFileConfigurationBuilder
                ReloadingMultiFileConfigurationBuilder
            CombinedConfigurationBuilder
                ReloadingCombinedConfigurationBuilder


Configuration Sources
---------------------


    PropertiesConfiguration Loads configuration values from a properties file.
    XMLConfiguration Takes values from an XML document.
    INIConfiguration Loads the values from a .ini file as used by Windows.
    PropertyListConfiguration Loads values from an OpenStep .plist file. XMLPropertyListConfiguration is also available to read the XML variant used by Mac OS X.
    JNDIConfiguration Using a key in the JNDI tree, can retrieve values as configuration properties.
    BaseConfiguration An in-memory method of populating a Configuration object.
    HierarchicalConfiguration An in-memory Configuration object that is able to deal with complex structured data.
    SystemConfiguration A configuration using the system properties
    ConfigurationConverter Takes a java.util.Properties or an org.apache.commons.collections.ExtendedProperties and converts it to a Configuration object.


Data Types
----------


    BigDecimal
    BigInteger
    boolean
    byte
    double
    float
    int
    long
    short
    String

    getList()
    getArray()



------------------------------


            String capacity = cconfig.getString("capacity[@value]", "");
            String expireAfterWrite = cconfig.getString("expireAfterWrite[@value]", "");
            String expireAfterAccess = cconfig.getString("expireAfterAccess[@value]", "");
            String softValues = cconfig.getString("softValues[@value]", "");
            String weakValues = cconfig.getString("weakValues[@value]", "");
