package org.xc.jmh;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 6, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class Cache03
{
    final int SIZE = 100_000;
    final int[] src = new int[SIZE];

    @Setup
    public void setup()
    {
    	for (int i = 0; i < src.length; i++)
    	{
    		src[i] = i;
    	}
    }

    int step(int strideSize)
    {
        int sum = 0;
        for (int i = 0; i < SIZE; i = i + strideSize)
        {
            sum += src[i];
        }
        return sum;
    }


    @Benchmark
    public int step1()
    {
        int sum = 0;
        for (int i = 0; i < SIZE; i++)
        {
            sum += src[i];
        }

        return sum;
    }

    @Benchmark
    public int step20()
    {
        int sum = 0;
        for (int i = 0; i < SIZE; i = i + 20)
        {
            sum += src[i];
        }

        return sum;
    }

    @Benchmark
    public int stepUnrolled1()
    {
        return step(1);
    }

    @Benchmark
    public int stepUnrolled2()
    {
        return step(2);
    }

    @Benchmark
    public int stepUnrolled20()
    {
        return step(20);
    }
}
