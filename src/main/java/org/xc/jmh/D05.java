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
 * Overprovision the sized one
 */
//	Benchmark                            Mode  Cnt     Score     Error   Units
//	D05.classic                          avgt    3    38.565 ±   3.472   ns/op
//	D05.classic:gc.alloc.rate            avgt    3  2175.652 ± 197.139  MB/sec
//	D05.classic:gc.alloc.rate.norm       avgt    3    88.000 ±   0.001    B/op
//	D05.classic:gc.count                 avgt    3    19.000            counts
//	D05.classic:gc.time                  avgt    3    16.000                ms

//	D05.builder                          avgt    3    69.304 ±  11.692   ns/op
//	D05.builder:gc.alloc.rate            avgt    3  3960.716 ± 634.450  MB/sec
//	D05.builder:gc.alloc.rate.norm       avgt    3   288.000 ±   0.001    B/op
//	D05.builder:gc.count                 avgt    3    28.000            counts
//	D05.builder:gc.time                  avgt    3    25.000                ms

//	D05.sizedBuilder                     avgt    3    53.869 ±   9.066   ns/op
//	D05.sizedBuilder:gc.alloc.rate       avgt    3  5804.981 ± 978.373  MB/sec
//	D05.sizedBuilder:gc.alloc.rate.norm  avgt    3   328.000 ±   0.001    B/op
//	D05.sizedBuilder:gc.count            avgt    3    31.000            counts
//	D05.sizedBuilder:gc.time             avgt    3    20.000                ms

@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1, jvmArgsAppend = "-Xms4g -Xmx4g -XX:+AlwaysPreTouch -XX:+UseG1GC")
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class D05
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
		var sb = new StringBuilder(200);
		sb.append(a);
		sb.append(b);
		sb.append(c);
		sb.append(d);
		return sb.toString();
	}
}

