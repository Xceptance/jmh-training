package org.xc.jmh;

import java.util.Random;
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
@Warmup(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 2000, timeUnit = TimeUnit.MILLISECONDS)
@Fork(1)
public class Strings2
{
    private String[] foo = new String[5];
    private Random r = new Random(1L);

    @Setup
    public void setup()
    {
        foo[0] = String.valueOf(r.nextInt()) + "ashdfhgas dfhsa df";
        foo[1] = String.valueOf(r.nextInt()) + "hsa df";
        foo[2] = String.valueOf(r.nextInt()) + "fhsa df";
        foo[3] = String.valueOf(r.nextInt()) + "as dfhsa df";
        foo[4] = String.valueOf(r.nextInt())
                        + "ashdfhgas dfhsa hsajdhf kjahskjdh fkjhsa dhfkjskhj hdf"
                        + "ashdfhgas dfhsa hsajdhf kjahs as87837 837 987kjskhj hdf";
    }

    public String foo()
    {
        return foo[r.nextInt(5)];
    }

    @Benchmark
    public String plain()
    {
        return "a" + foo();
    }

    @Benchmark
    public String concat()
    {
        return "a".concat(foo());
    }

    @Benchmark
    public String builder()
    {
        return new StringBuilder().append("a").append(foo()).toString();
    }

    @Benchmark
    public String builderSized()
    {
        return new StringBuilder(128).append("a").append(foo()).toString();
    }

    @Benchmark
    public String builderNonFluid()
    {
         final StringBuilder sb = new StringBuilder();
         sb.append("a");
         sb.append(foo());
         return sb.toString();
    }

    @Benchmark
    public String builderNonFluidSized()
    {
        final StringBuilder sb = new StringBuilder(128);
        sb.append("a");
        sb.append(foo());
        return sb.toString();
    }
}