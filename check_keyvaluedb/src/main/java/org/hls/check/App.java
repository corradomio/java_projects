package org.hls.check;

import jext.data.kv.KVStorage;
import jext.data.kv.KVStorageManager;
import jext.data.kv.OpenMode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class App {

    private static void create() throws IOException {

        new File("glove.6B.50d.db").delete();

        KVStorage<String, float[]> storage = KVStorageManager.open(OpenMode.WRITE, new File("glove.6B.50d.db"), String.class, float[].class);

        int count = 0;
        try(BufferedReader rdr = new BufferedReader(new FileReader(new File("E:\\Datasets\\GloVe\\glove.6B\\glove.6B.50d.txt")))) {
            for(String line = rdr.readLine(); line != null; line = rdr.readLine()) {
                String[] parts = line.split(" ");
                String word = parts[0];
                float[] embedding = toFloats(parts, 1);

                ++count;
                if (count % 1000 == 0)
                    System.out.printf("  %d\n", count);

                storage.put(word, embedding);
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
        finally {
            storage.close();
        }
    }

    private static void read() throws IOException {

        System.out.println("Open storage");
        KVStorage<String, float[]> storage = KVStorageManager.open(new File("glove.6B.50d.db"), String.class, float[].class);

        System.out.println("read 1");
        float[] embeddings = storage.get("the");
        System.out.println(Arrays.asList(embeddings[0], embeddings[1], embeddings[2], embeddings[3]));

        System.out.println("read 2");
        embeddings = storage.get("sandberger");
        System.out.println(Arrays.asList(embeddings[0], embeddings[1], embeddings[2], embeddings[3]));

        System.out.println("scan");
        for(String key : storage.keySet()) {
            embeddings = storage.get(key);
            System.out.println(key);
        }

        storage.close();
    }

    public static void main(String[] args) throws Exception {
        KVStorageManager.configure();

        create();
        read();
    }

    private static float[] toFloats(String[] sv, int offset) {
        float[] fv = new float[sv.length-offset];
        int j=0;
        for(int i=offset; i<sv.length; ++i,++j)
            fv[j] = Float.parseFloat(sv[i]);
        return fv;
    }
}
