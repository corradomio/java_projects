package com.example;

public class Main {
    public static void main(String[] args) {
        PoissonDistrib pd = new PoissonDistrib(6,10);
        for (int i=0; i<100; ++i)
            System.out.println(pd.nextInt());
        System.out.println("Hello world!");
    }
}