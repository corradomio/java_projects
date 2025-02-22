package com.example;

import jext.util.concurrent.Parallel;
import jext.util.concurrent.ParallelException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        // String[] msg = {
        //     "Hello", "Cruel", "World", "!"
        // };

        int[] array = {
            1,2,3,4,5,6,7,8,9,10,
            11,12,13,14,15,16,17,18,19,20
        };

        // Parallel.forEach(0,10, (i)->{
        //     System.out.println(i);
        // });
        //
        // Parallel.forEach(msg, (m)->{
        //     System.out.println(m);
        // });
        //
        // Parallel.forEach(array, (i)->{
        //     System.out.println(i);
        // });

        try {
            Parallel.forEach(array, i -> {
                // if (i == 10)
                throw new IllegalArgumentException();
            });
        } catch (ParallelException e) {
            System.out.println(e.getMessage());
        }

        Parallel.shutdown();
    }
}
