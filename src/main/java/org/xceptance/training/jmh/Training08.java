package org.xceptance.training.jmh;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;

/**
 * Ok, we still have to learn one thing, one thing only....
 * Return or eat something otherwise this is nonsense.
 * 
 * @author rschwietzke
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class Training08
{
     @Benchmark
     public void s1()
     {
         new String();
     }

     @Benchmark
     public void s2()
     {
         String s = "";
     }

     @Benchmark
     public void s3()
     {
         String s = new String("");
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
     
     @Benchmark
     public void nothing()
     {
     }
     
     /**
      * Command line
      * 
      * mvn clean compile install; java -jar target/benchmarks.jar Training08
      */
     
     /**
      * Results
      * 
        Benchmark           Mode  Cnt  Score   Error  Units
        Training08.nothing  avgt    3  0.502 ± 0.141  ns/op
        Training08.s1       avgt    3  0.503 ± 0.047  ns/op
        Training08.s1_r     avgt    3  6.446 ± 0.566  ns/op
        Training08.s2       avgt    3  0.498 ± 0.019  ns/op
        Training08.s2_r     avgt    3  3.259 ± 0.238  ns/op
        Training08.s3       avgt    3  0.511 ± 0.113  ns/op
        Training08.s3_r     avgt    3  7.439 ± 1.736  ns/op

        -Xint
        Benchmark           Mode  Cnt    Score    Error  Units
        Training08.nothing  avgt    3   92.487 ±  1.405  ns/op
        Training08.s1       avgt    3  289.713 ± 35.695  ns/op
        Training08.s1_r     avgt    3  353.986 ± 81.802  ns/op
        Training08.s2       avgt    3   86.911 ±  4.295  ns/op
        Training08.s2_r     avgt    3  167.986 ± 38.805  ns/op
        Training08.s3       avgt    3  284.768 ± 46.444  ns/op
        Training08.s3_r     avgt    3  366.652 ± 90.978  ns/op

     */
}
