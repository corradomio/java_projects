Per supportare

    SELECT .. FROM %s ...

non si puo' istanziare SUBITO i vari java.sql.Statement(s)
possono essere istanziati SOLO alla 's.execute(...)'
perche' in questo caso TUTTI i parametri sono stati impostati

--

Update:

    1) si devono impostare i 'statement-parameters' usando un indice NEGATIVO

        ps.setString(-<index>, <value>)

    2) si possono impostare i 'query-parameters'

        ps.setXXX(<index>, <value>)


------------------------------------------------------------
- Estensioni:
------------------------------------------------------------

    1) e' possibile registrare delle "query" che possono essere richiamate per "nome"
    2) "preparedStatement"/ puo' eseguire query passando il nome
    3) setString(index, value)
        SE index e' < 0, e' usato come "structure_parameter" cioe' come 'text-replacement'
    4) setXXX(name, value)
        e' possibile specificare i parametri per nome

Questo fa si che l'interfaccia JDBC non cambi.

------------------------------------------------------------
- Semplificazioni
------------------------------------------------------------

    jext.sql.DriverManager
        usato per 'wrappare' l'implementazione standard aggiungendo le
        nuove funzionalita'

    jext.sql.Connection
        aggiunge il supporto alla registrazione delle 'namedQueries'

    jext.sql.PrepareStatement
    jext.sql.CallableStatement
        e' possibile specificare i parametri per nome e non solo per posizione

    jext.sql.ResultSet
        NON SERVE

