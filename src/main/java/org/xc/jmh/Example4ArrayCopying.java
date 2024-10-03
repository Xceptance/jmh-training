package org.xc.jmh;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class Example4ArrayCopying
{
    @Param({"10", "1000", "100000"})
    int SIZE;
    long[] src;

    @Setup
    public void setup()
    {
        src = new long[SIZE];
        final Random r = new Random(7L);
        for (int i = 0; i < src.length; i++)
        {
        	src[i] = r.nextLong();
        }
    }

    @Benchmark
    public long[] systemCopy()
    {
        var dest = new long[src.length];
        System.arraycopy(src, 0, dest, 0, src.length);

        return dest;
    }

    @Benchmark
    public long[] arrayCopy()
    {
        return Arrays.copyOf(src, src.length);
    }

    @Benchmark
    public long[] manualCopy()
    {
        var dest = new long[src.length];
        for (int i = 0; i < src.length; i++)
        {
            dest[i] = src[i];
        }

        return dest;
    }
}