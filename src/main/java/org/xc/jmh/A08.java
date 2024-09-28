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
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

/**
 * The simplest JMH benchmark, it is still non-sense!
 */
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class A08
{
	@Benchmark
	@CompilerControl(CompilerControl.Mode.DONT_INLINE)
	public long doNothing1()
	{
		return 2 * 1699446438633L / 2 + 3 - 8272L;
	}

	@Benchmark
	@CompilerControl(CompilerControl.Mode.DONT_INLINE)
	public long doNothing2()
	{
		return 2 * 1699446438633L / 2 + 3 - 8272L;
	}
}
