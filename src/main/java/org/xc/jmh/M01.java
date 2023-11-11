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

@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Thread)
public class M01
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
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.NANOSECONDS)
	public long sum_AverageTime()
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
	@BenchmarkMode(Mode.Throughput)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	public long sum_Throughput()
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
	@BenchmarkMode(Mode.SingleShotTime)
	@OutputTimeUnit(TimeUnit.NANOSECONDS)
	public long sum_SingleShotTime()
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
	@BenchmarkMode(Mode.SampleTime)
	@OutputTimeUnit(TimeUnit.NANOSECONDS)
	public long sum_SampleTime()
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
