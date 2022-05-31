Noe4J node types
----------------
    https://neo4j.com/docs/cypher-manual/current/syntax/values/

    Property types
        - Can be returned from Cypher queries
        - Can be used as parameters
        - Can be stored as properties
        - Can be constructed with Cypher literals
        int, float
        string
        bool
        point (spatial)
        date, time, localtime, datetime, localdatetime, duration (=== long)
        <simple type>[]

    Structural types
        - Can be returned from Cypher queries
        node
            - id
            - labels
            - map of properties
        relationship
            - id
            - type
            - map of properties
            - id start node
            - id end node
        path
            - an alternating sequence of nodes and relationships

    composite type
        - Can be returned from Cypher queries
        - Can be used as parameters
        - Can be constructed with Cypher literals
        list    a heterogeneous, ordered collection of values, each of which has any property, structural or composite type.
        map     a heterogeneous, unordered collection of (Key, Value) pairs.
            - key : string
            - value : any property, structural or composite type

-----------------------------------------------------------------------------

if refId e' definito, aggiungilo alla lista dei parametri
se rev == 0

// Integer, Float, String, Boolean,
// Point,
// Date, Time, LocalTime, DateTime, LocalDateTime, Duration.

default values

    bool:   false
    int:    0
    float:  0.0
    string: ""

create
    normal prop
        n.prop = value
    revision prop
        n.prop = [..., rev:value, ...]


update
    normal prop
        n.prop = value
    revision prop
        n.prop[rev] = value
        n.prop = [..., rev:value, ...]


query
    normal prop
        n.prop < <= == != >= > value
        n.prop (starts|ends) with|contains value
        n.prop in value
    revision prop
        n.prop[rev] < <= == != >= > value
        n.prop[rev] (starts|ends) with|contains value
        n.prop[rev] in value
