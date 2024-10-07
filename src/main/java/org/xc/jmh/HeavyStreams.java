package org.xc.jmh;

import java.math.BigInteger;
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

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, batchSize = 1, time = 1, timeUnit = TimeUnit.NANOSECONDS)
@Fork(1)
public class HeavyStreams
{
    final static int SIZE = 1024;
    final static BigInteger[] integers = new BigInteger[SIZE];

    static
    {
        for (int i = 0; i < integers.length; i++)
        {
            integers[i] = BigInteger.valueOf(i).pow(i);
        }
    }

    // ==========================================================================
    @Benchmark
    @Warmup(iterations = 0, batchSize = 1)
    public int lambdaArrayCold()
    {
        return Arrays.stream(integers).filter(i -> i.mod(BigInteger.TWO).equals(BigInteger.ZERO)).map(i -> i.sqrt()).mapToInt(BigInteger::intValue).sum();
    }
    @Benchmark
    @Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
    public int lambdaArrayWarm()
    {
        return Arrays.stream(integers).filter(i -> i.mod(BigInteger.TWO).equals(BigInteger.ZERO)).map(i -> i.sqrt()).mapToInt(BigInteger::intValue).sum();
    }
    @Benchmark
    @Warmup(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
    public int lambdaArrayHot()
    {
        return Arrays.stream(integers).filter(i -> i.mod(BigInteger.TWO).equals(BigInteger.ZERO)).map(i -> i.sqrt()).mapToInt(BigInteger::intValue).sum();
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

            if (v.mod(BigInteger.TWO).equals(BigInteger.ZERO))
            {
                sum += v.sqrt().intValue();
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

            if (v.mod(BigInteger.TWO).equals(BigInteger.ZERO))
            {
                sum += v.sqrt().intValue();
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

            if (v.mod(BigInteger.TWO).equals(BigInteger.ZERO))
            {
                sum += v.sqrt().intValue();
            }
        }

        return sum;
    }
}
