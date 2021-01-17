package org.hls.check;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Thread currentThread = Thread.currentThread();

        System.out.printf("%s: %d\n", currentThread.getName(), currentThread.getId());
        Arrays.asList(currentThread.getStackTrace()).forEach(st -> {
            System.out.println(st.toString());
        });

        // Thread.getAllStackTraces().forEach((k,v) -> {
        //     System.out.println(k);
        // });
    }
}
