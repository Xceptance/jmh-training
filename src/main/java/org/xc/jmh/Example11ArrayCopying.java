package org.xc.jmh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
public class Example11ArrayCopying
{
    @Param({"1000"})
    int SIZE;
    @Param({"1", "10", "1000", "10000"})
    int COUNT;
    final List<long[]> srcArrays = new ArrayList<>();
    final List<long[]> destArrays = new ArrayList<>();
    final Random r = new Random(7L);

    @Setup
    public void setup()
    {
    	srcArrays.clear();
    	destArrays.clear();
    	var a = new long[SIZE];

        for (int i = 0; i < a.length; i++)
        {
        	a[i] = r.nextLong();
        }

        // get us enough arrays
        for (int i = 0; i < COUNT; i++)
        {
        	srcArrays.add(Arrays.copyOf(a, a.length));
        	destArrays.add(Arrays.copyOf(a, a.length));
        }

        Collections.shuffle(srcArrays);
        Collections.shuffle(destArrays);
    }

    @Benchmark
    public long[] systemCopy()
    {
    	var src = srcArrays.get(r.nextInt(srcArrays.size()));
    	var dest = destArrays.get(r.nextInt(destArrays.size()));

        System.arraycopy(src, 0, dest, 0, src.length);
        return dest;
    }

    @Benchmark
    public long[] manualCopy()
    {
    	var src = srcArrays.get(r.nextInt(srcArrays.size()));
    	var dest = destArrays.get(r.nextInt(destArrays.size()));

    	for (int i = 0; i < src.length; i++)
        {
            dest[i] = src[i];
        }

        return dest;
    }
}
