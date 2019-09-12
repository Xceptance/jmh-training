package org.xceptance.training.jmh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

/**
 * Make the code consistent aka List<String> as result, 
 * make the test case simpler to see the impact by
 * removing the dimensions
 * 
 * @author rschwietzke
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Thread) // ignore for now
public class Training35
{
    final Pattern precompiledPattern = Pattern.compile(",");
    String toSplit = "Go,Java,Rust,C";
    
    @Benchmark
    public List<String> precompiledPattern()
    {   
        final String[] cols = precompiledPattern.split(toSplit);
        return Arrays.asList(cols);
    }
    
    @Benchmark
    public List<String> precompiledStream()
    {
        return precompiledPattern.splitAsStream(toSplit).collect(Collectors.toList());
    }

     @Benchmark
     public List<String> pattern()
     {
         final Pattern p = Pattern.compile(",");
         final String[] cols = p.split(toSplit);
         
         return Arrays.asList(cols);
     }

     @Benchmark
     public List<String> split()
     {
         final String[] cols = toSplit.split(",");
         return Arrays.asList(cols);
     }

     @Benchmark
     public List<String> tokenize()
     {
         final StringTokenizer st = new StringTokenizer(toSplit, ",");
         final List<String> result = new ArrayList<>();
         
         while (st.hasMoreTokens())
         {
             result.add(st.nextToken());
         }
         
         return result;
     }

     @Benchmark
     public List<String> stream()
     {
         final Pattern p = Pattern.compile(",");
         return p.splitAsStream(toSplit).collect(Collectors.toList());
     }
     
     /**
      * Command line
      * 
      * mvn clean compile install; java -jar target/benchmarks.jar Training20
      */
     
     /**
      * Results
      * 
        Benchmark                      Mode  Cnt    Score     Error  Units
        Training35.pattern             avgt    3  432.157 ±  15.709  ns/op
        Training35.precompiledPattern  avgt    3  325.016 ± 393.624  ns/op
        Training35.precompiledStream   avgt    3  347.904 ±  16.640  ns/op
        Training35.split               avgt    3  184.465 ±  13.128  ns/op
        Training35.stream              avgt    3  478.500 ±  32.254  ns/op
        Training35.tokenize            avgt    3  165.332 ±   3.873  ns/op

     */
}
