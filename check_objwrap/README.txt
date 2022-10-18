


1) soluzione:

    Managers.newObject("name") -> ObjectWrapper

    ow.exists()
    ow.create(parameters)
    ow.delete()

2) soluzione:

    Managers.exists("name")
    Managers.create("name", parameters)
    Managers.get("name") -> ObjectInstance
    oi.delete()


La prima e' meglio, anche se la seconda puo' essere implementata sulla base
della prima


