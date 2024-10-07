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

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 3, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 2, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class Strings1 {
    private static final String PREFIX = "a";
    private String foo = null;
    private int LENGTH;

    @Setup
    public void setup() {
        //                        13 chars                  18 chars
        foo = String.valueOf(System.currentTimeMillis()) + "ashdfhgas dfhsa df";
        LENGTH = plain().length();
    }

    @Benchmark
    public String plain() {
        return PREFIX + foo;
    }

    @Benchmark
    public String concat() {
        return PREFIX.concat(foo);
    }

    @Benchmark
    public String builder() {
        return new StringBuilder().append(PREFIX).append(foo).toString();
    }

    @Benchmark
    public String builderSized() {
        return new StringBuilder(LENGTH).append(PREFIX).append(foo).toString();
    }

    @Benchmark
    public String builderNonFluid() {
        final StringBuilder sb = new StringBuilder();
        sb.append(PREFIX);
        sb.append(foo);
        return sb.toString();
    }

    @Benchmark
    public String builderNonFluidSized() {
        final StringBuilder sb = new StringBuilder(LENGTH);
        sb.append(PREFIX);
        sb.append(foo);
        return sb.toString();
    }
}