package org.xc.jmh;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

/**
 * Test several ways to split up strings. We are not implementing our own
 * solution for the moment, rather use what is provided. We want to split
 * a string by a String as single char
 *
 */
//	Benchmark            (SIZE)  Mode  Cnt    Score    Error  Units
//	D12.apacheTokenizer       1  avgt    3   46.281 ± 10.668  ns/op
//	D12.apacheTokenizer       2  avgt    3   77.025 ±  8.153  ns/op
//	D12.apacheTokenizer       4  avgt    3  115.209 ±  7.373  ns/op
//	D12.apacheTokenizer       8  avgt    3  179.966 ± 18.193  ns/op
//	D12.apacheTokenizer      16  avgt    3  423.845 ± 41.339  ns/op
//	D12.apacheTokenizer      32  avgt    3  827.045 ± 91.573  ns/op
//	D12.regex                 1  avgt    3   25.773 ±  2.771  ns/op
//	D12.regex                 2  avgt    3   76.907 ± 29.403  ns/op
//	D12.regex                 4  avgt    3  134.178 ±  8.821  ns/op
//	D12.regex                 8  avgt    3  282.400 ± 50.161  ns/op
//	D12.regex                16  avgt    3  501.458 ± 23.216  ns/op
//	D12.regex                32  avgt    3  931.818 ± 54.199  ns/op
//	D12.split                 1  avgt    3   11.151 ±  1.318  ns/op
//	D12.split                 2  avgt    3   50.511 ±  4.701  ns/op
//	D12.split                 4  avgt    3   79.199 ±  6.807  ns/op
//	D12.split                 8  avgt    3  141.804 ± 41.213  ns/op
//	D12.split                16  avgt    3  270.680 ±  9.016  ns/op
//	D12.split                32  avgt    3  530.629 ± 28.752  ns/op
//	D12.tokenizer             1  avgt    3   23.866 ±  1.325  ns/op
//	D12.tokenizer             2  avgt    3   59.040 ±  4.144  ns/op
//	D12.tokenizer             4  avgt    3  104.078 ±  9.354  ns/op
//	D12.tokenizer             8  avgt    3  186.534 ± 15.183  ns/op
//	D12.tokenizer            16  avgt    3  385.389 ± 32.311  ns/op
//	D12.tokenizer            32  avgt    3  783.273 ± 27.180  ns/op

@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1, jvmArgsAppend = {"-Xms4g", "-Xmx4g", "-XX:+AlwaysPreTouch"})
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class D12
{
	@Param({"1", "2", "4", "8", "16", "32"})
	int SIZE;
	String BASE = "any";
	String data;
	String del = ",";
	Pattern delPattern;

	@Setup
	public void setup()
	{
		data = IntStream.range(0, SIZE).mapToObj(i -> BASE).collect(Collectors.joining(","));
		System.out.println(data);
		delPattern = Pattern.compile(del);
	}

	@Benchmark
	public void split(final Blackhole bj)
	{
		bj.consume(D12.split(data,  del));
	}

	@Benchmark
	public void tokenizer(final Blackhole bj)
	{
		bj.consume(D12.tokenizer(data,  del));
	}

	@Benchmark
	public void regex(final Blackhole bj)
	{
		bj.consume(D12.regex(data, delPattern));
	}

	@Benchmark
	public void apacheTokenizer(final Blackhole bj)
	{
		bj.consume(D12.apacheTokenizer(data,  del));
	}

	public static String[] split(String s, String d)
	{
		return s.split(d);
	}

	public static String[] tokenizer(String s, String d)
	{
		var st = new StringTokenizer(s, d);
		var l = new ArrayList<String>();
		while (st.hasMoreTokens())
		{
			l.add(st.nextToken());
		}
		var result = new String[l.size()];
		return l.toArray(result);
	}

	public static String[] regex(String s, Pattern d)
	{
		return d.split(s);
	}

	public static String[] apacheTokenizer(String s, String d)
	{
		var st = new org.apache.commons.text.StringTokenizer(s, d);
		return st.getTokenArray();
	}
}

