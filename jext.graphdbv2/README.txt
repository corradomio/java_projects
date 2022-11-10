
    MATCH (n:type {prop:$value, ...})
    WHERE n.prop op value
       OR pred(n.prop, ...)
    SET n.prop = value, n.prop = fun(n.prop, ....)
    RETURN n, n.prop, ...



    CREATE (n:type


Delete nodes&edges in revision
------------------------------

With rev != -1, nothing is really deleted.
The revisioned object will have 'inRevision[rev] = false'


Revisioned edges
----------------

An edge is revisioned IF one of the node is revisioned
NO: also the edge must be revisioned!


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

-----------------------------------------------------------------------------

1) rimuovere la dipendenza da GraphSchema in ModelSchema
2) supportare models & nodeType non esistenti con comportamenti ""standard""

simple_type
    int, double
    string
    bool


revisioned_type
    simple_type[revision]


revisioned_node
    inRevision : bool[]


Problema:
    come supportare strutture dati piu' complesse?
    mappe
    tensori (array moltidimensionali)

    l'unica e':

    (node)-[:property]->(value {revision:rev})

    invece che una property del node, una relazione con il node della property
    e il valore della property per quella particolare revisione


-----------------------------------------------------------------------------

    name    ==  Name[name, -1]
    value   == Value[=, value]

    {name,          value           }
    {Name[name, i], value           }
    {name,          Value[op, value]}
    {Name[name, i], Value[op, value]}

-----------------------------------------------------------------------------

modelNode
    revisions: [1,2,3,4]        quali revisioni sono possibili

revisionedNode
    inRevision: [f,t,f,...]    in quale revisione e' visibile
                 0 1 2

revisionedEdge
    inRevision: [f,t,f,...]    in quale revisione e' visibile
                 0 1 2

-----------------------------------------------------------------------------

special properties:

    1) $count: it is incremented on each "create"
    2) versioned T[]: it must be implemented with an extra node with name VT

        VT {
            revision: int
            value: T[]
        }

