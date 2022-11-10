package jext.graph.named;

import java.util.HashSet;
import java.util.Set;

public class NamedQuery {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private String name;
    private String body;
    private String version = "";
    // query parameters: $name
    private Set<String> qparams = new HashSet<>();
    // text parameters: ${name}
    private Set<String> tparams = new HashSet<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public NamedQuery() {

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

    // ----------------------------------------------------------------------
    // Properties/set
    // ----------------------------------------------------------------------

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setBody(String body) {
        this.body = body;
        parseParameters();
    }

    private void parseParameters() {
        int b,e;
        // text parameters
        b = body.indexOf("${", 0);
        while (b != -1) {
            e = body.indexOf("}", b);

            String tparam = body.substring(b+2,e);
            tparams.add(tparam);

            b = body.indexOf("${", e);
        }

        // query parameters
        b = body.indexOf("$", 0);
        while (b != -1) {
            if (body.charAt(b+1) == '{') {
                b = body.indexOf("$", b+1);
                continue;
            }
            e = endNameOf(body, b+1);

            String qparam = body.substring(b+1,e);
            qparams.add(qparam);

            b = body.indexOf("$", e);
        }
    }

    private static int endNameOf(String s, int b) {
        int e = b+1, n=s.length();
        while(e < n && Character.isJavaIdentifierPart(s.charAt(e)))
            ++e;
        return e;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
