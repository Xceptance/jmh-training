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
 * See the compile difference
 * java -jar target/benchmarks.jar "A04c.addShort" -jvmArgsAppend "-XX:+PrintCompilation"
 * java -jar target/benchmarks.jar "A04c.addLong" -jvmArgsAppend "-XX:+PrintCompilation"
 */
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class A04c
{
	long time;
	Long lTime;

	@Setup
	public void setup()
	{
		time = System.currentTimeMillis();
		lTime = Long.valueOf(time);
	}

	@Warmup(iterations = 2, time = 500, timeUnit = TimeUnit.MILLISECONDS)
	@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
	@Benchmark
	public long addShort()
	{
		return 1 + time;
	}

	@Warmup(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
	@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
	@Benchmark
	public long addLong()
	{
		return 1 + time;
	}

}
