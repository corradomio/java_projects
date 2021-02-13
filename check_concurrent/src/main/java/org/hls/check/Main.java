package org.hls.check;

import jext.io.Console;
import jext.util.concurrent.Parallel;
import jext.util.concurrent.Sleep;

import java.io.IOException;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {

        Random rnd = new Random();

        Parallel.setup();

        Parallel.forEach(0, 5, (i) -> {

            int last = rnd.nextInt(1000000);
            Parallel.forEach(0, last, (j) -> {
                if (j % 10000 == 0) {
                    System.out.printf("%d, %d\n", i, j);
                    Sleep.sleep(500);
                }
            });
            System.out.printf("[%d] done\n", i);
        });

        Parallel.shutdown();

        Console.readKey("Press any key");
    }
}
