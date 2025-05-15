package com.example;

public class CheckThread {

    public static void main(String[] args) {
        if (Thread.currentThread().interrupted())
            System.out.println("ok");
    }
}
