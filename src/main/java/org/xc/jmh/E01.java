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

@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1, jvmArgsAppend = {"-Xms4g", "-Xmx4g", "-XX:+AlwaysPreTouch"})
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class E01
{
    final int[] integers = new int[1000];

    @Setup
    public void setup()
    {
        for (int i = 0; i < integers.length; i++)
        {
            integers[i] = i;
        }
    }

    @Benchmark
    public int lambda()
    {
        return Arrays.stream(integers).filter(i -> i % 2 == 0).sum();
    }

    @Benchmark
    public int loop()
    {
        int sum = 0;
        for (int i = 0; i < integers.length; i++)
        {
            var v = integers[i];
            if (v % 2 == 0)
            {
                sum += v;
            }
        }

        return sum;
    }
}

