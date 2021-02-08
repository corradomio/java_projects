Serializer performances
    https://github.com/eishay/jvm-serializers/wiki

Note: we need a FULL graph serializer



JSON:
    Gson
    Jackson
    Fastjson
    Moshi
    Jsoniter

Binary
    Kryo
        https://github.com/EsotericSoftware/kryo
        this works well.

    ActiveSerializer
        https://github.com/activej/activej/tree/v4.0-beta1/core-serializer

    Protostuff
        https://github.com/eishay/jvm-serializers/wiki
        https://github.com/protostuff/protostuff
        NO: it doesn't accept interfaces in deserialization

    fst : Fast-serialization
        https://github.com/RuedigerMoeller/fast-serialization
        https://ruedigermoeller.github.io/fast-serialization/
        NO: class MUST BE ""Serializable""/""Externalizable""
