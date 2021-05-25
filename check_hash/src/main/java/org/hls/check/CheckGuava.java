package org.hls.check;

import com.google.common.base.Charsets;
import com.google.common.base.Objects;
import com.google.common.hash.Funnel;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import jext.util.LongHash;


public class CheckGuava {

    public static void main(String[] args) {
        Objects.hashCode(1, 2.);
        LongHash.hashCode(1, 2.);

        long id = 1001;
        String name = "corrado";
        Object person = null;
        Funnel<Object> personFunnel = null;


        HashFunction hf = Hashing.md5();
        HashCode hc = hf.newHasher()
            .putLong(id)
            .putString(name, Charsets.UTF_8)
            .putObject(person, personFunnel)
            .hash();
    }
}
