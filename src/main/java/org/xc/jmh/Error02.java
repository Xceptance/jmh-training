package org.xc.jmh;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.BenchmarkParams;

@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Fork(value = 1, jvmArgsAppend = {"-Xms4g", "-Xmx4g", "-XX:+AlwaysPreTouch"})
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class Error02
{
	int iterationCount = 0;
    final String[] integers = new String[1000];

    @Setup(Level.Iteration)
    public void setup(BenchmarkParams params)
    {
    	iterationCount++;

        for (int i = 0; i < integers.length; i++)
        {
            integers[i] = new String(String.valueOf(i));
        }
        // when we are beyond warmup the third time, inject failure
        if (iterationCount == params.getWarmup().getCount() + 6)
        {
        	integers[integers.length / 2] = "any other";
        }
        else
        {
        	integers[integers.length / 2] = "other any";
        }
    }

    @Benchmark
    public long parse()
    {
    	long total = 0;
    	for (int i = 0; i < integers.length; i++)
    	{
    		try
    		{
    			total += Integer.valueOf(integers[i]);
    		}
    		catch(NumberFormatException e)
    		{
    			total = 0;
    		}
    	}

    	return total;
    }
}

