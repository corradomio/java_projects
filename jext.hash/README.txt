Java hashCode
-------------

    Each object implements "int Object.hashCode()"
    Some utility functions:

        Objects.hash(Object ... values) == Arrays.hashCode(Object[] a)
        Objects.hashCode(Object o)
            (o == null) ? 0 : o.hashCode()

        Arrays.hashCode(type[] o)
            with type a primitive type or Object


There are two different implementations for collections:

    String & List

         int hashCode = 1;
         for (E e : list)
             hashCode = 31*hashCode + ((e == null) ? 0 : e.hashCode());

    For collections & maps

        int hashCode = 1
        for (E e : collection)
             hashCode += ((e == null) ? 0 : e.hashCode())

    For Map.Entry:
        K k = getKey();
        V v = getValue();
        ((k == null) ? 0 : k.hashCode()) ^ ((v == null) ? 0 : v.hashCode());


Guava Hashing
-------------
    HashFunction hf = Hashing.md5();
    HashCode hc = hf.newHasher()
           .putLong(id)
           .putString(name, Charsets.UTF_8)
           .putObject(person, personFunnel)
           .hash();


Other hash functions
--------------------
http://www.burtleburtle.net/bob/hash/doobs.html
http://www.burtleburtle.net/bob/c/lookup3.c





--- Provider SUN, version 1.80 ---
Algorithm name: "MD2"
Algorithm name: "MD5"
Algorithm name: "SHA"
Algorithm name: "SHA-224"
Algorithm name: "SHA-256"
Algorithm name: "SHA-384"
Algorithm name: "SHA-512"
Alias: "SHA-1" -> "SHA"
Alias: "OID.1.3.14.3.2.26" -> "SHA"
Alias: "1.3.14.3.2.26" -> "SHA"
Alias: "OID.2.16.840.1.101.3.4.2.4" -> "SHA-224"
Alias: "OID.2.16.840.1.101.3.4.2.3" -> "SHA-512"
Alias: "OID.2.16.840.1.101.3.4.2.2" -> "SHA-384"
Alias: "OID.2.16.840.1.101.3.4.2.1" -> "SHA-256"
Alias: "2.16.840.1.101.3.4.2.4" -> "SHA-224"
Alias: "2.16.840.1.101.3.4.2.3" -> "SHA-512"
Alias: "2.16.840.1.101.3.4.2.2" -> "SHA-384"
Alias: "2.16.840.1.101.3.4.2.1" -> "SHA-256"
Alias: "SHA1" -> "SHA"

--- Provider BC, version 1.51 ---
Algorithm name: "GOST3411"
Algorithm name: "MD2"
Algorithm name: "MD4"
Algorithm name: "MD5"
Algorithm name: "SHA-1"
Algorithm name: "RIPEMD128"
Algorithm name: "RIPEMD160"
Algorithm name: "RIPEMD256"
Algorithm name: "RIPEMD320"
Algorithm name: "SHA-224"
Algorithm name: "SHA-256"
Algorithm name: "SHA-384"
Algorithm name: "SHA-512"
Algorithm name: "SHA-512/224"
Algorithm name: "SHA-512/256"
Algorithm name: "SHA3-224"
Algorithm name: "SHA3-256"
Algorithm name: "SHA3-384"
Algorithm name: "SHA3-512"
Algorithm name: "Skein-256-128"
Algorithm name: "Skein-256-160"
Algorithm name: "Skein-256-224"
Algorithm name: "Skein-256-256"
Algorithm name: "Skein-512-128"
Algorithm name: "Skein-512-160"
Algorithm name: "Skein-512-224"
Algorithm name: "Skein-512-256"
Algorithm name: "Skein-512-384"
Algorithm name: "Skein-512-512"
Algorithm name: "Skein-1024-384"
Algorithm name: "Skein-1024-512"
Algorithm name: "Skein-1024-1024"
Algorithm name: "SM3"
Algorithm name: "TIGER"
Algorithm name: "WHIRLPOOL"
Alias: "SHA256" -> "SHA-256"
Alias: "SHA224" -> "SHA-224"
Alias: "1.3.36.3.2.3" -> "RIPEMD256"
Alias: "1.3.36.3.2.2" -> "RIPEMD128"
Alias: "1.3.36.3.2.1" -> "RIPEMD160"
Alias: "1.2.156.197.1.401" -> "SM3"
Alias: "SHA512" -> "SHA-512"
Alias: "SHA1" -> "SHA-1"
Alias: "GOST" -> "GOST3411"
Alias: "2.16.840.1.101.3.4.2.6" -> "SHA-512/256"
Alias: "2.16.840.1.101.3.4.2.5" -> "SHA-512/224"
Alias: "2.16.840.1.101.3.4.2.4" -> "SHA-224"
Alias: "2.16.840.1.101.3.4.2.3" -> "SHA-512"
Alias: "2.16.840.1.101.3.4.2.2" -> "SHA-384"
Alias: "2.16.840.1.101.3.4.2.1" -> "SHA-256"
Alias: "1.2.643.2.2.9" -> "GOST3411"
Alias: "1.3.14.3.2.26" -> "SHA-1"
Alias: "SHA512/224" -> "SHA-512/224"
Alias: "GOST-3411" -> "GOST3411"
Alias: "SHA512256" -> "SHA-512/256"
Alias: "SHA384" -> "SHA-384"
Alias: "SM3" -> "SM3"
Alias: "SHA" -> "SHA-1"
Alias: "1.2.840.113549.2.5" -> "MD5"
Alias: "1.2.840.113549.2.4" -> "MD4"
Alias: "1.2.840.113549.2.2" -> "MD2"