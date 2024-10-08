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

/**
 * The simplest JMH benchmark
 * Produce a result by returning data.
 */
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class A04a
{
	long time;
	Long lTime;

	@Setup
	public void setup()
	{
		time = System.currentTimeMillis();
		lTime = Long.valueOf(time);
	}

	@Benchmark
	public long addCheap1()
	{
		return 1 + 1727610292048L;
	}

	@Benchmark
	public long addCheap2()
	{
		return 1 + time;
	}

	@Benchmark
	public long addCheap3()
	{
		return 1 + lTime.longValue();
	}

	@Benchmark
	public double addExpensive()
	{
		return (time * 0x5DEECE66DL + 0xBL + time) / (time * time * 0.42d);
	}
}
