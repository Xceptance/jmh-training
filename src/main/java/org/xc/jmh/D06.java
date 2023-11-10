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
 * Write the append the usual way
 */
//	Benchmark                                 Mode  Cnt     Score      Error   Units
//  D06.classic                               avgt    3    39.171 ±    1.756   ns/op
//  D06.classic:gc.alloc.rate                 avgt    3  2141.901 ±   94.108  MB/sec
//  D06.classic:gc.alloc.rate.norm            avgt    3    88.000 ±    0.001    B/op
//  D06.classic:gc.count                      avgt    3    18.000             counts
//  D06.classic:gc.time                       avgt    3    12.000                 ms

//	D06.builder                               avgt    3    69.742 ±   11.188   ns/op
//	D06.builder:gc.alloc.rate                 avgt    3  3937.372 ±  627.425  MB/sec
//	D06.builder:gc.alloc.rate.norm            avgt    3   288.000 ±    0.001    B/op
//	D06.builder:gc.count                      avgt    3    26.000             counts
//	D06.builder:gc.time                       avgt    3    21.000                 ms

//	D06.builderFluid                          avgt    3    89.399 ±   28.167   ns/op
//	D06.builderFluid:gc.alloc.rate            avgt    3  3071.172 ±  974.155  MB/sec
//	D06.builderFluid:gc.alloc.rate.norm       avgt    3   288.000 ±    0.001    B/op
//	D06.builderFluid:gc.count                 avgt    3    21.000             counts
//	D06.builderFluid:gc.time                  avgt    3    14.000                 ms

//	D06.sizedBuilder                          avgt    3    56.953 ±    8.636   ns/op
//	D06.sizedBuilder:gc.alloc.rate            avgt    3  3481.812 ±  525.816  MB/sec
//	D06.sizedBuilder:gc.alloc.rate.norm       avgt    3   208.000 ±    0.001    B/op
//	D06.sizedBuilder:gc.count                 avgt    3    20.000             counts
//	D06.sizedBuilder:gc.time                  avgt    3    17.000                 ms

//	D06.sizedBuilderFluid                     avgt    3    50.325 ±   26.601   ns/op
//	D06.sizedBuilderFluid:gc.alloc.rate       avgt    3  3942.593 ± 2064.943  MB/sec
//	D06.sizedBuilderFluid:gc.alloc.rate.norm  avgt    3   208.000 ±    0.001    B/op
//	D06.sizedBuilderFluid:gc.count            avgt    3    27.000             counts
//	D06.sizedBuilderFluid:gc.time             avgt    3    20.000                 ms

@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1, jvmArgsAppend = "-Xms4g -Xmx4g -XX:+AlwaysPreTouch -XX:+UseG1GC")
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class D06
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

	@Benchmark
	public String builderFluid()
	{
		var sb = new StringBuilder();
		sb.append(a).append(b).append(c).append(d);
		return sb.toString();
	}

	@Benchmark
	public String sizedBuilderFluid()
	{
		var sb = new StringBuilder(3 * 20 + 13);
		sb.append(a).append(b).append(c).append(d);
		return sb.toString();
	}
}

