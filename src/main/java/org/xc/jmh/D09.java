package org.xc.jmh;

import java.util.ArrayList;
import java.util.List;
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
import org.xc.jmh.util.FastRandom;
import org.xc.jmh.util.RandomUtils;

/**
 * Try size combinations
 */
//	Benchmark         (LENGTH)  (SIZE)  Mode  Cnt        Score        Error  Units
//	D09.classic              2      10  avgt    3      161.931 ±     29.449  ns/op
//	D09.classic              2     100  avgt    3     2014.493 ±     38.313  ns/op
//	D09.classic              2    1000  avgt    3   135081.349 ±   8723.749  ns/op
//	D09.classic             10      10  avgt    3      175.512 ±     22.154  ns/op
//	D09.classic             10     100  avgt    3     6400.353 ±    894.713  ns/op
//	D09.classic             10    1000  avgt    3   560486.952 ±  17028.894  ns/op
//	D09.classic            100      10  avgt    3      672.769 ±     16.308  ns/op
//	D09.classic            100     100  avgt    3    57644.693 ±   4047.099  ns/op
//	D09.classic            100    1000  avgt    3  5746165.427 ± 135734.112  ns/op
//
//	D09.builder              2      10  avgt    3       88.857 ±     20.482  ns/op
//	D09.builder              2     100  avgt    3      787.051 ±     12.118  ns/op
//	D09.builder              2    1000  avgt    3     8102.610 ±   1076.472  ns/op
//	D09.builder             10      10  avgt    3      113.822 ±      3.897  ns/op
//	D09.builder             10     100  avgt    3      971.233 ±    121.840  ns/op
//	D09.builder             10    1000  avgt    3    12258.610 ±   3868.321  ns/op
//	D09.builder            100      10  avgt    3      625.489 ±     51.419  ns/op
//	D09.builder            100     100  avgt    3     4742.832 ±    221.032  ns/op
//	D09.builder            100    1000  avgt    3    40440.797 ±   6638.784  ns/op
//
//	D09.sizedBuilder         2      10  avgt    3       84.767 ±     46.554  ns/op
//	D09.sizedBuilder         2     100  avgt    3      761.503 ±     98.383  ns/op
//	D09.sizedBuilder         2    1000  avgt    3     7603.698 ±   2081.241  ns/op
//	D09.sizedBuilder        10      10  avgt    3       90.040 ±      5.363  ns/op
//	D09.sizedBuilder        10     100  avgt    3      937.419 ±    150.019  ns/op
//	D09.sizedBuilder        10    1000  avgt    3     9968.026 ±    449.801  ns/op
//	D09.sizedBuilder       100      10  avgt    3      288.770 ±     16.691  ns/op
//	D09.sizedBuilder       100     100  avgt    3     2804.930 ±    659.456  ns/op
//	D09.sizedBuilder       100    1000  avgt    3    29160.424 ±   4047.001  ns/op

@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1, jvmArgsAppend = "-Xms4g -Xmx4g -XX:+AlwaysPreTouch -XX:+UseG1GC")
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class D09
{
	List<String> strings = new ArrayList<>();

	@Param({"10", "100", "1000"})
	int SIZE = 10;

	@Param({"2", "10", "100"})
	int LENGTH = 10;

	@Setup
	public void setup()
	{
		var r = new FastRandom(42);
		for (int i = 0; i < SIZE; i++)
		{
			strings.add(RandomUtils.randomString(r, LENGTH));
		}
	}

	@Benchmark
	public String classic()
	{
		String result = "";
		for (int i = 0; i < SIZE; i++)
		{
			result += strings.get(i);
		}
		return result;
	}

	@Benchmark
	public String builder()
	{
		var sb = new StringBuilder();
		for (int i = 0; i < SIZE; i++)
		{
			sb.append(strings.get(i));
		}
		return sb.toString();
	}

	@Benchmark
	public String sizedBuilder()
	{
		var sb = new StringBuilder(SIZE * LENGTH);
		for (int i = 0; i < SIZE; i++)
		{
			sb.append(strings.get(i));
		}
		return sb.toString();
	}
}

