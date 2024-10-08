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

@Warmup(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class A05
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
	@CompilerControl(CompilerControl.Mode.DONT_INLINE)
	public long addCheap1()
	{
		return 1 + 1727610292048L;
	}

	@Benchmark
	@CompilerControl(CompilerControl.Mode.DONT_INLINE)
	public long addCheap2()
	{
		return 1 + time;
	}

	@Benchmark
	@CompilerControl(CompilerControl.Mode.DONT_INLINE)
	public long addCheap3()
	{
		return 1 + lTime.longValue();
	}

	@Benchmark
	@CompilerControl(CompilerControl.Mode.DONT_INLINE)
	public double addExpensive()
	{
		return (time * 0x5DEECE66DL + 0xBL + time) / (time * time * 0.42d);
	}
}