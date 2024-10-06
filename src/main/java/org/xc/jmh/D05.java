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
 * Test several ways to split up strings. We are not implementing our own
 * solution for the moment, rather use what is provided. We want to split
 * a string by a char
 *
 * This is a small 1BRC exercise. The source format is
 * City;Temperature e.g. Berlin;5.5 or Rom;34.5 or Bergen;-12.8
 *
 * The temperature will go into another method soon, hence, you are supposed
 * to "consume" the temperature as String for the moment via a Blackhole.
 */
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1, jvmArgsAppend = {"-Xms4g", "-Xmx4g", "-XX:+AlwaysPreTouch"})
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class D05
{
	@Setup
	public void setup()
	{
		// Berlin;12.4
		// Rom;25.9
		// Bergen;-5.5
	}

	@Benchmark
	public void split(final Blackhole b)
	{
		// String::split()
	}

	@Benchmark
	public void indexOf(final Blackhole b)
	{
		// String::indexOf
	}

	@Benchmark
	public void tokenizer(final Blackhole b)
	{
		// use StringTokenizer
	}

	@Benchmark
	public void yourOwn(final Blackhole b)
	{
		// an optional different idea
	}
}

