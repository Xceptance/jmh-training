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
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

/**
 * Test per size to make comparison of impact easier
 * 
 * @author rschwietzke
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Thread) // ignore for now
public class Training31
{
    @Param({"1", "5", "10", "20"})
    int size; 
    
    final Pattern precompiledPattern = Pattern.compile(",");

    final String[] data = {
            "foo",
            "foo,bar,buz,top,tof,rot",
            "foo,bar,buz,top,tof,rot,qwe,pop,lol,aha",
            "foo,bar,buz,top,tof,rot,qwe,pop,lol,aha,foo,bar,buz,top,tof,rot,qwe,pop,lol,aha"
    };
    
    String toSplit;

    @Setup
    public void setup()
    {
        switch (size)
        {
            case 1: toSplit = data[0]; break;
            case 5: toSplit = data[1]; break;
            case 10: toSplit = data[2]; break;
            case 20: toSplit = data[3]; break;
        };
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
      * Benchmark                      (size)  Mode  Cnt     Score     Error  Units
        Training31.pattern                  1  avgt    3   200.189 ±   6.953  ns/op
        Training31.pattern                  5  avgt    3   538.680 ±  32.135  ns/op
        Training31.pattern                 10  avgt    3   744.355 ±  16.465  ns/op
        Training31.pattern                 20  avgt    3  1282.833 ± 324.866  ns/op
        Training31.precompiledPattern       1  avgt    3    76.720 ±   3.313  ns/op
        Training31.precompiledPattern       5  avgt    3   401.606 ±  18.173  ns/op
        Training31.precompiledPattern      10  avgt    3   608.551 ±  63.168  ns/op
        Training31.precompiledPattern      20  avgt    3  1142.627 ± 250.676  ns/op
        Training31.precompiledStream        1  avgt    3   121.961 ±   3.063  ns/op
        Training31.precompiledStream        5  avgt    3   366.316 ±  82.058  ns/op
        Training31.precompiledStream       10  avgt    3   555.740 ± 122.426  ns/op
        Training31.precompiledStream       20  avgt    3   987.757 ±  64.883  ns/op
        Training31.split                    1  avgt    3    17.935 ±   1.316  ns/op
        Training31.split                    5  avgt    3   256.743 ±   4.131  ns/op
        Training31.split                   10  avgt    3   380.809 ±  19.366  ns/op
        Training31.split                   20  avgt    3   763.911 ±  26.348  ns/op
        Training31.stream                   1  avgt    3   244.671 ±  39.745  ns/op
        Training31.stream                   5  avgt    3   507.201 ±  37.486  ns/op
        Training31.stream                  10  avgt    3   727.975 ±  61.196  ns/op
        Training31.stream                  20  avgt    3  1176.916 ± 807.481  ns/op
        Training31.tokenize                 1  avgt    3    10.574 ±   0.655  ns/op
        Training31.tokenize                 5  avgt    3   167.419 ±   3.196  ns/op
        Training31.tokenize                10  avgt    3   302.596 ±   8.959  ns/op
        Training31.tokenize                20  avgt    3   579.401 ± 207.446  ns/op
     */
}
