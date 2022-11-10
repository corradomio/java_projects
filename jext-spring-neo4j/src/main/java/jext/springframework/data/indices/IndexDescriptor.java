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

    //
    // Neo4j v3
    //

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

    //
    // Neo4J v4
    //
    // id	name	state	populationPercent
    //      uniqueness	type	entityType	labelsOrTypes
    //      properties	provider

    public IndexDescriptor(Map<String, Object> rec, int version) {
        if (version == 3) {
            this.name = (String) rec.get("indexName");
            this.description = (String) rec.get("description");
            this.type = (String) rec.get("type");

            // tokenNames: String[]
            this.labels.addAll(Arrays.asList((String[])rec.get("tokenNames")));
            // properties: String[]
            this.properties.addAll(Arrays.asList((String[])rec.get("properties")));
        }
        else {
            this.name = (String) rec.get("name");
            this.type = (String) rec.get("type");
            this.description = String.format("%s[%s,%s,%s]",
                rec.get("name"),
                rec.get("entityType"),
                rec.get("uniqueness"),
                rec.get("type"));

            // tokenNames: String[]
            Object[] array = (Object[]) rec.get("labelsOrTypes");
            for (int i=0; i<array.length; ++i)
                this.labels.add(array[i].toString());
            // properties: String[]
            array = (Object[]) rec.get("properties");
            for (int i=0; i<array.length; ++i)
                this.properties.add(array[i].toString());
        }
    }

    @Override
    public String toString() {
        return description;
    }
}
