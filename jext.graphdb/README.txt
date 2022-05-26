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
