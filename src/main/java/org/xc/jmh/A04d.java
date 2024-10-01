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
import org.openjdk.jmh.infra.Blackhole;

/**
 * The simplest JMH benchmark
 * The optional way to blackhole stuff to avoid dropping it.
 */
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class A04d
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
	public void addCheap1(Blackhole b)
	{
		b.consume(1 + 1727610292048L);
	}

	@Benchmark
	public void addCheap2(Blackhole b)
	{
		b.consume(1 + time);
	}

	@Benchmark
	public void addCheap3(Blackhole b)
	{
		b.consume(1 + lTime.longValue());
	}

	@Benchmark
	public void addExpensive(Blackhole b)
	{
		b.consume((time * 0x5DEECE66DL + 0xBL + time) / (time * time * 0.42d));
	}
}
