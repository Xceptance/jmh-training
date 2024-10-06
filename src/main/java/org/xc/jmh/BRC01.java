package org.xc.jmh;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.xc.jmh.util.ParseDouble;

/**
 * Just some BRC number parsing fun
 */
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1, jvmArgsAppend = {"-Xms4g", "-Xmx4g", "-XX:+AlwaysPreTouch"})
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class BRC01
{
	final int SIZE = 10_000;
	List<String> sNumbers = new ArrayList<>(SIZE);
	List<byte[]> bNumbers = new ArrayList<>(SIZE);

	@Setup
	public void setup()
	{
		var r = new Random(4242L);
		for (int i = 0; i < SIZE; i++)
		{
			var a = r.nextInt(199) - 99; // -99 to 99
			var b = r.nextInt(10); // 0 to 9
			var s = String.valueOf(a) + "." + String.valueOf(b);
			sNumbers.add(s);
			bNumbers.add(s.getBytes());
		}
	}

	@Benchmark
	@OperationsPerInvocation(SIZE)
	public double classic()
	{
		double t = 1;
		for (int i = 0; i < SIZE; i++)
		{
			t += Double.parseDouble(sNumbers.get(i));
		}
		return t;
	}

	@Benchmark
	@OperationsPerInvocation(SIZE)
	public double ownParseDouble()
	{
		double t = 1d;
		for (int i = 0; i < SIZE; i++)
		{
			var s = sNumbers.get(i);
			t += ParseDouble.parseDouble(s, 0, s.length() - 1);
		}
		return t;
	}

	@Benchmark
	@OperationsPerInvocation(SIZE)
	public int parseToIntFromString()
	{
		int t = 1;
		for (int i = 0; i < SIZE; i++)
		{
			var s = sNumbers.get(i);
			t += ParseDouble.parseInteger(s, 0, s.length() - 1);
		}
		return t;
	}

	@Benchmark
	@OperationsPerInvocation(SIZE)
	public int parseToIntFromByte()
	{
		int t = 1;
		for (int i = 0; i < SIZE; i++)
		{
			var s = bNumbers.get(i);
			t += ParseDouble.parseInteger(s, 0, s.length - 1);
		}
		return t;
	}
}

