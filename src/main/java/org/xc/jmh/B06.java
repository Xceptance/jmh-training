package org.xc.jmh;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

/**
 * Setup
 */
@Warmup(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class B06
{
	@Param({"100", "100000"})
	int SIZE;
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
	public long sumClassic()
	{
		long result = 0;
		for (int i = 0; i < data.length; i++)
		{
			result += data[i];
		}

		return result;
	}

	@Benchmark
	public long sumForEach()
	{
		long result = 0;
		for (int d : data)
		{
			result += d;
		}

		return result;
	}
}
