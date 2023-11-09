package org.xc.jmh.naive;

import java.util.Arrays;

/**
 * Let's calculate the average of integers in a list using a plain, a lambda,
 * and a parallel lambda approach.
 *
 * @author rschwietzke
 *
 */
public class Example3
{
    public static void main(String[] args)
    {
        var data = new int[100_000];
        for (int i = 0; i < data.length; i++)
        {
            data[i] = i;
        }

        // manual
        var s1 = System.nanoTime();
        {
            var total = 0.0d;
            for (int i = 0; i < data.length; i++)
            {
                total += data[i];
            }
            var r = total / data.length;
        }
        var e1 = System.nanoTime();

        // stream
        var s2 = System.nanoTime();
        {
            var r = Arrays.stream(data).average();
        }
        var e2 = System.nanoTime();

        // stream parallel
        var s3 = System.nanoTime();
        {
            var r = Arrays.stream(data).parallel().average();
        }
        var e3 = System.nanoTime();

        System.out.printf("Manual  : %d ns%n", e1 - s1);
        System.out.printf("Stream  : %d ns%n", e2 - s2);
        System.out.printf("Parallel: %d ns%n", e3 - s3);
    }
}
