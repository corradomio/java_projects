package jext.metrics.providers;

import jext.metrics.Metric;

public class SciToolsMetric implements Metric {

    public static SciToolsMetric of(String name, String kname, String key, float value) {
        return new SciToolsMetric(name, kname, key, value);
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private final String name;
    private final String kname;
    private final String key;
    private final float value;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private SciToolsMetric(String name, String kname, String key, float value) {
        this.name = name;
        this.kname = kname;
        this.key = key;
        this.value = value;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return String.format("%s:%s:%s", name, kname, key);
    }

    @Override
    public float getValue() {
        return 0.0f;
    }

    // ----------------------------------------------------------------------
    // Done
    // ----------------------------------------------------------------------

}
