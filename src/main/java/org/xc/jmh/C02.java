package org.xc.jmh;

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
@Measurement(iterations = 5, batchSize = 1, time=1, timeUnit = TimeUnit.NANOSECONDS)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class C02
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
    @Warmup(iterations = 0, batchSize = 1)
	public byte[] systemCopyCold()
	{
		var dest = new byte[src.length];
		System.arraycopy(src, 0, dest, 0, src.length);

		return dest;
	}
	@Benchmark
    @Warmup(iterations = 1, time = 100, timeUnit = TimeUnit.MILLISECONDS)
	public byte[] systemCopyWarm()
	{
		var dest = new byte[src.length];
		System.arraycopy(src, 0, dest, 0, src.length);

		return dest;
	}
	@Benchmark
    @Warmup(iterations = 3, time = 2, timeUnit = TimeUnit.SECONDS)
	@Measurement(iterations = 10, batchSize = 1, time=1, timeUnit = TimeUnit.NANOSECONDS)
	public byte[] systemCopyHot()
	{
		var dest = new byte[src.length];
		System.arraycopy(src, 0, dest, 0, src.length);

		return dest;
	}

	@Benchmark
    @Warmup(iterations = 0, batchSize = 1)
	public byte[] manualCopyCold()
	{
		var dest = new byte[src.length];
		for (int i = 0; i < src.length; i++)
		{
			dest[i] = src[i];
		}

		return dest;
	}
	@Benchmark
    @Warmup(iterations = 1, time = 100, timeUnit = TimeUnit.MILLISECONDS)
	public byte[] manualCopyWarm()
	{
		var dest = new byte[src.length];
		for (int i = 0; i < src.length; i++)
		{
			dest[i] = src[i];
		}

		return dest;
	}

	@Benchmark
    @Warmup(iterations = 3, time = 2, timeUnit = TimeUnit.SECONDS)
	@Measurement(iterations = 10, batchSize = 1, time=1, timeUnit = TimeUnit.NANOSECONDS)
	public byte[] manualCopyHot()
	{
		var dest = new byte[src.length];
		for (int i = 0; i < src.length; i++)
		{
			dest[i] = src[i];
		}

		return dest;
	}
}

