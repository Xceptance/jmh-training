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
 * Small test first. Totally naive!!
 * 
 * @author rschwietzke
 */
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class Training05
{
     @Benchmark
     @BenchmarkMode(Mode.AverageTime)
     @OutputTimeUnit(TimeUnit.NANOSECONDS)
     public void bench()
     {
         "Foo,Bar,Test".split(",");
     }
     
     /**
      * Command line
      * 
      * mvn clean compile install; java -jar target/benchmarks.jar Training05
      */
     
     /**
      * Results
         # JMH version: 1.21
        # VM version: JDK 11.0.4, OpenJDK 64-Bit Server VM, 11.0.4+11-post-Ubuntu-1ubuntu218.04.3
        # VM invoker: /usr/lib/jvm/java-11-openjdk-amd64/bin/java
        # VM options: <none>
        # Warmup: 3 iterations, 1 s each
        # Measurement: 10 iterations, 1 s each
        # Timeout: 10 min per iteration
        # Threads: 1 thread, will synchronize iterations
        # Benchmark mode: Average time, time/op
        # Benchmark: org.xceptance.training.jmh.Training05.bench
        
        # Run progress: 0.00% complete, ETA 00:00:13
        # Fork: 1 of 1
        # Warmup Iteration   1: 194.662 ns/op
        # Warmup Iteration   2: 166.320 ns/op
        # Warmup Iteration   3: 158.765 ns/op
        Iteration   1: 155.177 ns/op
        Iteration   2: 148.119 ns/op
        Iteration   3: 143.208 ns/op
        Iteration   4: 144.138 ns/op
        Iteration   5: 143.335 ns/op
        Iteration   6: 144.108 ns/op
        Iteration   7: 146.223 ns/op
        Iteration   8: 145.873 ns/op
        Iteration   9: 142.524 ns/op
        Iteration  10: 144.172 ns/op
        
        
        Result "org.xceptance.training.jmh.Training05.bench":
          145.688 ±(99.9%) 5.638 ns/op [Average]
          (min, avg, max) = (142.524, 145.688, 155.177), stdev = 3.729
          CI (99.9%): [140.049, 151.326] (assumes normal distribution)
        
        
        # Run complete. Total time: 00:00:13
        
        REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
        why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
        experiments, perform baseline and negative tests that provide experimental control, make sure
        the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
        Do not assume the numbers tell you what you want them to tell.
        
        Benchmark         Mode  Cnt    Score   Error  Units
        Training05.bench  avgt   10  145.688 ± 5.638  ns/op
     */
}
