Persistence -> EntityManagerFactory -> EntityManager

EntityManagerFactory:
    equivalent to the 'engine' in SQLAlchemy
    it is just the object containing everything it is necessary to know
    to connect to the DBMS

EntityManager
    equivalent to the 'connection' in SQLAlchemy or 'Session' in other
    architecture. It is the effective connection to the DBMS, used
    to execute SQL statements, and handle transactions.
    If not specified, a transaction is automatically created.
    The entity manager must be 'closed' at the end