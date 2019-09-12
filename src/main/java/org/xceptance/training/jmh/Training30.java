package org.xceptance.training.jmh;

import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.xceptance.util.FastRandom;

/**
 * Make data a factor
 * 
 * @author rschwietzke
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Thread) // ignore for now
public class Training30
{
    final Pattern precompiledPattern = Pattern.compile(",");
    final FastRandom r = new FastRandom(7L);

    final String[] data = {
            "foo",
            "foo,bar",
            "foo,bar,buz",
            "foo,bar,buz,top",
            "foo,bar,buz,top,tof",
            "foo,bar,buz,top,tof,rot",
            "foo,bar,buz,top,tof,rot,qwe",
            "foo,bar,buz,top,tof,rot,qwe,pop",
            "foo,bar,buz,top,tof,rot,qwe,pop,lol",
            "foo,bar,buz,top,tof,rot,qwe,pop,lol,aha"
    };
    
    String toSplit;

    @Setup(Level.Invocation)
    public void setup()
    {
        toSplit = data[r.nextInt(data.length)];
    }
    
    @Benchmark
    public void precompiledPattern(final Blackhole b)
    {
        final String[] cols = precompiledPattern.split(toSplit);
        
        for (String s : cols)
        {
            b.consume(s);
        }
    }
    
    @Benchmark
    public void precompiledStream(final Blackhole b)
    {
        precompiledPattern.splitAsStream(toSplit).forEach(s -> b.consume(s));
    }

     @Benchmark
     public void pattern(final Blackhole b)
     {
         final Pattern p = Pattern.compile(",");
         final String[] cols = p.split(toSplit);
         
         for (String s : cols)
         {
             b.consume(s);
         }
     }

     @Benchmark
     public void split(final Blackhole b)
     {
         final String[] cols = toSplit.split(",");
         
         for (String s : cols)
         {
             b.consume(s);
         }
     }

     @Benchmark
     public void tokenize(final Blackhole b)
     {
         final StringTokenizer st = new StringTokenizer(toSplit, ",");
         
         while (st.hasMoreTokens())
         {
             b.consume(st.nextToken());
         }
     }

     @Benchmark
     public void stream(final Blackhole b)
     {
         final Pattern p = Pattern.compile(",");
         p.splitAsStream(toSplit).forEach(s -> b.consume(s));
     }
     
     /**
      * Command line
      * 
      * mvn clean compile install; java -jar target/benchmarks.jar Training20
      */
     
     /**
      * Results
      * 
        Benchmark                      Mode  Cnt    Score    Error  Units
        Training30.pattern             avgt    3  587.557 ± 74.707  ns/op
        Training30.precompiledPattern  avgt    3  441.839 ± 49.530  ns/op
        Training30.precompiledStream   avgt    3  494.524 ± 54.549  ns/op
        Training30.split               avgt    3  272.838 ± 37.578  ns/op
        Training30.stream              avgt    3  648.489 ± 18.975  ns/op
        Training30.tokenize            avgt    3  246.168 ± 51.308  ns/op

     */
}
