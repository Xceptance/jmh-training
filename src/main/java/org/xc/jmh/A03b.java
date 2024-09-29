package org.xc.jmh;

import java.math.BigInteger;
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
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class A03b
{
	Long time;
	BigInteger biTime;

	@Setup
	public void setup()
	{
		time = System.currentTimeMillis();
		biTime = BigInteger.valueOf(time);
	}

	@Benchmark
	public void addCheap()
	{
		var x = 1 + 1727610292048L;
	}

	@Benchmark
	public void addWithSideEffect()
	{
		var x = 1 + biTime.longValue();
	}

	@Benchmark
	public void addExpensive()
	{
		var x = (time * 0x5DEECE66DL + 0xBL + time) / (time * time * 0.42d);
	}
}
