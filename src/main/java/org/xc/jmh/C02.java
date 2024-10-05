package org.xc.jmh;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

/**
 *
 */
@Measurement(iterations = 1, time=10, timeUnit = TimeUnit.MILLISECONDS)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class C02
{
	final int SIZE = 100_000;
	final byte[] src = new byte[SIZE];
	final byte[] dest = new byte[SIZE];

	@Benchmark
    @Warmup(iterations = 0)
	public byte[] systemCopyCold()
	{
		System.arraycopy(src, 0, dest, 0, src.length);
		return dest;
	}

	@Benchmark
    @Warmup(iterations = 1, time=10, timeUnit = TimeUnit.MILLISECONDS)
	public byte[] systemCopyWarm()
	{
		System.arraycopy(src, 0, dest, 0, src.length);
		return dest;
	}

	@Benchmark
    @Warmup(iterations = 1, time=1000, timeUnit = TimeUnit.MILLISECONDS)
	public byte[] systemCopyHot()
	{
		System.arraycopy(src, 0, dest, 0, src.length);
		return dest;
	}

	@Benchmark
    @Warmup(iterations = 0)
	public byte[] manualCopyCold()
	{
		for (int i = 0; i < src.length; i++)
		{
			dest[i] = src[i];
		}
		return dest;
	}

	@Benchmark
    @Warmup(iterations = 1, time=10, timeUnit = TimeUnit.MILLISECONDS)
	public byte[] manualCopyWarm()
	{
		for (int i = 0; i < src.length; i++)
		{
			dest[i] = src[i];
		}
		return dest;
	}

	@Benchmark
    @Warmup(iterations = 1, time=1000, timeUnit = TimeUnit.MILLISECONDS)
	public byte[] manualCopyHot()
	{
		for (int i = 0; i < src.length; i++)
		{
			dest[i] = src[i];
		}
		return dest;
	}
}

