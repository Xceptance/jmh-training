package org.xceptance.training.jmh;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

/**
 * Blackholes to the rescue
 * 
 * @author rschwietzke
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class Training10
{
     @Benchmark
     public void s1_b(final Blackhole b)
     {
         b.consume(new String());
     }
     
     @Benchmark
     public void s2_b(final Blackhole b)
     {
         b.consume("");
     }

     @Benchmark
     public void s3_b(final Blackhole b)
     {
         b.consume(new String(""));
     }     
     
     @Benchmark
     public void nothing()
     {
     }
     
     @Benchmark
     public String s1_r()
     {
         return new String();
     }
     
     @Benchmark
     public String s2_r()
     {
         return "";
     }

     @Benchmark
     public String s3_r()
     {
         return new String("");
     }  
     
     /**
      * Command line
      * 
      * mvn clean compile install; java -jar target/benchmarks.jar Training10
      */
     
     /**
      * Results
      * 
        Benchmark           Mode  Cnt  Score   Error  Units
        Training10.nothing  avgt    3  0.504 ± 0.184  ns/op
        Training10.s1_b     avgt    3  6.349 ± 0.487  ns/op
        Training10.s1_r     avgt    3  6.409 ± 0.487  ns/op
        Training10.s2_b     avgt    3  3.256 ± 0.439  ns/op
        Training10.s2_r     avgt    3  3.251 ± 0.513  ns/op
        Training10.s3_b     avgt    3  7.258 ± 0.253  ns/op
        Training10.s3_r     avgt    3  7.325 ± 0.890  ns/op

     */
}
