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
 * Now with more warmup.
 * 
 * @author rschwietzke
 */
@Warmup(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class Training06
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
      * mvn clean compile install; java -jar target/benchmarks.jar Training06
      */
     
     /**
      * Results
         # JMH version: 1.21
        # VM version: JDK 11.0.4, OpenJDK 64-Bit Server VM, 11.0.4+11-post-Ubuntu-1ubuntu218.04.3
        # VM invoker: /usr/lib/jvm/java-11-openjdk-amd64/bin/java
        # VM options: <none>
        # Warmup: 5 iterations, 2 s each
        # Measurement: 10 iterations, 1 s each
        # Timeout: 10 min per iteration
        # Threads: 1 thread, will synchronize iterations
        # Benchmark mode: Average time, time/op
        # Benchmark: org.xceptance.training.jmh.Training06.bench
        
        # Run progress: 0.00% complete, ETA 00:00:20
        # Fork: 1 of 1
        # Warmup Iteration   1: 162.427 ns/op
        # Warmup Iteration   2: 152.925 ns/op
        # Warmup Iteration   3: 139.471 ns/op
        # Warmup Iteration   4: 139.766 ns/op
        # Warmup Iteration   5: 140.489 ns/op
        Iteration   1: 142.501 ns/op
        Iteration   2: 140.077 ns/op
        Iteration   3: 140.256 ns/op
        Iteration   4: 142.049 ns/op
        Iteration   5: 140.772 ns/op
        Iteration   6: 142.111 ns/op
        Iteration   7: 140.427 ns/op
        Iteration   8: 142.458 ns/op
        Iteration   9: 142.550 ns/op
        Iteration  10: 141.791 ns/op
        
        
        Result "org.xceptance.training.jmh.Training06.bench":
          141.499 ±(99.9%) 1.515 ns/op [Average]
          (min, avg, max) = (140.077, 141.499, 142.550), stdev = 1.002
          CI (99.9%): [139.984, 143.014] (assumes normal distribution)
        
        
        # Run complete. Total time: 00:00:20
        
        REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
        why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
        experiments, perform baseline and negative tests that provide experimental control, make sure
        the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
        Do not assume the numbers tell you what you want them to tell.
        
        Benchmark         Mode  Cnt    Score   Error  Units
        Training06.bench  avgt   10  141.499 ± 1.515  ns/op

     */
}
