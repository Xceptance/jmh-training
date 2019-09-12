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
 * Our four methods, remember the pattern
 * 
 * @author rschwietzke
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Thread) // ignore for now
public class Training21
{
    final String toSplit = "foo,bar,mars,sun";
    final Pattern precompiledPattern = Pattern.compile(",");

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
        Benchmark                      Mode  Cnt    Score      Error  Units
        Training21.pattern             avgt    3  593.055 ± 1549.983  ns/op
        Training21.precompiledPattern  avgt    3  493.901 ±  275.786  ns/op
        Training21.precompiledStream   avgt    3  414.897 ±  853.059  ns/op
        Training21.split               avgt    3  209.599 ±  277.852  ns/op
        Training21.stream              avgt    3  560.167 ±  662.601  ns/op
        Training21.tokenize            avgt    3  214.652 ±  346.167  ns/op



     */
}
