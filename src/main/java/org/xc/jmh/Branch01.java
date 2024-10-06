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
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@Threads(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class Branch01
{
    private static final int COUNT = 10000;

    // sorted ints
    private int[] sorted = new int[COUNT];
    // fully unsoerted
    private int[] unsorted = new int[COUNT];
    // alternating branch usage
    private int[] pattern2 = new int[COUNT];
    // 80/20 branch usage
    private int[] pattern5 = new int[COUNT];
    // data from pattern2, but randomized
    private int[] random = new int[COUNT];

    @Setup
    public void setup()
    {
        final Random r = new Random(13241L);

        // using int here to avoid problems with memory and caches aka
        // only inline data and hence same caching behavior
        for (int i = 0; i < COUNT; i++)
        {
        	final int d = r.nextInt(100_000_000) + 1;

            sorted[i] = d;
            unsorted[i] = d;
            pattern2[i] = i % 2 == 0 ? 100_000_000 : r.nextInt(50_000_000);
            pattern5[i] = i % 5 == 0 ? 100_000_000 : r.nextInt(50_000_000);
            random[i] = pattern2[i];
        }
        Arrays.sort(sorted);

        // make it noisy to break the branch predictor
        for (int i = 0; i < random.length; i++) {
            int j = r.nextInt(random.length);
            var t = random[j];
            random[j] = random[i];
            random[i] = t;
        }
    }

    public long doIt(int[] array)
    {
    	long sum = 2;
        for (int v : array)
        {
            if (v > 50_000_000)
            {
                sum += (v >> 2);

            }
            else
            {
                sum += (v << 2);
            }
        }
        return sum;
    }


    @Benchmark
    public long sorted(Blackhole bh)
    {
    	return doIt(sorted);
    }

    @Benchmark
    public long unsorted(Blackhole bh)
    {
    	return doIt(unsorted);
    }

    @Benchmark
    public long pattern2(Blackhole bh)
    {
        return doIt(pattern2);
    }

    @Benchmark
    public long random(Blackhole bh)
    {
        return doIt(random);
    }

    @Benchmark
    public long pattern5(Blackhole bh)
    {
        return doIt(pattern5);
    }
}