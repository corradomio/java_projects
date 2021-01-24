package org.hls.examples;
import jcuda.*;
import jcuda.runtime.*;

public class Main {

    public static void main(String[] args) {
        Pointer pointer = new Pointer();
        JCuda.cudaMalloc(pointer, 4);
        System.out.println("Pointer: "+pointer);
        JCuda.cudaFree(pointer);
    }
}
