package org.xc.jmh;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

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
import org.openjdk.jmh.infra.Blackhole;

/**
 * Test several ways to split up strings. We are not implementing our own
 * solution for the moment, rather use what is provided. We want to split
 * a string by a String as single char
 */
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1, jvmArgsAppend = {"-Xms4g", "-Xmx4g", "-XX:+AlwaysPreTouch"})
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class D11
{
	String data = "foo,bar,bar,test,xmas";
	String del = ",";

	@Setup
	public void setup()
	{
		data = new String(data + data + data + data);
	}

	@Benchmark
	public void split(final Blackhole bj)
	{
		bj.consume(D11.split(data,  del));
	}

	@Benchmark
	public void tokenizer(final Blackhole bj)
	{
		bj.consume(D11.tokenizer(data,  del));
	}

	@Benchmark
	public void regex(final Blackhole bj)
	{
		bj.consume(D11.regex(data,  del));
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

	public static String[] regex(String s, String d)
	{
		// split using
		return Pattern.compile(s).split(d);
	}
}

