package org.xc.jmh;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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

/**
 * This is horribly wrong code!!!!
 */
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class B08
{
	@Param({"1", "10", "100", "1000"})
	int SIZE;
	List<String> data = new ArrayList<>(SIZE);

	@Setup
	public void setup()
	{
		var r = new Random(42);
		for (int i = 0; i < SIZE; i++)
		{
			data.add(String.valueOf(r.nextInt()));
		}
	}

	@Benchmark
	public List<String> sort()
	{
		Collections.sort(data);
		return data;
	}
}
