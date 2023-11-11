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
 */
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1, jvmArgsAppend = {"-Xms4g", "-Xmx4g", "-XX:+AlwaysPreTouch"})
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class D10
{
	@Setup
	public void setup()
	{

	}

	@Benchmark
	public void split(final Blackhole bj)
	{
		// String::split()
	}

	@Benchmark
	public void tokenizer(final Blackhole bj)
	{
		// use StringTokenizer(data, delimiter)
	}

	@Benchmark
	public void regex(final Blackhole bj)
	{
		// split using
		// Pattern.compile(delimiter)
		// split(this);
	}

	@Benchmark
	public void apacheTokenizer(final Blackhole bj)
	{
		// org.apache.commons.text.StringTokenizer
	}
}

