package org.hls.check;

import jext.io.Console;
import jext.util.concurrent.Parallel;

import java.io.IOException;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {

        Random rnd = new Random();

        Parallel.setup();

        Parallel.forEach(0, 5, (i) -> {

            int last = rnd.nextInt(1000000);
            Parallel.forEach(0, last, (j) -> {
                if ((System.currentTimeMillis()/1000)%10 == 0)
                    System.out.printf("%d, %d\n", i, j);
            });

        });

        int uthreads = Parallel.shutdown();
        System.out.println(uthreads);

        Console.readKey("Press any key");
    }
}
