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
 * Now with more warmup and more measurement.
 * 
 * @author rschwietzke
 */
@Warmup(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(5)
public class Training07
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
        # Benchmark: org.xceptance.training.jmh.Training07.bench
        
        # Run progress: 0.00% complete, ETA 00:01:40
        # Fork: 1 of 5
        # Warmup Iteration   1: 209.659 ns/op
        # Warmup Iteration   2: 155.465 ns/op
        # Warmup Iteration   3: 139.121 ns/op
        # Warmup Iteration   4: 140.133 ns/op
        # Warmup Iteration   5: 140.187 ns/op
        Iteration   1: 139.212 ns/op
        Iteration   2: 140.968 ns/op
        Iteration   3: 139.579 ns/op
        Iteration   4: 141.277 ns/op
        Iteration   5: 138.973 ns/op
        Iteration   6: 141.145 ns/op
        Iteration   7: 139.613 ns/op
        Iteration   8: 138.963 ns/op
        Iteration   9: 139.251 ns/op
        Iteration  10: 139.401 ns/op
        
        # Run progress: 20.00% complete, ETA 00:01:22
        # Fork: 2 of 5
        # Warmup Iteration   1: 159.677 ns/op
        # Warmup Iteration   2: 159.707 ns/op
        # Warmup Iteration   3: 139.707 ns/op
        # Warmup Iteration   4: 140.440 ns/op
        # Warmup Iteration   5: 138.522 ns/op
        Iteration   1: 139.015 ns/op
        Iteration   2: 138.632 ns/op
        Iteration   3: 140.454 ns/op
        Iteration   4: 144.241 ns/op
        Iteration   5: 139.924 ns/op
        Iteration   6: 139.661 ns/op
        Iteration   7: 140.783 ns/op
        Iteration   8: 140.578 ns/op
        Iteration   9: 138.251 ns/op
        Iteration  10: 145.531 ns/op
        
        # Run progress: 40.00% complete, ETA 00:01:01
        # Fork: 3 of 5
        # Warmup Iteration   1: 168.662 ns/op
        # Warmup Iteration   2: 155.107 ns/op
        # Warmup Iteration   3: 142.235 ns/op
        # Warmup Iteration   4: 141.947 ns/op
        # Warmup Iteration   5: 141.841 ns/op
        Iteration   1: 142.497 ns/op
        Iteration   2: 141.579 ns/op
        Iteration   3: 142.728 ns/op
        Iteration   4: 141.878 ns/op
        Iteration   5: 143.696 ns/op
        Iteration   6: 142.177 ns/op
        Iteration   7: 142.588 ns/op
        Iteration   8: 142.359 ns/op
        Iteration   9: 142.024 ns/op
        Iteration  10: 143.128 ns/op
        
        # Run progress: 60.00% complete, ETA 00:00:40
        # Fork: 4 of 5
        # Warmup Iteration   1: 165.220 ns/op
        # Warmup Iteration   2: 159.926 ns/op
        # Warmup Iteration   3: 144.967 ns/op
        # Warmup Iteration   4: 143.549 ns/op
        # Warmup Iteration   5: 144.540 ns/op
        Iteration   1: 144.370 ns/op
        Iteration   2: 145.209 ns/op
        Iteration   3: 145.022 ns/op
        Iteration   4: 144.319 ns/op
        Iteration   5: 146.187 ns/op
        Iteration   6: 143.527 ns/op
        Iteration   7: 145.681 ns/op
        Iteration   8: 143.500 ns/op
        Iteration   9: 143.673 ns/op
        Iteration  10: 144.914 ns/op
        
        # Run progress: 80.00% complete, ETA 00:00:20
        # Fork: 5 of 5
        # Warmup Iteration   1: 175.664 ns/op
        # Warmup Iteration   2: 151.191 ns/op
        # Warmup Iteration   3: 134.791 ns/op
        # Warmup Iteration   4: 134.669 ns/op
        # Warmup Iteration   5: 134.586 ns/op
        Iteration   1: 134.711 ns/op
        Iteration   2: 135.094 ns/op
        Iteration   3: 134.886 ns/op
        Iteration   4: 136.413 ns/op
        Iteration   5: 137.445 ns/op
        Iteration   6: 137.608 ns/op
        Iteration   7: 135.022 ns/op
        Iteration   8: 137.542 ns/op
        Iteration   9: 138.457 ns/op
        Iteration  10: 136.447 ns/op
        
        
        Result "org.xceptance.training.jmh.Training07.bench":
          140.803 ±(99.9%) 1.530 ns/op [Average]
          (min, avg, max) = (134.711, 140.803, 146.187), stdev = 3.090
          CI (99.9%): [139.273, 142.332] (assumes normal distribution)
        
        
        # Run complete. Total time: 00:01:42
        
        REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
        why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
        experiments, perform baseline and negative tests that provide experimental control, make sure
        the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
        Do not assume the numbers tell you what you want them to tell.
        
        Benchmark         Mode  Cnt    Score   Error  Units
        Training07.bench  avgt   50  140.803 ± 1.530  ns/op

     */
}
