Minimo supporto:

    type:
        primitivi: int double string bytes
        compositi: array[T]: lista omogenea di tipi
                   list[T]:  lista di tipi diversi
                   map[K,T]: T: generico tipo, K: int, string

    oppure

        primitivi:  bytes
        composity:  list[bytes | list | map]
                    map[bytes, bytes | list | map]

    node: list of labels
        id
        property: type

    edge: label
        id
        property: type


Supporto alla revisione
