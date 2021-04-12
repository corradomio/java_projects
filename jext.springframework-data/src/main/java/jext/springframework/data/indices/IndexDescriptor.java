package jext.springframework.data.indices;

import java.util.ArrayList;
import java.util.List;

public class IndexDescriptor {
    // single/multi label:
    //      'Unnamed index'
    // fulltext:
    //      name assigned to the fulltext index
    public String name;

    // single/multi label:
    //      INDEX ON :type(refId,...)
    // fulltext:
    //      INDEX ON NODE:project, module, source, type, comment, method, field, library, component, feature(name)
    //
    public String description;

    // single/multi label:
    //      'node_label_property'
    // fulltext:
    //      'node_fulltext'
    //
    public String type;

    // list of labels (can be only 1)
    public List<String> labels = new ArrayList<>();
    // list of properties (can be only 1)
    public List<String> properties = new ArrayList<>();

    /**
     * Index name.
     * For the fulltext indices, it is the specified name,
     * for the label/property indices is ":label(property,...)"
     * as available in "description"
     */
    public String getName() {
        if ("Unnamed index".equals(name))
            return nameOf(description);
        else
            return name;
    }

    private static String nameOf(String description) {
        // INDEX ON :type(refId,...)
        int pos = description.indexOf(':');
        return description.substring(pos);
    }

    @Override
    public String toString() {
        return description;
    }
}
