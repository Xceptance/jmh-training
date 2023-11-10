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

/**
 * Variation with just a longer string
 */
//	Benchmark                            Mode  Cnt     Score      Error   Units
//	D03.classic                          avgt    3    17.729 ±    3.656   ns/op
//	D03.classic:gc.alloc.rate            avgt    3  3441.370 ±  707.945  MB/sec
//	D03.classic:gc.alloc.rate.norm       avgt    3    64.000 ±    0.001    B/op
//	D03.classic:gc.count                 avgt    3    24.000             counts
//	D03.classic:gc.time                  avgt    3    16.000                 ms

//	D03.builder                          avgt    3    33.197 ±    3.378   ns/op
//	D03.builder:gc.alloc.rate            avgt    3  4365.135 ±  445.135  MB/sec
//	D03.builder:gc.alloc.rate.norm       avgt    3   152.000 ±    0.001    B/op
//	D03.builder:gc.count                 avgt    3    30.000             counts
//	D03.builder:gc.time                  avgt    3    20.000                 ms

//	D03.sizedBuilder                     avgt    3    25.098 ±    8.410   ns/op
//	D03.sizedBuilder:gc.alloc.rate       avgt    3  3951.304 ± 1323.356  MB/sec
//	D03.sizedBuilder:gc.alloc.rate.norm  avgt    3   104.000 ±    0.001    B/op
//	D03.sizedBuilder:gc.count            avgt    3    27.000             counts
//	D03.sizedBuilder:gc.time             avgt    3    17.000                 ms

@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1, jvmArgsAppend = "-Xms4g -Xmx4g -XX:+AlwaysPreTouch -XX:+UseSerialGC")
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class D03
{
	String a, b, c;

	@Setup
	public void setup()
	{
		a = b = c = "foobar42";
	}

	@Benchmark
	public String classic()
	{
		return a + b + c;
	}

	@Benchmark
	public String builder()
	{
		var sb = new StringBuilder();
		sb.append(a);
		sb.append(b);
		sb.append(c);
		return sb.toString();
	}

	@Benchmark
	public String sizedBuilder()
	{
		var sb = new StringBuilder(3 * 8);
		sb.append(a);
		sb.append(b);
		sb.append(c);
		return sb.toString();
	}
}

