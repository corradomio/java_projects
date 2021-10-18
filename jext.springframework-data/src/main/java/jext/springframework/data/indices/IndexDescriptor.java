package jext.springframework.data.indices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class IndexDescriptor {
    // single/multi label:
    //      'Unnamed index' or 'index_<counter>'
    // fulltext:
    //      name assigned in the create statement
    public String name;

    // single/multi label:
    //      INDEX ON :label(property,...)
    //      INDEX ON :label(property_1, ...)
    // fulltext:
    //      INDEX ON NODE:label_1, ...(property_1, ....)
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


    // single property index
    //
    // {
    //      provider={version=1.0, key=native-btree},
    //      indexName='Unnamed index',
    //      description='INDEX ON :type(refId)',
    //      progress=100.0,
    //      tokenNames=['component'],
    //      state=ONLINE,
    //      id=14,
    //      type='node_label_property',
    //      failureMessage='',
    //      properties=['refId']
    // }
    //
    // full text index
    //
    // {
    //      provider={version=1.0, key=fulltext},
    //      indexName='spl_fulltext_index',
    //      description='INDEX ON NODE:project, module, source, type, comment, method, field, library, component, feature(name)',
    //      progress=100.0,
    //      tokenNames=['project', 'module', 'source', 'type', 'comment', 'method', 'field', 'library', 'component', 'feature'],
    //      state=ONLINE,
    //      id=7,
    //      type='node_fulltext',
    //      failureMessage='',
    //      properties=['name']
    // }
    //

    public IndexDescriptor(Map<String, Object> rec) {
        this.name = (String) rec.get("indexName");
        this.description = (String) rec.get("description");
        this.type = (String) rec.get("type");

        // tokenNames: String[]
        this.labels.addAll(Arrays.asList((String[])rec.get("tokenNames")));
        // properties: String[]
        this.properties.addAll(Arrays.asList((String[])rec.get("properties")));
    }

    @Override
    public String toString() {
        return description;
    }
}
