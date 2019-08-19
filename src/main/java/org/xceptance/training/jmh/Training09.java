package org.xceptance.training.jmh;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;

/**
 * Just returning might not help... the right thing has to be returned
 * 
 * @author rschwietzke
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class Training09
{
     @Benchmark
     public long returnLong()
     {
         return 1 + new Random().nextLong();
     }

     @Benchmark
     public long returnIgnoreString()
     {
         final long l = new Random().nextLong();
    
         final String s = String.valueOf(l);
         return 10 + l;
     }

     @Benchmark
     public long returnAndUseString()
     {
         final long l = new Random().nextLong();

         final String s = String.valueOf(l);
         return s.length() + l;
     }
    
     
     /**
      * Command line
      * 
      * mvn clean compile install; java -jar target/benchmarks.jar Training09
      */
     
     /**
      * Results
      * 
        Benchmark                      Mode  Cnt    Score    Error  Units
        Training09.returnAndUseString  avgt    3  104.862 ± 33.953  ns/op
        Training09.returnIgnoreString  avgt    3  101.467 ±  4.931  ns/op
        Training09.returnLong          avgt    3   58.611 ±  1.708  ns/op

     */
}
