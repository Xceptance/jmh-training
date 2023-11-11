package org.xc.jmh;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.xc.jmh.util.FastRandom;
import org.xc.jmh.util.RandomUtils;

/**
 *
 */
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 2, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1, jvmArgsAppend = {"-Xms4g", "-Xmx4g", "-XX:+AlwaysPreTouch"})
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class T01
{
	final int SIZE = 10_000;
	List<String> csv = new ArrayList<>(SIZE);

	@Setup(Level.Trial)
	public void setup()
	{
		var r = new FastRandom(41L);
		for (int i = 0; i < SIZE; i++)
		{
			var sb = new StringJoiner(",");
			for (int j = 0; j < 5; j++)
			{
				sb.add(RandomUtils.randomString(r, 15));
			}
			csv.add(sb.toString());
		}
	}

	@Benchmark
	@Threads(1)
	public long split_1()
	{
		return split();
	}

	@Benchmark
	@Threads(2)
	public long split_2()
	{
		return split();
	}

	@Benchmark
	@Threads(4)
	public long split_4()
	{
		return split();
	}

	@Benchmark
	@Threads(6)
	public long split_6()
	{
		return split();
	}

	@Benchmark
	@Threads(8)
	public long split_8()
	{
		return split();
	}

	public long split()
	{
		long count = 0;
		for (var s : csv)
		{
			count += s.split(",").length;
		}
		return count;
	}
}

