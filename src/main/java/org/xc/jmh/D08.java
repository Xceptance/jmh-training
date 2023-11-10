package org.xc.jmh;

import java.util.ArrayList;
import java.util.List;
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
import org.xc.jmh.util.FastRandom;
import org.xc.jmh.util.RandomUtils;

/**
 * Use loops for building a string based on array data
 */
//	Benchmark                            Mode  Cnt     Score     Error   Units
//	D08.classic                          avgt    3   165.559 ±   3.441   ns/op
//	D08.classic:gc.alloc.rate            avgt    3  5481.654 ± 125.365  MB/sec
//	D08.classic:gc.alloc.rate.norm       avgt    3   952.000 ±   0.001    B/op
//	D08.classic:gc.count                 avgt    3    28.000            counts
//	D08.classic:gc.time                  avgt    3    23.000                ms

//	D08.builder                          avgt    3   123.831 ±   8.594   ns/op
//	D08.builder:gc.alloc.rate            avgt    3  3694.866 ± 264.751  MB/sec
//	D08.builder:gc.alloc.rate.norm       avgt    3   480.000 ±   0.001    B/op
//	D08.builder:gc.count                 avgt    3    26.000            counts
//	D08.builder:gc.time                  avgt    3    18.000                ms

//	D08.sizedBuilder                     avgt    3    84.339 ±  20.898   ns/op
//	D08.sizedBuilder:gc.alloc.rate       avgt    3  2984.400 ± 737.765  MB/sec
//	D08.sizedBuilder:gc.alloc.rate.norm  avgt    3   264.000 ±   0.001    B/op
//	D08.sizedBuilder:gc.count            avgt    3    21.000            counts
//	D08.sizedBuilder:gc.time             avgt    3    13.000                ms

@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1, jvmArgsAppend = "-Xms4g -Xmx4g -XX:+AlwaysPreTouch -XX:+UseG1GC")
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class D08
{
	List<String> strings = new ArrayList<>();
	final int SIZE = 10;
	final int LENGTH = 10;

	@Setup
	public void setup()
	{
		var r = new FastRandom(42);
		for (int i = 0; i < SIZE; i++)
		{
			strings.add(RandomUtils.randomString(r, LENGTH));
		}
	}

	@Benchmark
	public String classic()
	{
		String result = "";
		for (int i = 0; i < SIZE; i++)
		{
			result += strings.get(i);
		}
		return result;
	}

	@Benchmark
	public String builder()
	{
		var sb = new StringBuilder();
		for (int i = 0; i < SIZE; i++)
		{
			sb.append(strings.get(i));
		}
		return sb.toString();
	}

	@Benchmark
	public String sizedBuilder()
	{
		var sb = new StringBuilder(SIZE * LENGTH);
		for (int i = 0; i < SIZE; i++)
		{
			sb.append(strings.get(i));
		}
		return sb.toString();
	}
}

