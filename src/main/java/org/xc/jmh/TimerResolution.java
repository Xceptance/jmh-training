package org.xc.jmh;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

/**
 * Simplified example based on
 * https://github.com/shipilev/timers-bench/blob/master/src/main/java/net/shipilev/TimersBench.java
 * by Aleksey Shipilëv
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
@Fork(1)
@State(Scope.Thread)
public class TimerResolution
{
    @Benchmark
    public long latency_nanotime()
    {
        return System.nanoTime();
    }

    public static double precision(long[] t)
    {
        // store samples
        for (int i = 0; i < t.length; i++)
        {
            t[i] = System.nanoTime();
        }

        // check difference
        var total = 0.0d;
        for (int i = 0; i < t.length - 1; i++)
        {
            total += t[i + 1] - t[i];
        }

        return total / (t.length - 1);
    }

    public static long precision2()
    {
        return System.nanoTime() - System.nanoTime();
    }

    public static void main(String[] args)
    {
        var t = new long[100];

        // call it to warm it
        var total = 0.0d;
        for (int i = 0; i < 100000; i++)
        {
            total += precision(t);
            total += precision2();
        }
        System.out.printf("Precision 1 - %8.2f%n", precision(t));

        total = 0.0d;
        for (int i = 0; i < 100000; i++)
        {
            total += precision2();
        }
        System.out.printf("Precision 2 - %8.2f%n", Math.abs(total / 100000));

        var s = System.nanoTime();
        for (int i = 0; i < 10_000_000; i = i + 10)
        {
            System.nanoTime();
            System.nanoTime();
            System.nanoTime();
            System.nanoTime();
            System.nanoTime();
            System.nanoTime();
            System.nanoTime();
            System.nanoTime();
            System.nanoTime();
            System.nanoTime();
            System.nanoTime();
        }
        var e = System.nanoTime();
        System.out.printf("Latency - %8.2f%n", ((e - s) / 10_000_000d));

        // Samples
        Arrays.stream(t).forEach(v -> System.out.printf("%d%n", v));
    }
}