package org.xc.jmh;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.CompilerControl;
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
 * The simplest JMH benchmark, it is still non-sense!
 * Avoid compiler magic
 */
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class A10
{
	long time;

	@Setup
	public void setup()
	{
		time = System.currentTimeMillis();
	}

	@Benchmark
	public void addWrong()
	{
		var x = 1L + 1L;
	}

	@Benchmark
	public void addWrongDynamic()
	{
		var x = 1L + time;
	}

	@Benchmark
	@CompilerControl(CompilerControl.Mode.DONT_INLINE)
	public void addWrongNoInline()
	{
		var x = 1L + 1L;
	}

	@Benchmark
	@CompilerControl(CompilerControl.Mode.DONT_INLINE)
	public void addWrongDynamicNoInline()
	{
		var x = 1L + time;
	}

	@Benchmark
	@CompilerControl(CompilerControl.Mode.DONT_INLINE)
	public void addDynamicBlackhole(final Blackhole bh)
	{
		bh.consume(1L + time);
	}

	@Benchmark
	@CompilerControl(CompilerControl.Mode.DONT_INLINE)
	public void addStaticBlackhole(final Blackhole bh)
	{
		bh.consume(1L + 1L);
	}

	@Benchmark
	@CompilerControl(CompilerControl.Mode.DONT_INLINE)
	public long addDynamicReturn()
	{
		return 1L + time;
	}

	@Benchmark
	@CompilerControl(CompilerControl.Mode.DONT_INLINE)
	public long addStaticReturn()
	{
		return 1L + 1L;
	}
}
