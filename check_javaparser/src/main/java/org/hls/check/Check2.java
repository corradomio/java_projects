package org.hls.check;

import jext.serialization.fst.FstSerializer;
import jext.serialization.kryo.KryoSerializer;
import jext.serialization.protostuff.ProtostuffSerializer;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Check2 {

    public static void main(String[] args) throws IOException {

        List<String> l = new LinkedList<>();
        l.add("one");
        l.add("two");
        l.add("three");

        // KryoSerializer.serialize(new File("test.dat"), l);
        // ProtostuffSerializer.serialize(new File("test.dat"), l);
        FstSerializer.serialize(new File("test.dat"), l);

        // l = KryoSerializer.deserialize(new File("test.dat"), List.class);
        // l = ProtostuffSerializer.deserialize(new File("test.dat"), LinkedList.class);
        l = FstSerializer.deserialize(new File("test.dat"), LinkedList.class);
        System.out.printf("%s, %s", l, l.getClass());

    }
}
