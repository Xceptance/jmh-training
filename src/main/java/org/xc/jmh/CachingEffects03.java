package org.xc.jmh;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
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
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1, jvmArgsAppend = {"-Xms4g", "-Xmx4g", "-XX:+AlwaysPreTouch"})
public class CachingEffects03
{
    @Param({"true", "false"})
    boolean live;

    @Param({"100", "10000"})
    int SIZE;
    int LOAD = 1_000_000;

    List<String> work;
    List<String> load;

	@Setup(value = Level.Trial)
    public void setup()
    {
        load = new ArrayList<>(LOAD);
        work = new ArrayList<>(SIZE);

		var s = new StringBuilder("09876512tuqw joiqwie uasdjk alsjklfjfjklsjfkljjsd aisud asdudzauzsdu auszfduzasf$");
		s.append(System.currentTimeMillis());

        for (int i = 0; i < LOAD; i++)
        {
            // ensure that we are a new string aka a new memory area next to the previous one
            load.add(s.toString());
        }

        if (live)
        {
            // shuffle the data, with fixed outcome
            Collections.shuffle(load, new Random(42L));
        }

        // copy us the data, when live, we get a random pattern, when
        // not live, we get basically data that is laid out next to each other
        for (int i = 0; i < SIZE; i++)
        {
            work.add(load.get(i));
        }
    }

    @Benchmark
    public int manual()
    {
        var n = 0;
        for (int i = 0; i < work.size(); i++)
        {
            var s = work.get(i);
            for (int index = 0; index < s.length(); index++)
            {
                if (s.charAt(index) == '$')
                {
                    n += index;
                    break;
                }
            }
        }
        return n;
    }

    @Benchmark
    public int indexof()
    {
        var n = 0;
        for (int i = 0; i < work.size(); i++)
        {
            n += work.get(i).indexOf('$');
        }
        return n;
    }
}
