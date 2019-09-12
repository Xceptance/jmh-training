package org.xceptance.training.jmh;

import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

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
import org.openjdk.jmh.infra.Blackhole;

/**
 * Test two dimensions at once
 * 
 * @author rschwietzke
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Thread) // ignore for now
public class Training32
{
    @Param({"1", "5", "10", "20"})
    int size; 

    @Param({"1", "2", "5", "10"})
    int length; 
    
    final Pattern precompiledPattern = Pattern.compile(",");

    String toSplit;

    @Setup
    public void setup()
    {
        String s = "";
        for (int i = 0; i < length; i++)
        {
            s += "a";
        }
        
        final StringJoiner sj = new StringJoiner(",");
        for (int i = 0; i < size; i++)
        {
            sj.add(s);
        }
        
        toSplit = sj.toString();
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


     */
}
