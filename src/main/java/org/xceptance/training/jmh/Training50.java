package org.xceptance.training.jmh;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
 * Just to show how to debug and test
 * 
 * @author rschwietzke
 */
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@State(Scope.Thread) // ignore for now
public class Training50
{
    private String toSplit;

    @Setup
    @Before
    public void setup()
    {
        toSplit = "C,Rust,Java";
    }
    
    @Benchmark
    public List<String> split()
    {
        final String[] cols = toSplit.split(",");
        return Arrays.asList(cols);
    }
    
    @Test
    public void testSplit()
    {
        List<String> result = split();
        Assert.assertEquals(3, result.size());
        Assert.assertEquals("C", result.get(0));
        Assert.assertEquals("Rust", result.get(1));
        Assert.assertEquals("Java", result.get(2));
    }
    
    /**
     * Results
     * 
     */
}
