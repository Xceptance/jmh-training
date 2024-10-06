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
 * Just compare String building
 */
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1, jvmArgsAppend = {"-Xms4g", "-Xmx4g", "-XX:+AlwaysPreTouch"})
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class D01
{
	@Setup
	public void setup()
	{

	}

	@Benchmark
	public String classic()
	{
		// classic string magic here, such as a + b
		return null;
	}

	@Benchmark
	public String concat()
	{
		// use a.concat(b)
		return null;
	}

	@Benchmark
	public String builder()
	{
		// use a stringbuilder a.append(b)
		return null;
	}

	@Benchmark
	public String sizedBuilder()
	{
		// use a presized stringbuilder
		// new StringBuilder(expectedSize)
		// a.eppend(b)
		return null;
	}
}

