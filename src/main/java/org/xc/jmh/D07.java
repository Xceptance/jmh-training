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
 * Pick strings from a random set and hold the set for memory
 * fragmentation
 */
//	Benchmark                            Mode  Cnt     Score     Error   Units
//	D07.classic                          avgt    3    38.850 ±   6.792   ns/op
//	D07.classic:gc.alloc.rate            avgt    3  2355.784 ± 413.465  MB/sec
//	D07.classic:gc.alloc.rate.norm       avgt    3    96.000 ±   0.001    B/op
//	D07.classic:gc.count                 avgt    3    19.000            counts
//	D07.classic:gc.time                  avgt    3    12.000                ms

//	D07.builder                          avgt    3    71.630 ±  14.507   ns/op
//	D07.builder:gc.alloc.rate            avgt    3  3940.151 ± 794.551  MB/sec
//	D07.builder:gc.alloc.rate.norm       avgt    3   296.000 ±   0.001    B/op
//	D07.builder:gc.count                 avgt    3    27.000            counts
//	D07.builder:gc.time                  avgt    3    19.000                ms

//	D07.sizedBuilder                     avgt    3    50.445 ±   1.626   ns/op
//	D07.sizedBuilder:gc.alloc.rate       avgt    3  4082.388 ± 130.663  MB/sec
//	D07.sizedBuilder:gc.alloc.rate.norm  avgt    3   216.000 ±   0.001    B/op
//	D07.sizedBuilder:gc.count            avgt    3    29.000            counts
//	D07.sizedBuilder:gc.time             avgt    3    19.000                ms

@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1, jvmArgsAppend = "-Xms4g -Xmx4g -XX:+AlwaysPreTouch -XX:+UseG1GC")
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class D07
{
	String a, b, c;
	long d;
	List<String> strings = new ArrayList<>(10000);

	@Setup
	public void setup()
	{
		var r = new FastRandom(42);
		for (int i = 0; i < 10000; i++)
		{
			strings.add(RandomUtils.randomString(r, 5 + r.nextInt(15)));
		}

		a = strings.get(r.nextInt(strings.size()));
		b = strings.get(r.nextInt(strings.size()));
		c = strings.get(r.nextInt(strings.size()));
		d = System.currentTimeMillis();
	}

	@Benchmark
	public String classic()
	{
		return a + b + c + d;
	}

	@Benchmark
	public String builder()
	{
		var sb = new StringBuilder();
		sb.append(a).append(b).append(c).append(d);
		return sb.toString();
	}

	@Benchmark
	public String sizedBuilder()
	{
		var sb = new StringBuilder(3 * 20 + 13);
		sb.append(a).append(b).append(c).append(d);
		return sb.toString();
	}
}

