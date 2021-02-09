Serializer performances
    https://github.com/eishay/jvm-serializers/wiki

Note: we need a FULL graph serializer

Other docs
    https://cr.openjdk.java.net/~briangoetz/amber/serialization.html

Other libs:
    Arrow, Avro, Bert, Blixter, Bond, Capn Proto, CBOR, Colfer, Elsa,
    Externalizor, FlatBuffers, FST, GemFire PDX, Gson, Hessian, Ion,
    Jackson, JBoss Marshaling, JSON.simple, Kryo, Kudu, Lightning,
    MessagePack, Okapi, ORC, Paranoid, Parcelable, Parquet, POF, Portable,
    Protocol Buffers, Protostuff, Quickser, ReflecT, Seren, Serial, Simple,
    Simple Binary Encoding, SnakeYAML, Stephenerialization, Thrift,
    TinySerializer, travny, Verjson, Wobly, Xson, XStream, YamlBeans


JSON:
    Gson
    Jackson
    Fastjson
    Moshi
    Jsoniter

Binary
    Kryo
        https://github.com/EsotericSoftware/kryo
        https://www.baeldung.com/kryo
        this works well.

    Protostuff
        https://github.com/protostuff/protostuff
        https://protostuff.github.io/docs/
        NO: it doesn't accept interfaces in deserialization

    fst : Fast-serialization
        https://github.com/RuedigerMoeller/fast-serialization
        https://ruedigermoeller.github.io/fast-serialization/
        NO: class MUST BE ""Serializable""/""Externalizable""


Others

    Azrael
        https://github.com/sylvainhalle/Azrael

    ActiveSerializer
        https://github.com/activej/activej/tree/v4.0-beta1/core-serializer
