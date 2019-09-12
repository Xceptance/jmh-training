package org.xceptance.training.jmh;

import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

/**
 * The orignal code without list but with the Training35 data
 * 
 * @author rschwietzke
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Thread) // ignore for now
public class Training35a
{
    final Pattern precompiledPattern = Pattern.compile(",");
    String toSplit = "Go,Java,Rust,C";
    
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


     */
}
