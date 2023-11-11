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
import org.openjdk.jmh.infra.Blackhole;

@Fork(1)
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class M02
{
	final int SIZE = 1000;
	int[] data;

	@Setup
	public void setup()
	{
		var r = new Random(42L);
		data = new int[SIZE];
		for (int i = 0; i < SIZE; i++)
		{
			data[i] = r.nextInt();
		}
	}

	@Benchmark
	@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
	@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
	public long sum_time()
	{
		long result = 0;
		for (int i = 0; i < data.length; i++)
		{
			result += data[i];
			Blackhole.consumeCPU(10);
		}

		return result;
	}

	@Benchmark
	@Warmup(iterations = 2, batchSize = 1, time = 1, timeUnit = TimeUnit.SECONDS)
	@Measurement(iterations = 3, batchSize = 1000, time = 1, timeUnit = TimeUnit.SECONDS)
	public long sum_batch()
	{
		long result = 0;
		for (int i = 0; i < data.length; i++)
		{
			result += data[i];
			Blackhole.consumeCPU(10);
		}

		return result;
	}
}
