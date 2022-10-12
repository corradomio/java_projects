package jext.metrics.providers.scitools;

public class SciToolsObject {

    public static SciToolsObject of(String id, String name, String kname) {
        return new SciToolsObject(id, name, kname);
    }

    private final String id;
    private final String name;
    private final String kname;

    private SciToolsObject(String id, String name, String kname) {
        this.id = id;
        this.name = name;
        this.kname = kname;
    }

}
