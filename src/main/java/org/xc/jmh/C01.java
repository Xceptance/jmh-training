package org.xc.jmh;

import java.util.Arrays;
import java.util.Random;
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
 *
 */
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class C01
{
	final int SIZE = 10000;
	final byte[] src = new byte[SIZE];

	@Setup
	public void setup()
	{
		final Random r = new Random(7L);
		r.nextBytes(src);
	}

	@Benchmark
	public byte[] systemCopy()
	{
		var dest = new byte[src.length];
		System.arraycopy(src, 0, dest, 0, src.length);

		return dest;
	}

	@Benchmark
	public byte[] manualCopy()
	{
		var dest = new byte[src.length];
		for (int i = 0; i < src.length; i++)
		{
			dest[i] = src[i];
		}

		return dest;
	}
}

