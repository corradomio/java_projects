package jext.graph.named;

import jext.util.MapUtils;
import jext.util.StringUtils;

public class NamedIndex {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static final String DOT = ".";

    private String name;
    private String version = "";
    private String body;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public NamedIndex() {

    }

    // ----------------------------------------------------------------------
    // Properties/get
    // ----------------------------------------------------------------------

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getBody() {
        return body;
    }

    public boolean hasVersion(String dbversion) {
        return version.isEmpty() || dbversion.startsWith(version);
    }

    // ----------------------------------------------------------------------
    // Properties/set
    // ----------------------------------------------------------------------

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        if (!version.endsWith(DOT))
            version += DOT;
        this.version = version;
    }

    public void setBody(String body) {
        this.body = body;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
