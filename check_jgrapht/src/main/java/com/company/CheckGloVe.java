package com.company;

import jext.embedding.Embedding;
import jext.embedding.GloVe;

import java.io.File;

public class CheckGloVe {

    public static void main(String[] args) {
        GloVe glove = new GloVe();

        glove.load(new File("D:\\Datasets\\GloVe\\glove.6B.100d.txt"));

        Embedding girl = glove.getEmbedding("girl");
        System.out.println(girl.get());

    }
}
