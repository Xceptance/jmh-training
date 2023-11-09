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
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * The simplest JMH benchmark, it is still non-sense!
 * Vary the data with a setup.
 */
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class A03
{
	long time;

	@Setup
	public void setup()
	{
		time = System.currentTimeMillis();
	}

	@Benchmark
	public void add()
	{
		var x = 1 + time;
	}

    public static void main(String[] args) throws RunnerException
    {
        var opt = new OptionsBuilder()
        		// important, otherwise we will run all tests!
                .include(A03.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
