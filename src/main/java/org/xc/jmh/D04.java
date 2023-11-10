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
import org.xc.jmh.util.FastRandom;
import org.xc.jmh.util.RandomUtils;

/**
 * Randomize the strings in length and add a long
 */
//	Benchmark                            Mode  Cnt     Score     Error   Units
//  D04.classic                          avgt    3    38.998 ±   0.647   ns/op
//  D04.classic:gc.alloc.rate            avgt    3  2151.222 ±  35.465  MB/sec
//  D04.classic:gc.alloc.rate.norm       avgt    3    88.000 ±   0.001    B/op
//  D04.classic:gc.count                 avgt    3    19.000            counts
//  D04.classic:gc.time                  avgt    3    16.000                ms
//	D04.builder                          avgt    3    74.137 ±   4.473   ns/op
//	D04.builder:gc.alloc.rate            avgt    3  3703.067 ± 222.368  MB/sec
//	D04.builder:gc.alloc.rate.norm       avgt    3   288.000 ±   0.001    B/op
//	D04.builder:gc.count                 avgt    3    26.000            counts
//	D04.builder:gc.time                  avgt    3    23.000                ms
//	D04.sizedBuilder                     avgt    3    54.718 ±   3.803   ns/op
//	D04.sizedBuilder:gc.alloc.rate       avgt    3  3624.191 ± 251.573  MB/sec
//	D04.sizedBuilder:gc.alloc.rate.norm  avgt    3   208.000 ±   0.001    B/op
//	D04.sizedBuilder:gc.count            avgt    3    21.000            counts
//	D04.sizedBuilder:gc.time             avgt    3    17.000                ms

@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1, jvmArgsAppend = "-Xms4g -Xmx4g -XX:+AlwaysPreTouch -XX:+UseSerialGC")
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class D04
{
	String a, b, c;
	long d;

	@Setup
	public void setup()
	{
		var r = new FastRandom(42);
		a = RandomUtils.randomString(r, 5 + r.nextInt(15));
		b = RandomUtils.randomString(r, 5 + r.nextInt(15));
		c = RandomUtils.randomString(r, 5 + r.nextInt(15));
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
		sb.append(a);
		sb.append(b);
		sb.append(c);
		sb.append(d);
		return sb.toString();
	}

	@Benchmark
	public String sizedBuilder()
	{
		var sb = new StringBuilder(3 * 20 + 13);
		sb.append(a);
		sb.append(b);
		sb.append(c);
		sb.append(d);
		return sb.toString();
	}
}

