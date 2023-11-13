package org.xc.jmh;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

/**
 * Simplified example based on
 * https://github.com/shipilev/timers-bench/blob/master/src/main/java/net/shipilev/TimersBench.java
 * by Aleksey Shipilëv
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 3, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 3, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
@Fork(1)
@State(Scope.Thread)
public class TimerResolutionMT
{
    @Benchmark
    @Threads(1)
    public long latency_nanotime1()
    {
        return System.nanoTime() + System.nanoTime();
    }

    @Benchmark
    @Threads(2)
    public long latency_nanotime2()
    {
        return System.nanoTime() + System.nanoTime();
    }

    @Benchmark
    @Threads(4)
    public long latency_nanotime4()
    {
        return System.nanoTime() + System.nanoTime();
    }

    @Benchmark
    @Threads(8)
    public long latency_nanotime8()
    {
        return System.nanoTime() + System.nanoTime();
    }
}