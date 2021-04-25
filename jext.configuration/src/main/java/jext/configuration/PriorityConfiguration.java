package jext.configuration;

public class PriorityConfiguration extends OverrideConfiguration {

    private static PriorityConfiguration instance = new PriorityConfiguration();

    public static PriorityConfiguration getConfiguration() {
        return instance;
    }

}
