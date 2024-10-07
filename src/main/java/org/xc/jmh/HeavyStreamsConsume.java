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
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Measurement(iterations = 5, batchSize = 1, time=1, timeUnit = TimeUnit.NANOSECONDS)
@Fork(1)
public class HeavyStreamsConsume
{
    final int SIZE = 1024;
    final int[] integers = new int[SIZE];

    @Setup
    public void setup()
    {
        for (int i = 0; i < integers.length; i++)
        {
            // don't make it the same values as the position
            integers[i] = i - SIZE;
        }
    }

    static int calcAndBurn(int a)
    {
    	Blackhole.consumeCPU(50);
    	return a % 2;
    }

    // ==========================================================================
    @Benchmark
    @Warmup(iterations = 0, batchSize = 1)
    public int lambdaArrayCold()
    {
        return Arrays
        		.stream(integers)
        		.filter(i -> calcAndBurn(i) == 0).sum();
    }
    @Benchmark
    @Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
    public int lambdaArrayWarm()
    {
        return Arrays
        		.stream(integers)
        		.filter(i -> calcAndBurn(i) == 0).sum();
    }
    @Benchmark
    @Warmup(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
    public int lambdaArrayHot()
    {
        return Arrays
        		.stream(integers)
        		.filter(i -> calcAndBurn(i) == 0).sum();
    }

    // ==========================================================================
    @Benchmark
    @Warmup(iterations = 0, batchSize = 1)
    public int loopCold()
    {
        int sum = 0;
        for (int i = 0; i < integers.length; i++)
        {
            var v = integers[i];
            if (calcAndBurn(v) == 0)
            {
                sum += v;
            }
        }

        return sum;
    }

    @Benchmark
    @Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
    public int loopWarm()
    {
        int sum = 0;
        for (int i = 0; i < integers.length; i++)
        {
            var v = integers[i];
            if (calcAndBurn(v) == 0)
            {
                sum += v;
            }
        }

        return sum;
    }

    @Benchmark
    @Warmup(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
    public int loopHot()
    {
        int sum = 0;
        for (int i = 0; i < integers.length; i++)
        {
            var v = integers[i];
            if (calcAndBurn(v) == 0)
            {
                sum += v;
            }
        }

        return sum;
    }
}
