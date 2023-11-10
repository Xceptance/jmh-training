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
 * Basic version, nothing sophisticated
 */
//Benchmark                            Mode  Cnt     Score     Error   Units
//D02.classic                          avgt    3    17.586 ±   3.767   ns/op
//D02.classic:gc.alloc.rate            avgt    3  3036.203 ± 652.039  MB/sec
//D02.classic:gc.alloc.rate.norm       avgt    3    56.000 ±   0.001    B/op
//D02.classic:gc.count                 avgt    3    19.000            counts
//D02.classic:gc.time                  avgt    3    14.000                ms
//
//D02.builder                          avgt    3    22.631 ±   2.087   ns/op
//D02.builder:gc.alloc.rate            avgt    3  3706.970 ± 331.384  MB/sec
//D02.builder:gc.alloc.rate.norm       avgt    3    88.000 ±   0.001    B/op
//D02.builder:gc.count                 avgt    3    25.000            counts
//D02.builder:gc.time                  avgt    3    21.000                ms
//
//D02.sizedBuilder                     avgt    3    23.692 ±   3.118   ns/op
//D02.sizedBuilder:gc.alloc.rate       avgt    3  3541.279 ± 466.683  MB/sec
//D02.sizedBuilder:gc.alloc.rate.norm  avgt    3    88.000 ±   0.001    B/op
//D02.sizedBuilder:gc.count            avgt    3    24.000            counts
//D02.sizedBuilder:gc.time             avgt    3    16.000                ms

@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1, jvmArgsAppend = "-Xms4g -Xmx4g -XX:+AlwaysPreTouch -XX:+UseSerialGC")
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class D02
{
	String a, b, c;

	@Setup
	public void setup()
	{
		a = b = c = "test";
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
		var sb = new StringBuilder(3 * 4);
		sb.append(a);
		sb.append(b);
		sb.append(c);
		return sb.toString();
	}
}

