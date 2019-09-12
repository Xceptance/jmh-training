package org.xceptance.training.jmh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
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
 * Ok, focus on what is great and see if this is usable when 
 * the server comes up aka cold. Touch the classes before use
 * to assume we are not the first who need it, only our code is 
 * cold
 * 
 * @author rschwietzke
 */
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.SingleShotTime)
@Fork(10)
@State(Scope.Thread) // ignore for now
public class Training41
{
    private final String toSplit = "Go,Java,Rust,C";
    public String hold;
    
    @Setup
    public void setup()
    {
        // use String.spllt, but differently from us
        String[] result = "Test:Test".split(":");
        
        // use Tokenizer
        StringTokenizer st = new StringTokenizer(":");
        
        // ensure we are not removed
        hold = st.toString() + result;
    }
    
    private List<String> split(final String s)
    {
        final String[] cols = s.split(",");
        return Arrays.asList(cols);
    }
    
    private List<String> tokenize(final String s)
    {
        final StringTokenizer st = new StringTokenizer(s, ",");
        final List<String> result = new ArrayList<>();

        while (st.hasMoreTokens())
        {
            result.add(st.nextToken());
        }

        return result;
    }

    @Benchmark
    @Warmup(iterations = 0)
    @Measurement(iterations = 1)
    public List<String> tokenizeIceCold()
    {
        return tokenize(toSplit);
    }

    @Benchmark
    @Warmup(iterations = 0)
    @Measurement(iterations = 10)
    public List<String> tokenizeCold()
    {
        return tokenize(toSplit);
    }

    @Benchmark
    @Warmup(iterations = 10)
    @Measurement(iterations = 10)
    public List<String> tokenizeWarm()
    {
        return tokenize(toSplit);
    }

    @Benchmark
    @Warmup(iterations = 100)
    @Measurement(iterations = 10)
    public List<String> tokenizeWarmer()
    {
        return tokenize(toSplit);
    }

    @Benchmark
    @Warmup(iterations = 10000)
    @Measurement(iterations = 10)
    public List<String> tokenizeHot()
    {
        return tokenize(toSplit);
    }    

    @Benchmark
    @Warmup(iterations = 0)
    @Measurement(iterations = 1)
    public List<String> splitIceCold()
    {
        return split(toSplit);
    }
    
    @Benchmark
    @Warmup(iterations = 0)
    @Measurement(iterations = 10)
    public List<String> splitCold()
    {
        return split(toSplit);
    }

    @Benchmark
    @Warmup(iterations = 10)
    @Measurement(iterations = 10)
    public List<String> splitWarm()
    {
        return split(toSplit);
    }

    @Benchmark
    @Warmup(iterations = 100)
    @Measurement(iterations = 10)
    public List<String> splitWarmer()
    {
        return split(toSplit);
    }

    @Benchmark
    @Warmup(iterations = 10000)
    @Measurement(iterations = 10)
    public List<String> splitHot()
    {
        return split(toSplit);
    } 
    
    /**
     * Results
     * 
     */
}
