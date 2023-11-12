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
import org.openjdk.jmh.infra.Blackhole;
import org.xc.jmh.helper.CharHasher;

/**
 * Compare three hashcode versions to hash a char
 * This code has not made it into JDK 19 but an intrinsified version
 * which is also used for other hashes
 */

//	Benchmark          Mode  Cnt  Score   Error  Units
//	D20.classic        avgt    3  3.561 ± 0.569  ns/op
//	D20.classicNotMul  avgt    3  3.553 ± 0.843  ns/op
//	D20.jdk19          avgt    3  3.478 ± 0.054  ns/op

@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1, jvmArgsAppend = {"-Xms4g", "-Xmx4g", "-XX:+AlwaysPreTouch"})
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class D20
{
	@Setup
	public void setup()
	{

	}

	@Benchmark
	public void classic(final Blackhole bj)
	{
		CharHasher.hashCodeClassic("foobar".toCharArray());
	}

	@Benchmark
	public void classicNotMul(final Blackhole bj)
	{
		CharHasher.hashCodeNoMul("foobar".toCharArray());
	}

	@Benchmark
	public void jdk19(final Blackhole bj)
	{
		CharHasher.hashCodeVectoredJDK19("foobar".toCharArray());
	}
}

