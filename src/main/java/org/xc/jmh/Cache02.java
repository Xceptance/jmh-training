package org.xc.jmh;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class Cache02
{
	@Param({"10", "1000", "10000"}) int SIZE;

    @Param({"true", "false"}) boolean RANDOM = false;

	int totalData = 10_000;
	int counter = 0;

	final List<List<String>> data = new ArrayList<>(SIZE);

	String[] CITIES = {"Berlin;", "Hannover;", "Prag;", "Rio;", "Hamburg;", "Paris;", "Rotterdam;"};
	String[] TEMPERATURES = {"-99.9", "99.9", "3.4", "-12.2", "22.2", "26.8", "31.1", "11.0", "-5.6"};

	@Setup(Level.Trial)
	public void setupTrial()
	{
		data.clear();
		final var r = new Random(7L);

		for (int j = 0; j < totalData; j++)
		{
			final List<String> d = new ArrayList<>(SIZE);
			for (int i = 0; i < SIZE; i++)
			{
				d.add(CITIES[r.nextInt(CITIES.length)] + TEMPERATURES[r.nextInt(TEMPERATURES.length)]);
			}
			data.add(d);
		}

		if (RANDOM)
		{
			Collections.shuffle(data);
		}
	}

	@Setup(Level.Iteration)
	public void setupIteration()
	{
		counter = 0;
	}

	@Benchmark
	public double cacheHit()
	{
		var d = data.get(0);

		var total = 0d;
		for (int i = 0; i < d.size(); i++)
		{
			var s = d.get(i);
			var pos = s.indexOf(';');
			total += pos + s.length(); // .parseDouble(s.substring(pos + 1));
		}

		return total;
	}

	@Benchmark
	public double cacheMiss()
	{
		var d = data.get(counter++ % totalData);

		var total = 0d;
		for (int i = 0; i < d.size(); i++)
		{
			var s = d.get(i);
			var pos = s.indexOf(';');
			total += pos + s.length(); // .parseDouble(s.substring(pos + 1));
		}

		return total;
	}
}
