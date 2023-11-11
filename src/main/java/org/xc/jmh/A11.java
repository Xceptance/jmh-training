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
 * Blackholes and return handling, based on
 * https://github.com/openjdk/jmh/blob/master/jmh-samples/src/main/java/org/openjdk/jmh/samples/JMHSample_09_Blackholes.java
 *
 * We explicitly want a full method call, otherwise data is really small
 */
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class A11
{
	double x1, x2;

	/*
	 * Get us data that is unknown to javac, so we cannot precalculate a thing
	 */
	@Setup
	public void setup()
	{
		x1 = System.currentTimeMillis() / 42.1801;
		x2 = System.currentTimeMillis() / 42.1802;
	}

	/*
	 * Just do something WITHOUT objects
	 */
	private double compute(double d)
	{
		for (int c = 0; c < 10; c++)
		{
			d = d * d / Math.PI;
		}
		return d;
	}

	/**
	 * Get us a result
	 */
	@Benchmark
	public double baseline()
	{
		return compute(x1);
	}

	/*
	 * While the compute(x2) computation is intact, compute(x1)
	 * does not have any effect or sideeffect, hence it can be
	 * safley dropped
	 */
	@Benchmark
	public double measureWrong()
	{
		compute(x1);
		return compute(x2);
	}

	/**
	 * Verify that measureWrong really has a dropped compute(x1)
	 */
	@Benchmark
	public double measureWrongProof()
	{
		// compute(x1);
		return compute(x2);
	}

	/*
	 * Option 1: Return a result, make it undroppable
	 *
	 * Merge multiple results into one and return it.
	 * This is OK when is computation is heavy and merging
	 * the results does not offset the results much.
	 */
	@Benchmark
	public double measureRight_Return()
	{
		return compute(x1) + compute(x2);
	}

	/*
	 * Option 2: Blackhole the entire result
	 */
	@Benchmark
	public void measureRight_Blackhole(Blackhole bh)
	{
		bh.consume(compute(x1) + compute(x2));
	}

	/*
	 * Option 3: Blackhole both
	 */
	@Benchmark
	public void measureRight_2Blackholes(Blackhole bh)
	{
		bh.consume(compute(x1));
		bh.consume(compute(x2));
	}
}
