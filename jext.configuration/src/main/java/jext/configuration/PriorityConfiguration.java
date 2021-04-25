package jext.configuration;

public class PriorityConfigurations extends OverrideConfiguration {

    private static PriorityConfigurations instance = new PriorityConfigurations();

    public static PriorityConfigurations getConfiguration() {
        return instance;
    }
}
